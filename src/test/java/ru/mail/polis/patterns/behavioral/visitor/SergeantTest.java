package ru.mail.polis.patterns.behavioral.visitor;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SergeantTest extends UnitTest<Sergeant> {

    /**
     * Create a new test instance for the given {@link Sergeant}
     */
    public SergeantTest() {
        super(Sergeant::new);
    }

    @Override
    void verifyVisit(Sergeant unit, UnitVisitor mockedVisitor) {
        verify(mockedVisitor).visitSergeant(eq(unit));
    }
}