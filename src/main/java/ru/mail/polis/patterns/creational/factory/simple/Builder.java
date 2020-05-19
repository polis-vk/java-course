package ru.mail.polis.patterns.creational.factory.simple;

import java.util.function.Supplier;

/**
 * Functional interface that allows adding builder with name to the factory.
 */
@FunctionalInterface
public interface Builder {
    void add(WeaponType name, Supplier<Weapon> supplier);
}
