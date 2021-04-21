package ru.mail.polis.classes.livecoding.transport;


import java.util.ArrayList;
import java.util.List;

public class Analyzer {

    public static double analyze(Route route, List<Human> humans, List<Transport> transportList) {
        int transportHumanCount = 0;
        List<Transport> transports = new ArrayList<>(transportList);
        for (Transport transport : transports) {
            transportHumanCount += transport.getMaxHumanCount();
        }
        int i = 0;
        while (transportHumanCount < humans.size()) {
            transports.add(humans.get(i++));
            transportHumanCount++;
        }

        transports.sort((transport1, transport2) -> {
            if (transport1.getVelocity() < transport2.getVelocity()) {
                return -1;
            }
            return transport1.getVelocity() == transport2.getVelocity() ? 0 : 1;
        });

        double time = 0;
        for (TransportNode node : route.getRoads()) {
            time += node.calculateTime(transports);
        }
        return time;
    }
}
