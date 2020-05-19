package ru.mail.polis.throwable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class ThrowableExample {

    // ---------- throw exception ----------


//    public static void main(String[] args) throws IOException {
////        exampleNPE(null);
//        exampleIOE(null);
//    }

    public static void exampleNPE(String arg) {
        if (arg == null) {
            throw new NullPointerException();
        }
    }

    public static void exampleIOE(String arg) throws IOException {
        if (arg == null) {
            throw new IOException();
        }
    }

    // ---------- try-catch-finally ----------

//    public static void main(String[] args) {
////        doSomething(new String[]{"FirstArgument", null, "Argument"});
////        System.out.println("------");
////        doSomething(new String[]{"SecondArgument", "", "Argument"});
////        System.out.println("------");
////        doSomething(new String[]{"ThirdArgument", "Argument"});
////        System.out.println("------");
//        doSomething(new String[]{"ForthArgument", "FifthArgument"});
//    }

    static Random rnd = new Random();

    public static void doSomething(String[] args) {
        try {
            for (String arg : args) {
                validate(arg);
                doSomething(arg);
            }
        } catch (ValidationException e) {
            System.out.println(e.getMessage() + ". Please try again");
            throw new IllegalArgumentException();
        } catch (Throwable e) {
            System.out.println(e.getClass());
        } finally {
            showResult();
        }
    }

    private static void showResult() {
        System.out.println("show result");
    }

    public static void doSomething(String arg) {
        if (arg.equals("Argument")) {
            throw new IllegalArgumentException(arg);
        }
        System.out.println("do something with " + arg);
    }

    public static void validate(String arg) throws ValidationException {
        if (arg == null) {
            throw new ValidationException("arg is null");
        }
        if (arg.isEmpty()) {
            throw new ValidationException("arg is empty");
        }
    }


    // ---------- try-with-resources ----------

    public static void tryWithoutResources() throws IOException {
        InputStream is = new FileInputStream("a.txt");
        RuntimeException exception = null;
        try {
            readFromInputStream(is);
        } catch (IOException e) {
            exception = new RuntimeException(e);
            throw exception;
        } finally {
            try {
                is.close(); // тоже может бросить ошибку, потеряем исходную
            } catch (IOException e) {
                if (exception != null) {
                    exception.addSuppressed(e);
                    throw exception;
                }
            }
        }
    }

    public static void tryWithResources() throws IOException {
        try (InputStream is = new FileInputStream("a.txt")) {
            readFromInputStream(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readFromInputStream(InputStream is) throws IOException {

    }


    static class Exception1 extends Exception {

    }

    static class Exception2 extends Exception1 {

    }

    static class Exception3 extends Exception2 {

    }

    public static void main(String[] args) {
        System.out.println(doException());
    }

    private static int doException() {
        try {
            System.out.println("try");
            throw new Exception3();
//            return 0;
        } catch (Exception3 e3) {
            System.out.println("e3");
            return 1;
        } catch (Exception2 e2) {
            System.out.println("e2");
            return 2;
        } catch (Exception1 e1) {
            System.out.println("e1");
            return 3;
        } finally {
            System.out.println("finally");
            return 4;
        }
    }
}
