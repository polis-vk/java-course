package ru.mail.polis.patterns.behavioral.strategy;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class DragonSlayerTest {

    /**
     * Verify if the dragon slayer uses the strategy during battle
     */
    @Test
    public void testGoToBattle() {
        final DragonSlayingStrategy strategy = mock(DragonSlayingStrategy.class);
        final DragonSlayer dragonSlayer = new DragonSlayer(strategy);

        dragonSlayer.goToBattle();
        verify(strategy).execute();
        verifyNoMoreInteractions(strategy);
    }

    /**
     * Verify if the dragon slayer uses the new strategy during battle after a change of strategy
     */
    @Test
    public void testChangeStrategy() {
        final DragonSlayingStrategy initialStrategy = mock(DragonSlayingStrategy.class);
        final DragonSlayer dragonSlayer = new DragonSlayer(initialStrategy);

        dragonSlayer.goToBattle();
        verify(initialStrategy).execute();

        final DragonSlayingStrategy newStrategy = mock(DragonSlayingStrategy.class);
        dragonSlayer.changeStrategy(newStrategy);

        dragonSlayer.goToBattle();
        verify(newStrategy).execute();

        verifyNoMoreInteractions(initialStrategy, newStrategy);
    }
}