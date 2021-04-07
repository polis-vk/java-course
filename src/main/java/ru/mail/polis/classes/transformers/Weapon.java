package ru.mail.polis.classes.transformers;

public class Weapon {

    private String name;
    private int count;
    private int defaultCount;
    private int force;

    public Weapon(String name, int count, int force) {
        this.name = name;
        this.count = count;
        this.defaultCount = count;
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public int getForce() {
        return force;
    }

    public int attack() {
        if (count <= 0) {
            reload();
        }
        System.out.println("Attack");
        count--;
        return force;
    }

    private void reload() {
        System.out.println("Start reload");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finish reload");
        count = defaultCount;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", force=" + force +
                '}';
    }
}
