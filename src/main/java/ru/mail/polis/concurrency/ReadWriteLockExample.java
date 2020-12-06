package ru.mail.polis.concurrency;

import ru.mail.polis.concurrency.work.MyReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {


    private final ReadWriteLock readWriteLock = new MyReadWriteLock(1000);

    public void put() {
//        System.out.println("put " + Thread.currentThread().getName());

        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            System.out.println("start write " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("finish write " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void get() {
//        System.out.println("get " + Thread.currentThread().getName());

        Lock lock = readWriteLock.readLock();
        lock.lock();
        try {
            System.out.println("start read " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("finish read " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample();
        new Thread(example::get).start();
        new Thread(example::get).start();
        new Thread(example::get).start();
        new Thread(example::get).start();

        new Thread(example::put).start();
        Thread.sleep(1000);

        new Thread(example::get).start();
        new Thread(example::get).start();
        new Thread(example::get).start();
        new Thread(example::get).start();

    }
}
