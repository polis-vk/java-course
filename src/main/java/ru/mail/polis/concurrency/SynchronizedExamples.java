package ru.mail.polis.concurrency;

@SuppressWarnings("WeakerAccess")
public class SynchronizedExamples {

    private final Object obj;

    public SynchronizedExamples(Object obj) {
        this.obj = obj;
    }

    public synchronized void doSomething() {

    }

    public void doSomething1() {
        synchronized (obj) {
            doOther();
        }
    }

    private void doOther() {

    }

    public static void main(String[] args) {
        new SynchronizedExamples(new Object()).doSomething1();
    }
}
