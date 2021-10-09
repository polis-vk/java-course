package ru.mail.polis.oop.examples.inheritance.statics;

class Parent {
    public static String greeting() {
        return "Hello";
    }

    public String name() {
        return "William";
    }
}

class Child extends Parent {
    public static String greeting() {
        return "Hi";
    }

    public String name() {
        return "Bill";
    }
}

public class StaticMethodsExample {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        Parent childAsParent = child;

        System.out.println(parent.greeting() + ", " + parent.name()); // Hello, William
        System.out.println(child.greeting() + ", " + child.name()); // Hi, Bill
        System.out.println(childAsParent.greeting() + ", " + childAsParent.name()); // Hello, Bill
        System.out.println(Child.greeting() + ", " + childAsParent.name()); // Hi, Bill
    }
}

