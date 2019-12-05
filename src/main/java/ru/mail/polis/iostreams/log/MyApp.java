package ru.mail.polis.iostreams.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp {
    final static Logger log = LoggerFactory.getLogger(MyApp.class);

    public static void main(String[] args) {
        log.info("Entering application.");

        Foo foo = new Foo();
        foo.doIt();
        log.info("Exiting application.");
    }
}
