package ru.mail.polis.reflection.method;


import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class MethodParameterSpy {
    private static final String  fmt = "%24s: %s%n";

    // for the morbidly curious
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void main(String... args) {

        try {
            printClassMethods(Class.forName("ru.mail.polis.reflection.method.ExampleMethods"));
            System.out.println(" --------------- ");
            printClassMethods(Class.forName("ru.mail.polis.reflection.method.MethodParameterExamples"));
            System.out.println(" --------------- ");
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    public static void printClassMethods(Class c) {
        Method[] allMethods = c.getDeclaredMethods();
        System.out.format(fmt, "Number of methods", allMethods.length);
        for (Method m : allMethods) {
            printMethod(m);
        }
    }

    public static void printMethod(Method m) {
        System.out.format("%s%n", m.toGenericString());
        System.out.format(fmt, "Return type", m.getReturnType());
        System.out.format(fmt, "Generic return type", m.getGenericReturnType());

        Parameter[] params = m.getParameters();
        for (int i = 0; i < params.length; i++) {
            printParameter(params[i]);
        }
    }

    public static void printParameter(Parameter p) {
        System.out.format(fmt, "Parameter class", p.getType());
        System.out.format(fmt, "Parameter name", p.getName());
        System.out.format(fmt, "Modifiers", p.getModifiers());
        System.out.format(fmt, "Is implicit?", p.isImplicit());
        System.out.format(fmt, "Is name present?", p.isNamePresent());
        System.out.format(fmt, "Is synthetic?", p.isSynthetic());
    }
}
