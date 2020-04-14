package ru.mail.polis.classes;

public class MainForEnum {
    public static void main(String[] args) {
        DayOfWeek today = DayOfWeek.MONDAY;
        System.out.println("today is " + today + ". It is " + today.getNumber() + " day of the week");

        DayOfWeek tomorrow = DayOfWeek.valueOf("TUESDAY");
        System.out.println(tomorrow == DayOfWeek.TUESDAY);
        System.out.println("tomorrow is " + tomorrow + ". It is " + tomorrow.getNumber() + " day of the week");
    }
}
