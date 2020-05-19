package ru.mail.polis.patterns.behavioral.visitor;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CommanderTest extends UnitTest<Commander> {
    /**
     * Create a new test instance for the given {@link Commander}
     */
    public CommanderTest() {
        super(Commander::new);
    }

    @Override
    void verifyVisit(Commander unit, UnitVisitor mockedVisitor) {
        verify(mockedVisitor, never()).visitCommander(eq(unit));
        inOrder(mockedVisitor).verify(mockedVisitor).visitCommander(eq(unit));
    }


}