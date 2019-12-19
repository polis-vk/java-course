package ru.mail.polis.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CountDownLatchExample {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(10);

        for (int i = 0; i < 10; i++) {
            new DemoThread(barrier).start();
        }


    }

    private static class DemoThread extends Thread {
        private final CyclicBarrier barrier;

        private DemoThread(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                action();
            } catch (Throwable e) {
                System.out.println(getName() + " interrupted");
            }
        }

        private void action() throws InterruptedException, BrokenBarrierException {
            Thread.sleep((long) (Math.random() * 10_000L));

            System.out.println(getName() + " finish initialization");
//            barrier.countDown();
            barrier.await();

            System.out.println(getName() + " main phase");

            Thread.sleep((long) (Math.random() * 10_000L));
            barrier.await();
            System.out.println(getName() + " end phase");

        }
    }
}
