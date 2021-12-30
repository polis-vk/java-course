package ru.mail.polis.oop.examples.abs;

public class EmailNotificationManager extends AbstractNotificationManger {
    @Override
    protected void send(String text) {
        System.out.println("Sending email: " + text);
    }
}
