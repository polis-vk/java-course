package ru.mail.polis.concurrency.work;

public class Box {

    private final Server server;
    private volatile Config config;

    public Box(Server server) {
        this.server = server;
    }

    public void action() {
        synchronized (this) {
            config = server.getConfig();
        }
        doAction();
    }

    private void doAction() {
        // config используется
    }

}
