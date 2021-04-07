package ru.mail.polis.classes.transformers;

public class AutoBot extends Transformer {

    public AutoBot(String name, int age, Weapon weapon) {
        super(name, age, weapon);
    }

    @Override
    public Point go() {
        System.out.println("AutoBot go");
        if (transformationType == TransformationType.HUMAN) {
            return super.go();
        } else {
            position.x += 10;
            return getPosition();
        }
    }

    public void transform() {
        if (transformationType == TransformationType.HUMAN) {
            transformationType = TransformationType.AUTO;
        } else {
            transformationType = TransformationType.HUMAN;
        }
        System.out.println("Transformation to " + transformationType);

    }

}
