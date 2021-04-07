package ru.mail.polis.classes.livecoding;

import java.io.Serializable;
import java.util.Objects;

/**
 * Опишите иммутабельное комплексное число, с геттерами и методами toString()
 * Так же дополните класс методами, чтобы эти объекты можно было использовать в HashMap
 * Плюс реализуйте 2 любых метод (операций) над комплексными числами (на ваш выбор).
 * Одна опреация должна быть статической, другая - нет.
 */
public class ComplexNumber {

    private static final double EPS = 0.0001;

    public static ComplexNumber multiply(ComplexNumber x, ComplexNumber y) {
        return new ComplexNumber(x.re * y.re - x.im * y.im, x.re * y.im + x.im * y.re);
    }

    private final double re;
    private final double im;

    public ComplexNumber(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public ComplexNumber plus(ComplexNumber other) {
        return new ComplexNumber(re + other.re, im + other.im);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return doubleEquals(re, that.re) && doubleEquals(im, that.im);
    }

    @Override
    public int hashCode() {
        return Objects.hash(re, im);
    }

    @Override
    public String toString() {
        return re + (im < 0 ? String.valueOf(im) : ("+" + im)) + "i";
    }

    private static boolean doubleEquals (double x, double y) {
        return Math.abs(x - y) < EPS;
    }

    public static void main(String[] args) {
        ComplexNumber first = new ComplexNumber(-1, 1);
        ComplexNumber second = new ComplexNumber(-1, -2);
        System.out.println(first == second);
        System.out.println(first.equals(second));
        System.out.println(first.plus(second)); // -2
        System.out.println(ComplexNumber.multiply(first, second)); // 0
    }
}