package ru.mail.polis.classes.livecoding.transport;

public class Horse implements Transport {

    private final double velocity;
    private final int maxHumanCount;
    private int humanCount;

    public Horse(double velocity, int maxHumanCount) {
        this.velocity = velocity;
        this.maxHumanCount = maxHumanCount;
    }

    public double getVelocity() {
        return humanCount == 1 ? velocity : velocity / 1.5;
    }

    public int getMaxHumanCount() {
        return maxHumanCount;
    }

    public void setHumanCount(int humanCount) {
        if (humanCount > maxHumanCount) {
            return;
        }
        this.humanCount = humanCount;
    }

    public int getHumanCount() {
        return humanCount;
    }
}
