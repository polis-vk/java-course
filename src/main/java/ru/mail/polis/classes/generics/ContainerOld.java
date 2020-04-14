package ru.mail.polis.classes.generics;

public class ContainerOld {

    private Object element;

    public ContainerOld(Object newElement) {
        this.element = newElement;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object newElement) {
        this.element = newElement;
    }
}
