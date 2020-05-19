package ru.mail.polis.classes.lib;

public class Customer extends People {

    private final String peopleId;

    public Customer(String name, int age, String peopleId) {
        super(name, age);
        this.peopleId = peopleId;
    }

    public String getPeopleId() {
        return peopleId;
    }
}
