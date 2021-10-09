package ru.mail.polis.oop.examples.abs;

public abstract class AbstractNotificationManger {

    public void sendNotification(User user) {
        String text = getText(user);
        send(text);
    }

    abstract void send(String text);

    private String getText(User user) {
        return "Hello, " + user.getName();
    }

}
