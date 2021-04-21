package ru.mail.polis.classes.livecoding.transport;

import java.util.List;

public class Road implements TransportNode {

    private final int length;
    private final double maxVelocity;
    private final int capacity;

    public Road(int length, double maxVelocity, int capacity) {
        this.length = length;
        this.maxVelocity = maxVelocity;
        this.capacity = capacity;
    }

    public int getLength() {
        return length;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public double getFunctionVelocity(int transportCount) {
        return transportCount <= capacity ? maxVelocity : maxVelocity * capacity / transportCount;
    }

    @Override
    public double calculateTime(List<Transport> transports) {
        double velocity = Math.min(getFunctionVelocity(transports.size()), transports.get(0).getVelocity());
        return length / velocity;
    }
}
