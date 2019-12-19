package ru.mail.polis.concurrency;

public class Singleton {

    private static volatile Singleton INSTANCE;

    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    private int x = 1;

    private Singleton() {}

    public int getX() {
        return x;
    }
}
