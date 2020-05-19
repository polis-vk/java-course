package ru.mail.polis.patterns.creational.builder;

public enum Armor {

    CLOTHES("clothes"),
    LEATHER("leather"),
    CHAIN_MAIL("chain mail"),
    PLATE_MAIL("plate mail");

    private String title;

    private Armor(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

