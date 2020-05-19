package ru.mail.polis.patterns.creational.factory.abstracts;

/**
 * OrcArmy
 */
public class OrcArmy implements Army {

    static final String DESCRIPTION = "This is the Orc Army!";

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
