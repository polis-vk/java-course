package ru.mail.polis.classes.livecoding.transport;

import java.util.List;

public class Route {
    private final List<TransportNode> roads;

    public Route(List<TransportNode> roads) {
        this.roads = roads;
    }

    public List<TransportNode> getRoads() {
        return roads;
    }
}
