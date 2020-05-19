package ru.mail.polis.patterns.structural.decorator;


/**
 * SimpleTroll implements {@link Troll} interface directly.
 */
public class SimpleTroll implements Troll {


    @Override
    public void attack() {
        System.out.println("The troll tries to grab you!");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        System.out.println("The troll shrieks in horror and runs away!");
    }
}
