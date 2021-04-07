package ru.mail.polis.classes.interfaces;

import java.util.Random;

public class SpamAnalyzer implements ImageAnalyzer, TextAnalyzer {

    Random rnd = new Random();

    @Override
    public boolean analyze(String text) {
        System.out.println("Spam analyze [" + text + "]");
        return rnd.nextBoolean();
    }

    public static void main(String[] args) {
        ImageAnalyzer imageAnalyzer = new SpamAnalyzer();
//        TextAnalyzer textAnalyzer = new SpamAnalyzer();
        SpamAnalyzer spamAnalyzer = new SpamAnalyzer();

        System.out.println(imageAnalyzer.analyze("img"));
//        System.out.println(textAnalyzer.analyze("text"));
        System.out.println(spamAnalyzer.analyze("spam"));
    }
}
