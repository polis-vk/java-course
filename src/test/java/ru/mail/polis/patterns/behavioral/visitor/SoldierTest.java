package ru.mail.polis.patterns.behavioral.visitor;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SoldierTest extends UnitTest<Soldier> {

    /**
     * Create a new test instance for the given {@link Soldier}
     */
    public SoldierTest() {
        super(Soldier::new);
    }

    @Override
    void verifyVisit(Soldier unit, UnitVisitor mockedVisitor) {
        verify(mockedVisitor).visitSoldier(eq(unit));
    }

}