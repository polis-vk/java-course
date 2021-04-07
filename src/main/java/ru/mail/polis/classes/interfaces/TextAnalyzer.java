package ru.mail.polis.classes.interfaces;

public interface TextAnalyzer {
    default boolean analyze(String text) {
        System.out.println("Text analyze [" + text + "]");
        return true;
    }
}
