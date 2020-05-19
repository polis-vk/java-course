package ru.mail.polis.patterns.creational.factory.abstracts;

/**
 * ElfCastle
 */
public class ElfCastle implements Castle {

    static final String DESCRIPTION = "This is the Elven castle!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
