package ru.mail.polis.classes.livecoding.transport;

import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        System.out.println(Analyzer.analyze(
                new Route(Arrays.asList(
                        new Road(1233, 20, 6),
                        new Road(1000, 50, 6),
                        new Road(300, 30, 6),
                        new Road(20, 2, 6)
                )),
                Arrays.asList(
                        new Human(10, 1000),
                        new Human(6, 1000),
                        new Human(5, 1000),
                        new Human(8, 1000),
                        new Human(1, 1000)
                ),
                 Collections.emptyList()));
//                Arrays.asList(
//                        new Horse(30, 2),
//                        new Horse(30, 2)
//                        new Horse(30, 2)
//                )));
    }
}
