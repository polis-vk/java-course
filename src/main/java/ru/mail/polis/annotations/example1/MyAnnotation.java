package ru.mail.polis.annotations.example1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.FIELD})
@interface MyAnnotation {
}

@MyAnnotation
class MyClass {
    @MyAnnotation
    private int x;
}

