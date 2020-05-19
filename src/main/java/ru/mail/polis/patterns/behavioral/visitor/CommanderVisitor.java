package ru.mail.polis.patterns.behavioral.visitor;


/**
 * CommanderVisitor
 */
public class CommanderVisitor implements UnitVisitor {


    @Override
    public void visitSoldier(Soldier soldier) {
        // Do nothing
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        // Do nothing
    }

    @Override
    public void visitCommander(Commander commander) {
        System.out.println("Good to see you " + commander);
    }
}
