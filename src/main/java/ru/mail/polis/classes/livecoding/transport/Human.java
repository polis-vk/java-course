package ru.mail.polis.classes.livecoding.transport;

public class Human implements Transport {
    private final double velocity;
    private final int workTime;

    public Human(double velocity, int workTime) {
        this.velocity = velocity;
        this.workTime = workTime;
    }

    public double getVelocity() {
        return velocity;
    }

    @Override
    public int getMaxHumanCount() {
        return 1;
    }

    public int getWorkTime() {
        return workTime;
    }
}
