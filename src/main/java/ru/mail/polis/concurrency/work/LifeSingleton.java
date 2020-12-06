package ru.mail.polis.concurrency.work;

public class LifeSingleton {

    private static volatile LifeSingleton instance;

    public static LifeSingleton getInstance() {
        if (instance == null) {
            synchronized (LifeSingleton.class) {
                if (instance == null) {
                    instance = new LifeSingleton();
                }
            }
        }
        return instance;
    }



    private LifeSingleton() {
    }

}
