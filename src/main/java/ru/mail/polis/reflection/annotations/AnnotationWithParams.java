package ru.mail.polis.reflection.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

enum Gender { MALE, FEMALE }

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AnnotationWithParams {
    String value();
    int number() default 0;
    double[] numbers();
    Gender gender() default Gender.FEMALE;
    Class<? extends Number> clazz() default Integer.class;
}

@AnnotationWithParams(value = "Hello", numbers = {1.0, 2.0}, clazz = Double.class)
class MyClass {
}

