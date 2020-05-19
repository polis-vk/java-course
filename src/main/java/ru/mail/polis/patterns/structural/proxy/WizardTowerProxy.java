package ru.mail.polis.patterns.structural.proxy;

/**
 * The proxy controlling access to the {@link IvoryTower}.
 */
public class WizardTowerProxy implements WizardTower {


    private static final int NUM_WIZARDS_ALLOWED = 3;
    private final WizardTower tower;
    private int numWizards;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUM_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            System.out.println(wizard + " is not allowed to enter!");
        }
    }
}
