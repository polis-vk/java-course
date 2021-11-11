package ru.mail.polis.reflection.security;

import java.lang.reflect.Field;

public class SecurityManagerExample {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        System.setSecurityManager(new SecurityManager());

        X x = new X(42);

        System.out.println(x);

        Field value = X.class.getDeclaredField("x");
        value.setAccessible(true);
        value.set(x, 43);

        System.out.println(x);
    }

    static class X {
        private int x;

        public X(int x) {
            this.x = x;
        }

        @Override
        public String toString() {
            return "x=" + x;
        }
    }
}
