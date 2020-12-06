package ru.mail.polis.concurrency.work;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class MyReadWriteLock implements ReadWriteLock {

    private final Semaphore semaphore;
    private final int n;
    private final MyLock readLock = new MyLock(true);
    private final MyLock writeLock = new MyLock(false);

    public MyReadWriteLock(int n) {
        this.semaphore = new Semaphore(n, true);
        this.n = n;
    }


    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }

    class MyLock implements Lock {

        private final boolean isReadLock;

        MyLock(boolean isReadLock) {
            this.isReadLock = isReadLock;
        }

        @Override
        public void lock() {
            try {
                if (isReadLock) {
                    semaphore.acquire();
                } else {
                    semaphore.acquire(n);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            if (isReadLock) {
                semaphore.release();
            } else {
                semaphore.release(n);
            }
        }

        @Override
        public Condition newCondition() {
            return null;
        }
    }
}
