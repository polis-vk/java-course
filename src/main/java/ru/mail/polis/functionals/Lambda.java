package ru.mail.polis.functionals;

import java.io.File;
import java.io.FileFilter;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;

public class Lambda {

    public static File[] getJavaFiles(File directory) {
        File[] files =  directory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(".java");
            }
        });

        files = directory.listFiles(file -> file.getName().endsWith(".java"));
        return files;
    }

    private int counter;

    public void lambda() {
        IntUnaryOperator operator = x -> { return (x % 2 == 0) ? x : x - 1; };
        IntUnaryOperator simpleOperator = x -> (x % 2 == 0) ? x : x - 1;
        counter++;
        int bonus = 10;
        IntUnaryOperator hardOperator = x -> {
            counter++;
            return x + bonus;
        };
//        bonus = 12;
    }

    public void link() {
        IntBinaryOperator intComparator = Integer::compare;
        Consumer<Object> printer = System.out::println;
        Function<Object, String> toString = Object::toString;
        IntFunction<Object[]> arrayCreator = Object[]::new;

    }

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
            double length1 =  Math.sqrt(square1.getX() * square1.getX() + square1.getY() * square1.getY());
            double length2 =  Math.sqrt(square2.getX() * square2.getX() + square2.getY() * square2.getY());
            return (int) (length1 - length2);
        };

        if (Comparator.comparing((Square square) -> Math.sqrt(square.getX() * square.getX() +
                square.getY() * square.getY()), Double::compare).compare(a, b) > 0) {
            System.out.println(a.h);
        }
    }
}
