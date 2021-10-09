package ru.mail.polis.oop.examples.inheritance;

class Animal {
    void move() {
        System.out.println("move");
    }
}

class Cat extends Animal {
    @Override
    void move() {
        run();
    }

    private void run() {
        System.out.println("run");
    }
}

public class OverridingExample {

    public static void main(String[] args) {
        Animal animal = new Animal();
        Cat cat = new Cat();
        Animal catAsAnimal = cat;

        animal.move(); // move
        cat.move(); // run
        catAsAnimal.move(); // run
    }
}

