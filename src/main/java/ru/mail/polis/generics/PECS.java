package ru.mail.polis.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PECS {

    static class Animal {
        void feed() {}
    }

    static class Pet extends Animal {
        void call() {}
    }

    static class Kitty extends Pet {
        void mew() {}
    }

    static class Dog extends Pet {
        void bark() {}
    }

    public static void main(String[] args) {
        // 1 вариант (можно T -> T)
        List<Pet> srcPet = Arrays.asList(new Pet(), new Kitty());
        List<Pet> destPet = new ArrayList<>();
        Collections.copy(destPet, srcPet);
        for (Pet p: destPet) {
            p.call();
        }

        // 2 вариант (можно ? extends T -> T)
        List<Kitty> srcKitty = Arrays.asList(new Kitty(), new Kitty());
        destPet = new ArrayList<>();
        Collections.copy(destPet, srcKitty);
        for (Pet p: destPet) {
            p.call();
        }

        // 3 вариант: нельзя ? extends T -> ? extends T;
//        List<Dog> destDog = new ArrayList<>();
//        Collections.copy(destDog, srcKitty);
//        for (Dog d: destDog) {
//            d.bark();
//        }

        // 4 вариант ? extends T -> ? super T
        List<Animal> animalDest = new ArrayList<>();
        Collections.<Pet>copy(animalDest, srcKitty);
        for(Animal a: animalDest) {
            a.feed();
        }

    }
}
