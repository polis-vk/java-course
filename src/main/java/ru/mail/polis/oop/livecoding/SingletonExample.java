package ru.mail.polis.oop.livecoding;

class DbConnection {

    private static DbConnection INSTANCE = new DbConnection();

    private DbConnection() {
        //
    }

    public static DbConnection getInstance() {
        return INSTANCE;
    }
}

public class SingletonExample {
    public static void main(String[] args) {
        DbConnection connection1 = DbConnection.getInstance();
        DbConnection connection2 = DbConnection.getInstance();
        System.out.println(connection1);
        System.out.println(connection2);
    }
}
