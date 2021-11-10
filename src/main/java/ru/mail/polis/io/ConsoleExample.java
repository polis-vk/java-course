package ru.mail.polis.io;

import java.io.Console;

public class ConsoleExample {

    public static void main(String[] args) {
        Console console = System.console();
        console.printf("Введите юзера ");
        String user = console.readLine();
        console.printf("%s, введите пароль", user);
        char[] pass = console.readPassword();
        console.printf("прощайте %s", user);
        doAction(pass);
        pass = new char[]{' '};
    }

    private static void doAction(char[] pass) {

    }

}
