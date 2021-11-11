package ru.mail.polis.reflection.hacks;

import java.lang.reflect.Field;

public class StringHack {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field value = String.class.getDeclaredField("value");
        value.setAccessible(true);
        System.out.println("hello!");
        System.out.println("hello!".equals("hi!"));
        value.set("hello!", "hi!".toCharArray());
        System.out.println("hello!");
        System.out.println("hello!".equals("hi!"));
    }
}
