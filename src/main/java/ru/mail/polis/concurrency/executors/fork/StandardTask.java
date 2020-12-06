package ru.mail.polis.concurrency.executors.fork;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class StandardTask extends RecursiveTask<Double> {

    private static final int THRESHOLD = 10;
    private final Problem problem;
    private final int left;
    private final int right;

    public StandardTask(Problem problem, int left, int right) {
        this.problem = problem;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Double compute() {
        if (right - left < THRESHOLD) {
            return problem.solve(left, right);
        }

        int mid = (left + right) / 2;
        StandardTask t1 = new StandardTask(problem, left, mid);
        StandardTask t2 = new StandardTask(problem, mid, right);
        t1.fork();
        t2.fork();
//        ForkJoinTask.invokeAll(t1, t2);

        double result = 0;
        result += t2.join();
        result += t1.join();
        return result;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        Future<Double> future = forkJoinPool.submit(new StandardTask(new Problem(), 0, 200_000_000));
        System.out.println(future.get());
        long finish = System.currentTimeMillis();
        System.out.println("Time " + (finish - start));


        start = System.currentTimeMillis();
        System.out.println(new Problem().solve(0, 200_000_000));
        finish = System.currentTimeMillis();
        System.out.println("Time " + (finish - start));
    }
}

// выполняет одну из задач в контексте воркера, если нужно
// тут уже порядок join-ов не важен
// ForkJoinTask.invokeAll(t1, t2);