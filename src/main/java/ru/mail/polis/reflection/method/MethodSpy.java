package ru.mail.polis.reflection.method;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodSpy {
    private static final String  fmt = "%24s: %s%n";

    // for the morbidly curious
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void main(String... args) {
        spy("java.lang.Class", "getConstructor");
        // Method.isVarArgs()
        System.out.println(" --------------- ");
        spy("java.lang.Class", "cast");
        System.out.println(" --------------- ");
        spy("java.io.PrintStream", "format");

    }

    private static void spy(String className, String methodName) {
        try {
            Class<?> c = Class.forName(className);
            Method[] allMethods = c.getDeclaredMethods();
            for (Method m : allMethods) {
                if (!m.getName().equals(methodName)) {
                    continue;
                }
                System.out.format("%s%n", m.toGenericString());

                System.out.format(fmt, "ReturnType", m.getReturnType());
                System.out.format(fmt, "GenericReturnType", m.getGenericReturnType());

                Class<?>[] pType  = m.getParameterTypes();
                Type[] gpType = m.getGenericParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    System.out.format(fmt,"ParameterType", pType[i]);
                    System.out.format(fmt,"GenericParameterType", gpType[i]);
                }

                Class<?>[] xType  = m.getExceptionTypes();
                Type[] gxType = m.getGenericExceptionTypes();
                for (int i = 0; i < xType.length; i++) {
                    System.out.format(fmt,"ExceptionType", xType[i]);
                    System.out.format(fmt,"GenericExceptionType", gxType[i]);
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}