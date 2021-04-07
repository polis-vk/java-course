package ru.mail.polis.classes.transformers;

public abstract class Transformer {
    private String name;
    private int age;
    protected Point position = new Point();
    private Weapon weapon;
    protected TransformationType transformationType = TransformationType.HUMAN;

    public Transformer(String name, int age, Weapon weapon) {
        this.name = name;
        this.age = age;
        this.weapon = weapon;
    }

    public Transformer(String name, int age, Weapon weapon, Point point) {
        this.name = name;
        this.age = age;
        this.weapon = weapon;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Point getPosition() {
        return new Point(position);
    }

    public abstract void transform();

    public Point go() {
        System.out.println("Go");
        position.x++;
        return getPosition();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public int attack() {
        return weapon.attack();
    }

    @Override
    public String toString() {
        return "Transformer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", position=" + position +
                ", weapon=" + weapon +
                ", transformationType=" + transformationType +
                '}';
    }

    public class Point {
        protected int x;

        Point() {
        }

        Point(Point other) {
            this.x = other.x;
        }

        @Override
        public String toString() {
            return "x=" + x + '}';
        }
    }
}
