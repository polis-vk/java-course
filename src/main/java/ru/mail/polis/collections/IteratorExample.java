package ru.mail.polis.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class IteratorExample {

    public static void main(String... args) {

        Set<String> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i + "");
        }
        iterLoop(set);
    }

    // maxList / count < 0,75 //
//    [112] [5 -> 1001]  -> [112] [5 -> 1001] [2 -> 162 -> 998] [111] -> [112] [1001] [2 -> 162] [111]   [][5][998][]

    static void forEach(Collection<String> iterable) {
        for (String str : iterable) {
            System.out.println(str);
        }
    }

    static void iterLoop(Collection<String> iterable) {
        for (Iterator<String> iterator = iterable.iterator(); iterator.hasNext(); ) {
            String str = iterator.next();
            System.out.println(str);
        }
    }

}
