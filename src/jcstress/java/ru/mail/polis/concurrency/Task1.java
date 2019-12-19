package ru.mail.polis.concurrency;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class Task1 {


    public void threadP1() {
        originalContainer.setValue(true);
        updateValue();
    }

    public void threadQ1() {
        originalContainer.setValue(false);
        updateValue();
    }

    public void threadP2() {
        timerContainer.setValue(true);
        updateValue();
    }

    public void threadQ2() {
        timerContainer.setValue(false);
        updateValue();
    }

    public void threadP3() {
        timerIsOn = true;
        updateValue();
    }

    public void threadQ3() {
        timerIsOn = false;
        updateValue();
    }

    AtomicContainer originalContainer = new AtomicContainer();
    AtomicContainer timerContainer = new AtomicContainer();
    boolean timerIsOn;
    AtomicBoolean resultValue;
    Consumer<Boolean> listener;

     synchronized void updateValue() {
        Boolean enabledValue = originalContainer.getValue();
        boolean newValue = false;
        if (enabledValue != null && enabledValue) {
            Boolean timerEnabledValue = timerContainer.getValue();
            newValue = timerEnabledValue == null || !timerEnabledValue || timerIsOn;
        }
        if (resultValue.compareAndSet(!newValue, newValue)) {
            listener.accept(resultValue.get());
        }
    }

    AtomicInteger counter = new AtomicInteger();
    AtomicBoolean enabled = new AtomicBoolean();

     public void increment() {
         while (true) {
             if (this.enabled.compareAndSet(true, false)) {
                 changeProperty();
                 this.enabled.set(true);
                 return;
             }
         }

     }

    private void changeProperty() {

    }
}
