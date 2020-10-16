package ru.mail.polis.functionals;

import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

public class Lambda {

    public static class OldFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            return file.getName().endsWith(".java");
        }
    }

    public static File[] getJavaFiles(File directory) {

        File[] files = directory.listFiles(new OldFileFilter());

        files =  directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        });
//
        files = directory.listFiles(file -> file.getName().endsWith(".java"));

        System.out.println(files);
        return files;
    }

    private int counter;

    public static void testFunction(IntUnaryOperator op) {
        op.applyAsInt(4);
    }

    public void lambda() {
//        IntUnaryOperator operator = x -> {
//            return (x % 2 == 0) ? x : x - 1;
//        };

        IntUnaryOperator operator = new IntUnaryOperator() {
            @Override
            public int applyAsInt(int x) {
                return (x % 2 == 0) ? x : x - 1;
            }
        };

        testFunction(x -> (x % 2 == 0) ? x : x - 1);

        IntUnaryOperator simpleOperator = x -> (x % 2 == 0) ? x : x - 1;
        counter++;
        int[] bonus = new int[] {10};
        IntUnaryOperator hardOperator = x -> {
            counter++;
            return x + bonus[0];
        };
        bonus[0] = 12;

    }

    //------------------------------------------------------------------------------------------

    public static void lambdaIdentity(String[] args) {
//        Runnable r = () -> System.out.println("Hello World!");
//        r.run();
//
        run(() -> System.out.println("Hello World!"));
//
//        Object x = (Runnable)() -> System.out.println("Hello World!");


        Runnable r = (args.length > 0 ?
                () -> System.out.println("Hello " + args[0] + "!") :
                () -> System.out.println("Hello World!"));

//        Object x = (Runnable) (args.length > 0 ?
//                () -> System.out.println("Hello " + args[0] + "!") :
//                () -> System.out.println("Hello World!"));


    }

    public static void valueCompatible(String arg) {
//        IntSupplier x = () -> 5;
//        System.out.println(x.getAsInt());


        IntSupplier x = () -> {
            while (true) {
            }
        };
        System.out.println(x.getAsInt());
    }

    static void run(Runnable r) {
        r.run();
    }

    //------------------------------------------------------------------------------------------


    public static void link() {
        IntBinaryOperator intComparator = Integer::compare;
        Consumer<Object> printer = System.out::println;
        Consumer<PrintStream> printStreamConsumer = PrintStream::println;
        Function<Object, String> toString = Object::toString;
        IntFunction<Object[]> arrayCreator = Object[]::new;

        System.setOut(null);
        Consumer<Object> lambdaPrinter = obj -> System.out.println(obj);
        Consumer<Object> methodRefPrinter = System.out::println;
        methodRefPrinter.accept("hello");
        lambdaPrinter.accept("hello");
    }

    //------------------------------------------------------------------------------------------


    public static void functionalPrimitive() {
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> compose = square.compose(x -> 2 * x);
        System.out.println(compose.apply(3));
        //f(x) -> x^2
        //g(x) -> 2 * x
        // h(x) -> (2 * x) ^ 2

        Function<Integer, Integer> inc = bind(Integer::sum, 1);
        System.out.println(inc.apply(10));
        //f(x, y) -> x + y
        //g(x) = f(x, 1)
        //g(10) = 11


        Function<Integer, Function<Integer, Integer>> curry = curry(Integer::sum);
        System.out.println(curry.apply(5).apply(6));
    }

    static <A, B, C> Function<B, C> bind(BiFunction<A, B, C> fn, A a) {
        Objects.requireNonNull(fn);
        return b -> fn.apply(a, b);
    }

    static <A, B, C> Function<A, Function<B, C>> curry(BiFunction<A, B, C> fn) {
        return a -> b -> fn.apply(a, b);
    }

    //------------------------------------------------------------------------------------------

    public static class Square {
        int x, y;
        int h;

        public Square(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getH() {
            return h;
        }
    }

    public static void main(String[] args) {
        Square a = new Square(0, 0, 2);
        Square b = new Square(1, 1, 1);

        Comparator<Square> comparator = (square1, square2) -> {
            double length1 = Math.sqrt(square1.getX() * square1.getX() + square1.getY() * square1.getY());
            double length2 = Math.sqrt(square2.getX() * square2.getX() + square2.getY() * square2.getY());
            return (int) (length1 - length2);
        };

        Comparator<Square> squareComparator =
                Comparator.comparing(Lambda::getSquareDistance, Double::compare);
        if (squareComparator.compare(a, b) > 0) {
            System.out.println(a.h);
        }
    }

    private static Double getSquareDistance(Square square) {
        return Math.sqrt(square.getX() * square.getX() + square.getY() * square.getY());
    }

    //------------------------------------------------------------------------------------------

    public static void optionalString() {
        Optional<String> optional = Optional.ofNullable("str");

        String result = optional.map(str -> str + "!")
                .map(str -> str.substring(0, 2))
                .orElse("empty");
    }
}
