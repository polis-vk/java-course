package ru.mail.polis.classes.transformers;

public class Deciptecon extends Transformer {

    public Deciptecon(String name, int age, Weapon weapon) {
        super(name, age, weapon);
    }

    @Override
    public void transform() {
        if (transformationType == TransformationType.HUMAN) {
            transformationType = TransformationType.PLANE;
        } else {
            transformationType = TransformationType.HUMAN;
        }
        System.out.println("Transformation to " + transformationType);

    }
}
