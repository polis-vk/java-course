package ru.mail.polis.reflection;

import java.io.Serializable;

public class ByteCodeExample implements Serializable {

    public static class Foo {
        final int id;
        int j = 12;

        public Foo(int id) {
            this.id = id;
        }


        int sum(int a) {
            return id + j + a;
        }
    }

    public static void main(String[] args) {
        ByteCodeExample temp = new ByteCodeExample();
        temp.setName("My");
        String result = temp.getName() + " " + temp.getCode();
        System.out.println(result);

        System.out.println("---------");

        int res = new Foo(10).sum(3);
        System.out.println(res);
    }

    public static final ByteCodeExample instance = new ByteCodeExample();

    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
