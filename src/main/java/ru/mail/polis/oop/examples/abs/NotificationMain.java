package ru.mail.polis.oop.examples.abs;

public class NotificationMain {
    public static void main(String[] args) {
        AbstractNotificationManger emailManager = new EmailNotificationManager();
        AbstractNotificationManger pushManager = new PushNotificationManager();
        emailManager.sendNotification(new User(12, "John Doe"));
        pushManager.sendNotification(new User(14, "Ann Doe"));
    }
}
