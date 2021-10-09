package ru.mail.polis.oop.examples.inheritance;

class Entity {
    private final int id;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

class PersonRecord extends Entity {
    private final String name;
    private final int age;

    public PersonRecord(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class Simple {
    public static void main(String[] args) {
        PersonRecord record = new PersonRecord(123, "John Doe", 47);
        System.out.println(record.getId());
    }
}
