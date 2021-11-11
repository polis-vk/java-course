package ru.mail.polis.reflection.annotations;

import java.util.Arrays;

public class AnnotationsExample {

    public static void main(String[] args) {
        Arrays.stream(X.class.getAnnotations()).forEach(System.out::println);

        System.out.println(X.class.isAnnotationPresent(AnnotationWithParams.class));

        AnnotationWithParams annotation = X.class.getAnnotation(AnnotationWithParams.class);
        System.out.println(annotation);
        System.out.println(annotation.value());
        System.out.println(annotation.number());
        System.out.println(Arrays.toString(annotation.numbers()));
    }

    @AnnotationWithParams(value = "Hello", numbers = {1.0, 2.0}, clazz = Double.class)
    static class X {

    }
}
