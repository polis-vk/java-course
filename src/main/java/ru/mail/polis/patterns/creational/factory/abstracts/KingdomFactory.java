package ru.mail.polis.patterns.creational.factory.abstracts;

/**
 * KingdomFactory factory interface.
 */
public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();

}
