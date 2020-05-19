package ru.mail.polis.patterns.behavioral.temlate_method;

/**
 *
 * Halfling thief uses {@link StealingMethod} to steal.
 *
 */
public class HalflingThief {

    private StealingMethod method;

    public HalflingThief(StealingMethod method) {
        this.method = method;
    }

    public void steal() {
        method.steal();
    }

    public void changeMethod(StealingMethod method) {
        this.method = method;
    }
}
