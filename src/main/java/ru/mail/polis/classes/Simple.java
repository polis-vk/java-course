package ru.mail.polis.classes;

import java.io.IOException;
import java.nio.file.Files;

public class Simple {

    static {
        System.out.println("Main static init 1");
    }

    public static void main(String[] args) {
        forAction("str", "str",0);
    }

    public static void forAction(String n, String a, int maxCount) {
        switch (n) {
            case "str":
                System.out.println("n = 1");
            case "sasd":
                System.out.println("n = 2");
                System.out.println("n = 2");
                System.out.println("n = 2");
                System.out.println("n = 2");
                break;
            default:
                System.out.println("n != 1 && n != 2");
        }
    }
}
