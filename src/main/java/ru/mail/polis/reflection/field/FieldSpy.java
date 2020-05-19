package ru.mail.polis.reflection.field;

import java.lang.reflect.Field;
import java.util.List;

public class FieldSpy<T> {
    public boolean[][] b = {{ false, false }, { true, true } };
    public String name  = "Alice";
    public List<Integer> list;
    public T val;
    public int a  = 10;
    public Integer aa = 10;

    public static void main(String... args) {
        spy("ru.mail.polis.reflection.field.FieldSpy", "b");
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldSpy", "name");
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldSpy", "list");
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldSpy", "val");
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldSpy", "a");
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldSpy", "aa");
    }

    private static void spy(String className, String fieldName) {
        try {
            Class<?> c = Class.forName(className);
            Field f = c.getField(fieldName);
            System.out.format("Type: %s%n", f.getType());
            System.out.format("GenericType: %s%n", f.getGenericType());

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}
