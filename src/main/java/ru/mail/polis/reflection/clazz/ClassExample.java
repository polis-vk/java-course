package ru.mail.polis.reflection.clazz;

import java.util.Arrays;

public class ClassExample {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<? extends String[]> clazz1 = args.getClass();
//        Class<? extends String[]> clazz2 = args.class;
        Class<? extends String[]> clazz2 = String[].class;
        Class<?> clazz3 = Class.forName("[Ljava.lang.String;");
        Class<?> clazz4 = clazz1.getSuperclass();
        Class<?>[] clazz5 = Character.class.getClasses();
        Class<?>[] clazz6 = Character.class.getDeclaredClasses();

        System.out.println(clazz1);
        System.out.println(clazz2);
        System.out.println(clazz3);
        System.out.println(clazz4);
        System.out.println(Arrays.toString(clazz5));
        System.out.println(Arrays.toString(clazz6));
        System.out.println(Double[].class);
        System.out.println(char[].class);

    }
}
