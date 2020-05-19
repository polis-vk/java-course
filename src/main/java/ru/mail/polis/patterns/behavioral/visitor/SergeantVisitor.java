package ru.mail.polis.patterns.behavioral.visitor;


/**
 * SergeantVisitor
 */
public class SergeantVisitor implements UnitVisitor {


    @Override
    public void visitSoldier(Soldier soldier) {
        // Do nothing
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        System.out.println("Hello " + sergeant);
    }

    @Override
    public void visitCommander(Commander commander) {
        // Do nothing
    }
}
