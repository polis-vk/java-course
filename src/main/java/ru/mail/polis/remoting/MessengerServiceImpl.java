package ru.mail.polis.remoting;

public class MessengerServiceImpl implements MessengerService {

    @Override
    public String sendMessage(String clientId, String message) {
        System.out.printf("Send message '%s' from %s%n", message, clientId);
        return "Server Message";
    }
}
