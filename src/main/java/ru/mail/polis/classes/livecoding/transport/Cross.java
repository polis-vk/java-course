package ru.mail.polis.classes.livecoding.transport;

import java.util.List;

public class Cross implements TransportNode {

    private final int stopTime;
    private final int driveTime;

    public Cross(int stopTime, int driveTime) {
        this.stopTime = stopTime;
        this.driveTime = driveTime;
    }

    @Override
    public double calculateTime(List<Transport> transports) {
        return stopTime + driveTime / (2 * transports.size());
    }
}
