package ru.mail.polis.reflection.field;

import java.lang.reflect.Field;
import java.util.Arrays;

enum Tweedle { DEE, DUM }

public class Book {
    public long chapters = 0;
    public String[] characters = { "Alice", "White Rabbit" };
    public Tweedle twin = Tweedle.DEE;

    public final boolean b = true;


    public static void main(String... args) {
        Book book = new Book();
        String fmt = "%6S:  %-12s = %s%n";

        try {
            Class<?> c = book.getClass();

            Field chap = c.getDeclaredField("chapters");
            System.out.format(fmt, "before", "chapters", book.chapters);
            chap.setLong(book, 12);
            System.out.format(fmt, "after", "chapters", chap.getLong(book));

            Field chars = c.getDeclaredField("characters");
            System.out.format(fmt, "before", "characters",
                    Arrays.asList(book.characters));
            String[] newChars = { "Queen", "King" };
            chars.set(book, newChars);
            System.out.format(fmt, "after", "characters",
                    Arrays.asList(book.characters));

            Field t = c.getDeclaredField("twin");
            System.out.format(fmt, "before", "twin", book.twin);
            t.set(book, Tweedle.DUM);
            System.out.format(fmt, "after", "twin", t.get(book));


            Field bool = c.getDeclaredField("b");
            System.out.format(fmt, "before", "b", book.b);
//            bool.setAccessible(true);
            bool.setBoolean(book, Boolean.FALSE);   // IllegalAccessException
            System.out.format(fmt, "after", "b", bool.get(book));

            // production code should handle these exceptions more gracefully
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
