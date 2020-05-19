package ru.mail.polis.reflection.method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodModifierSpy {
    private static int count;
    private static synchronized void inc() { count++; }
    private static synchronized int cnt() { return count; }

    public static void main(String... args) {
        spy("java.lang.Object", "wait");
        System.out.println(" --------------- ");
        spy("ru.mail.polis.reflection.method.MethodModifierSpy", "inc");
        System.out.println(" --------------- ");
        spy("java.lang.Class", "getConstructor");
        System.out.println(" --------------- ");
        spy("java.lang.String", "compareTo");
    }
    
    public static void spy(String className, String methodName) {
        try {
            Class<?> c = Class.forName(className);
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                if (!m.getName().equals(methodName)) {
                    continue;
                }
                System.out.format("%s%n", m.toGenericString());
                System.out.format("  Modifiers:  %s%n",
                        Modifier.toString(m.getModifiers()));
                System.out.format("  [ synthetic=%-5b var_args=%-5b bridge=%-5b ]%n",
                        m.isSynthetic(), m.isVarArgs(), m.isBridge());
                inc();
            }
            System.out.format("%d matching overload%s found%n", cnt(),
                    (cnt() == 1 ? "" : "s"));

            // production code should handle this exception more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
