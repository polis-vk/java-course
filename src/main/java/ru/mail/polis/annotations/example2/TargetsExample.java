package ru.mail.polis.annotations.example2;

import java.util.List;

import ru.mail.polis.annotations.example2.annotations.AnnotationAnnotation;
import ru.mail.polis.annotations.example2.annotations.ConstructorAnnotation;
import ru.mail.polis.annotations.example2.annotations.FieldAnnotation;
import ru.mail.polis.annotations.example2.annotations.LocalAnnotation;
import ru.mail.polis.annotations.example2.annotations.MethodAnnotation;
import ru.mail.polis.annotations.example2.annotations.ParamAnnotation;
import ru.mail.polis.annotations.example2.annotations.TypeAnnotation;
import ru.mail.polis.annotations.example2.annotations.TypeParamAnnotation;
import ru.mail.polis.annotations.example2.annotations.TypeUseAnnotation;

@TypeAnnotation
public class TargetsExample {
    @TypeAnnotation interface SomeInterface {}
    @AnnotationAnnotation @interface SomeAnnotation {}

    @FieldAnnotation int x;

    @ConstructorAnnotation TargetsExample(@ParamAnnotation int x) {
        this.x = x;
    }

    @MethodAnnotation <@TypeParamAnnotation T> void method(@ParamAnnotation T param) {
        @LocalAnnotation T localVariable = param;
        @LocalAnnotation int y = 42;
    }

    void getIntegerArrays(@TypeUseAnnotation List<@TypeUseAnnotation Integer @TypeUseAnnotation []> x) { }
}

