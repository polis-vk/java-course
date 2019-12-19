package ru.mail.polis.concurrency;


public class SingletonTask {

    private volatile static SingletonTask INSTANCE;

    public static SingletonTask getINSTANCE() {
        if (INSTANCE != null) {
            return INSTANCE;
        }
        synchronized (SingletonTask.class) {
            if (INSTANCE == null) {
                INSTANCE = new SingletonTask();
            }
        }
        return INSTANCE;
    }

    private SingletonTask() {
    }
}
