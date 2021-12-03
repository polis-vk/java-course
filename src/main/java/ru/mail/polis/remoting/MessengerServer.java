package ru.mail.polis.remoting;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Run first
 */
public class MessengerServer {

    public static void main(String[] args) {
        try {
            MessengerService server = new MessengerServiceImpl();
            MessengerService stub = (MessengerService) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(MessengerService.NAME, stub);
            System.out.println("MessengerService bound successfully");
        } catch (Exception e) {
            System.err.println("MessengerService exception:");
            e.printStackTrace();
        }
    }
}
