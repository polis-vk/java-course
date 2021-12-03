package ru.mail.polis.remoting;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MessengerService extends Remote {
    String NAME = "MessengerService";

    String sendMessage(String clientId, String message) throws RemoteException;
}
