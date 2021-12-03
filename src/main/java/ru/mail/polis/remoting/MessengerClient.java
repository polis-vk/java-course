package ru.mail.polis.remoting;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class MessengerClient {

    private static final Random random = new Random();

    public static void main(String[] args) {

        String clientId = randomString(5);
        System.out.println("Got id: " + clientId);

        try {
            Registry registry = LocateRegistry.getRegistry(null, 1099);
            MessengerService server = (MessengerService) registry.lookup(MessengerService.NAME);

            final int numMessages = 100;

            for (int i = 0; i < numMessages; ++i) {
                String message = randomString(10);

                System.out.printf("Send message (%d / %d): %s%n", i + 1, numMessages, message);
                server.sendMessage(clientId, message);

                long sleepMillis = 3000L + random.nextInt(7000 + 1);
                Thread.sleep(sleepMillis);
            }
        } catch (Exception e) {
            System.err.println("Client exception:");
            e.printStackTrace();
        }
    }

    private static final String randomString(int length) {
        return random.ints('a', 'z' + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
