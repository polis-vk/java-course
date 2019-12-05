package ru.mail.polis.iostreams.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerExample {

    private static final Logger log = Logger.getLogger(LoggerExample.class.getName());

    public static void main(String[] args) {
        log.fine("Start program");

        log.fine("Start argumentsInfo");
        argumentsInfo("Hello, world!", 42d);
        log.fine("Finish argumentsInfo");

        log.fine("Start maybeException");
        try {
            maybeException();
        } catch (IllegalArgumentException e) {
            log.log(Level.SEVERE, "Exception caught ", e);
            System.exit(2);
        }
        log.fine("Finish maybeException");
    }

    public static void argumentsInfo(String first, double second) {
        log.log(Level.INFO, "method argumentsInfo with arg1 = " + first + ", arg2 = " + second);
        log.log(Level.INFO, "method argumentsInfo with arg1 = {0}, arg2 = {1}", new Object[] {first, second});
    }

    public static void maybeException() {
        double random = Math.random();
        log.log(Level.FINE, "Generated random number: {0}", random);
        if (random < 0.5) {
            throw new IllegalArgumentException("Invalid phase of the Moon");
        }
    }
}
