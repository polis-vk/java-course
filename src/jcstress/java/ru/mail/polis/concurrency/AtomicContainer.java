package ru.mail.polis.concurrency;

public class AtomicContainer {

    private Boolean value;

    public synchronized Boolean getValue() {
        return value;
    }

    public synchronized boolean setValue(Boolean value) {
        if (value == null) {
            if (this.value == null) {
                return false;
            }
        } else {
            if (value.equals(this.value)) {
                return false;
            }
        }
        this.value = value;
        return true;
    }
}
