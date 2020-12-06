package ru.mail.polis.concurrency.executors.fork;

public class Problem {

    public double solve(int left, int right) {
        double result = 0;
        for (int i = left; i < right; i++) {
//            result += Math.abs(Math.sin(right));
            result += Math.sin(i);
        }
        return result;
    }
}
