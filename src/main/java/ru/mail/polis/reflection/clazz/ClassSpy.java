package ru.mail.polis.reflection.clazz;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

enum ClassMember { CONSTRUCTOR, FIELD, METHOD, CLASS, ALL }

public class ClassSpy {
    public static void main(String... args) {
        spy("java.lang.ClassCastException", Arrays.asList(ClassMember.CONSTRUCTOR));
        System.out.println(" --------------- ");
        spy("java.nio.channels.ReadableByteChannel", Arrays.asList(ClassMember.METHOD));
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.clazz.ClassMember", Arrays.asList(ClassMember.FIELD, ClassMember.METHOD));
    }

    private static void spy(String className, List<ClassMember> classMembers ) {
        try {
            Class<?> c = Class.forName(className);
            System.out.format("Class:%n  %s%n%n", c.getCanonicalName());

            Package p = c.getPackage();
            System.out.format("Package:%n  %s%n%n",
                    (p != null ? p.getName() : "-- No Package --"));

            for (ClassMember classMember : classMembers) {
                switch (classMember) {
                    case CONSTRUCTOR:
                        printMembers(c.getConstructors(), "Constructor");
                        break;
                    case FIELD:
                        printMembers(c.getFields(), "Fields");
                        break;
                    case METHOD:
                        printMembers(c.getMethods(), "Methods");
                        break;
                    case CLASS:
                        printClasses(c);
                        break;
                    case ALL:
                        printMembers(c.getConstructors(), "Constuctors");
                        printMembers(c.getFields(), "Fields");
                        printMembers(c.getMethods(), "Methods");
                        printClasses(c);
                        break;
                    default:
                        assert false;
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static void printMembers(Member[] mbrs, String s) {
        System.out.format("%s:%n", s);
        for (Member mbr : mbrs) {
            if (mbr instanceof Field)
                System.out.format("  %s%n", ((Field) mbr).toGenericString());
            else if (mbr instanceof Constructor)
                System.out.format("  %s%n", ((Constructor<?>) mbr).toGenericString());
            else if (mbr instanceof Method)
                System.out.format("  %s%n", ((Method) mbr).toGenericString());
        }
        if (mbrs.length == 0)
            System.out.format("  -- No %s --%n", s);
        System.out.format("%n");
    }

    private static void printClasses(Class<?> c) {
        System.out.format("Classes:%n");
        Class<?>[] clss = c.getClasses();
        for (Class<?> cls : clss)
            System.out.format("  %s%n", cls.getCanonicalName());
        if (clss.length == 0)
            System.out.format("  -- No member interfaces, classes, or enums --%n");
        System.out.format("%n");
    }
}
