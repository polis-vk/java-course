package ru.mail.polis.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2, true);

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoThread thread = new DemoThread(semaphore);
            threads.add(thread);
            thread.start();
        }

        Thread.sleep(20_000);
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static class DemoThread extends Thread {

        private final Semaphore semaphore;

        private DemoThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                action();
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
            }
        }

        private void action() throws InterruptedException {
            for (;;) {
                semaphore.acquire();
                try {
                    System.out.println(getName() + " acquired semaphore");
                    Thread.sleep(5000);
                } finally {
                    System.out.println(getName() + " releasing semaphore");
                    semaphore.release();
                }
            }
        }
    }
}
