package ru.mail.polis.misc;

import java.sql.Date;

public class ClassLoaderExamples {


    public static void main(String[] args) {
        ClassLoaderExamples demoObject = new ClassLoaderExamples();
        ClassLoader applicationClassLoader = demoObject.getClass().getClassLoader();
        printClassLoaderDetails(applicationClassLoader);

        // java.sql classes are loaded by platform classloader
        java.sql.Date now = new Date(System.currentTimeMillis());
        ClassLoader platformClassLoader = now.getClass().getClassLoader();
        printClassLoaderDetails(platformClassLoader);

        // java.lang classes are loaded by bootstrap classloader
        ClassLoader bootstrapClassLoader = args.getClass().getClassLoader();
        printClassLoaderDetails(bootstrapClassLoader);
    }

    private static void printClassLoaderDetails(ClassLoader classLoader) {
        // bootstrap classloader is represented by null in JVM
        if (classLoader != null) {
            System.out.println("ClassLoader class : " + classLoader.getClass().getName());
        } else {
            System.out.println("Bootstrap classloader");
        }
    }
}
