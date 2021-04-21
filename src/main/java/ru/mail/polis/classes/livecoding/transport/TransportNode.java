package ru.mail.polis.classes.livecoding.transport;

import java.util.List;

public interface TransportNode {

    double calculateTime(List<Transport> transports);
}
