package ru.mail.polis.reflection.field;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

enum Spy { BLACK , WHITE }

public class FieldModifierSpy {

    volatile int share;
    int instance;
    class Inner {}

    public static void main(String[] args) {
        spy("ru.mail.polis.reflection.field.FieldModifierSpy", Arrays.asList("volatile"));
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.Spy", Arrays.asList("public"));
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldModifierSpy$Inner", Arrays.asList("final"));
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.Spy", Arrays.asList("private", "static", "final"));
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.field.FieldModifierSpy", Arrays.asList("a"));
    }

    public static void spy(String className, List<String> modifiers) {
        try {
            Class<?> c = Class.forName(className);
            int searchMods = 0x0;
            for (String modifier : modifiers) {
                searchMods |= modifierFromString(modifier);
            }

            Field[] flds = c.getDeclaredFields();
            System.out.format("Fields in Class '%s' containing modifiers:  %s%n",
                    c.getName(),
                    Modifier.toString(searchMods));
            boolean found = false;
            for (Field f : flds) {
                int foundMods = f.getModifiers();
                // Require all of the requested modifiers to be present
                if ((foundMods & searchMods) == searchMods) {
                    System.out.format("%-8s [ synthetic=%-5b enum_constant=%-5b ]%n",
                            f.getName(), f.isSynthetic(),
                            f.isEnumConstant());
                    found = true;
                }
            }

            if (!found) {
                System.out.format("No matching fields%n");
            }

            // production code should handle this exception more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static int modifierFromString(String s) {
        int m = 0x0;
        if ("public".equals(s))           m |= Modifier.PUBLIC;
        else if ("protected".equals(s))   m |= Modifier.PROTECTED;
        else if ("private".equals(s))     m |= Modifier.PRIVATE;
        else if ("static".equals(s))      m |= Modifier.STATIC;
        else if ("final".equals(s))       m |= Modifier.FINAL;
        else if ("transient".equals(s))   m |= Modifier.TRANSIENT;
        else if ("volatile".equals(s))    m |= Modifier.VOLATILE;
        return m;
    }
}
