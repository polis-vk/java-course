package ru.mail.polis.oop.examples.interfaces;

interface Printable {
    String print();
}

class Control {
    private final int id;

    public Control(int id) {
        this.id = id;
    }
}

class Label extends Control implements Printable {
    private final String label;

    public Label(int id, String label) {
        super(id);
        this.label = label;
    }

    @Override
    public String print() {
        return label;
    }
}

public class InterfaceExample {
    public static void main(String[] args) {
        Printable printable = new Label(10, "Hello, world!");
        System.out.println(printable.print());
    }
}

