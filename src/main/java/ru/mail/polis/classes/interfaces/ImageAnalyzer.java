package ru.mail.polis.classes.interfaces;

public interface ImageAnalyzer {

    default boolean analyze(String image) {
        System.out.println("Image analyze [" + image + "]");
        return true;
    }

}
