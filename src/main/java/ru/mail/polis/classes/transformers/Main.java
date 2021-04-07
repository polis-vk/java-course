package ru.mail.polis.classes.transformers;

public class Main {

    public static void main(String[] args) {
        Weapon rocket = new Weapon("rocket", 1, 100);
        AutoBot prime = new AutoBot("Optimus Prime", 4500, rocket);
        System.out.println("First step: " + prime);

        prime.go();
        prime.attack();
        System.out.println("Second step: " + prime);

        prime.attack();
        System.out.println("third step: " + prime);

        prime.go();
        prime.setWeapon(new Weapon("laser", 100000, 10));
        prime.attack();
        System.out.println("fourth step: " + prime);

        prime.transform();
        System.out.println("fifth step: " + prime);

        prime.go();
        System.out.println("sixth step: " + prime);

        action(prime);
        action(new Deciptecon("Megatron", 3000, rocket));
        action(new AutoBotChild("Prime child", 1000, rocket));

    }

    public static int action(Transformer transformer) {
        System.out.println("-----=-----");
        transformer.go();
        int result  = transformer.attack();
        transformer.transform();
        transformer.go();
        result += transformer.attack();
        return result;
    }
}
