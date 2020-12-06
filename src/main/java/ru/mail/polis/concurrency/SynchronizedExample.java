package ru.mail.polis.concurrency;

public class SynchronizedExample {

    private final Object obj;

    public SynchronizedExample(Object obj) {
        this.obj = obj;
    }

    private void doSomething1() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " start doSomething1");
            doSomething();
            System.out.println(Thread.currentThread().getName() + " finish doSomething1");
        }
    }


    private void doSomething3() {
        synchronized (obj) {
            System.out.println(Thread.currentThread().getName() + " start doSomething1");
            doSomething();
            System.out.println(Thread.currentThread().getName() + " finish doSomething1");
        }
    }

    private void doSomething() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void doSomething2() {
        System.out.println(Thread.currentThread().getName() + " start doSomething2");
        doSomething();
        System.out.println(Thread.currentThread().getName() + " finish doSomething2");
    }

    public static void main(String[] args) {
        Object obj = new Object();
        SynchronizedExample synchronizedExample1 = new SynchronizedExample(obj);
        SynchronizedExample synchronizedExample2 = new SynchronizedExample(obj);
        new Thread(synchronizedExample1::doSomething2).start();
        synchronizedExample2.doSomething2();
    }
}
