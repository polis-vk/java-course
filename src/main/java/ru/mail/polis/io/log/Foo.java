package ru.mail.polis.io.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Foo {
    static final Logger log = LoggerFactory.getLogger(Foo.class);

    public void doIt() {
        log.debug("Did it again!");
    }
}
