package ru.mail.polis.classes;

public class Switch {

    public static void main(String... arg) {
        switchExample(-1);
        switchExample(0);
        switchExample(1);
        switchExample(2);
        switchExample(10);
    }

    public static void sign(int number) {
        switch (number) {
            case -1:
                System.out.println("negative");
                break;
            case 0:
                System.out.println("zero");
                break;
            default:
                System.out.println("positive");
        }
    }

     private static void switchExample(int number) {
        switch (number) {
            case 0:
            case 1:
                System.out.println("0 or 1");
                break;
            case 10:
                System.out.println("10");
            case 12:
                System.out.println("12");
                break;
            default:
                System.out.println("other");
        }
    }
}
