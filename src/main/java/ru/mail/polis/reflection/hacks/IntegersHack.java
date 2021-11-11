package ru.mail.polis.reflection.hacks;

import java.lang.reflect.Field;

public class IntegersHack {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        System.out.printf("2 + 2 = %d%n", 2 + 2); // 4

        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);
        value.set(4, 5);

        System.out.printf("2 + 2 = %d%n", 2 + 2); // 5
    }
}
