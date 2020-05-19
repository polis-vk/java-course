package ru.mail.polis.patterns.behavioral.temlate_method;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class HalflingThiefTest {

    @Mock
    private StealingMethod method;

    /**
     * Verify if the thief uses the provided stealing method
     */
    @Test
    public void testSteal() {
//        final StealingMethod method = mock(StealingMethod.class);
        final HalflingThief thief = new HalflingThief(method);

        thief.steal();
        verify(method).steal();
        verifyNoMoreInteractions(method);
    }

    /**
     * Verify if the thief uses the provided stealing method, and the new method after changing it
     */
    @Test
    public void testChangeMethod() {
        final StealingMethod initialMethod = mock(StealingMethod.class);
        final HalflingThief thief = new HalflingThief(initialMethod);

        thief.steal();
        verify(initialMethod).steal();

        final StealingMethod newMethod = mock(StealingMethod.class);
        thief.changeMethod(newMethod);

        thief.steal();
        verify(newMethod).steal();

        verifyNoMoreInteractions(initialMethod, newMethod);

    }
}