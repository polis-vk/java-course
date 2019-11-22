package ru.mail.polis.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class IteratorExample {

    public static void main(String... args) {
        iterLoop(Arrays.asList("Привет", "Андрей", "!"));
    }

    static void forEach(Collection<String> iterable) {
        for (String str : iterable) {
            System.out.println(str);
        }
    }

    static void iterLoop(Collection<String> iterable) {
        Iterator<String> it = null;
        for (it = iterable.iterator(); it.hasNext(); ) {
            String str = it.next();
            System.out.println(str);
        }
    }
}
