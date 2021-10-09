package ru.mail.polis.oop.examples.abs;

public class PushNotificationManager extends AbstractNotificationManger {
    private final PushManager pushManager;

    public PushNotificationManager() {
        this.pushManager = new PushManager();
    }

    @Override
    protected void send(String text) {
        pushManager.sendPush(text);
    }
}
