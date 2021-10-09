package ru.mail.polis.oop.livecoding;

import java.util.Objects;

class Person {
    private final long id;
    private final String firstname;
    private final String lastname;

    public Person(long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id
                && Objects.equals(firstname, person.firstname)
                && Objects.equals(lastname, person.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}

public class ObjectExample {
    public static void main(String[] args) {
        Person person1 = new Person(12L, "John", "Doe");
        Person person2 = new Person(12L, "John", "Doe");

        if (person1.equals(person2)) {
            System.out.println("equals");
        } else {
            System.out.println("not equals");
        }
    }
}
