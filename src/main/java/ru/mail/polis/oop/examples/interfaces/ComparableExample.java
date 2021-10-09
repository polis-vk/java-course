package ru.mail.polis.oop.examples.interfaces;

import java.util.Arrays;

class Person implements Comparable<Person> {
    private final String firstname;
    private final String lastname;

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public int compareTo(Person other) {
        int lastnameResult = lastname.compareTo(other.lastname);
        return lastnameResult != 0 ? lastnameResult : firstname.compareTo(other.firstname);
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}

public class ComparableExample {
    public static void main(String[] args) {
        Person[] people = new Person[]{
                new Person("John", "Doe"), new Person("Ann", "Doe"),
                new Person("Dorian", "Grey"), new Person("Ann", "Grey")
        };
        Arrays.sort(people);
        System.out.println(Arrays.toString(people)); // [Ann Doe, John Doe, Ann Grey, Dorian Grey]
    }
}
