package ru.mail.polis.generics;

public class ContainerNew<T> {

    private T element;

    public ContainerNew(T newElement) {
        this.element = newElement;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T newElement) {
        this.element = newElement;
    }
}
