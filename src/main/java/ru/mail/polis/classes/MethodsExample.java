package ru.mail.polis.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class MethodsExample {

    //  ---------- initialization ----------

//    public static void main(String[] args) {
//        new MethodsExample("Example", 1);
//        int a = 3;
//        System.out.println("---------");
//        new MethodsExample(2);
//    }

    static {
        System.out.println("Static init 1: ");
    }


    {
//        System.out.println(name);
        System.out.println("init 1");
    }
    private String name = "Irina";
    public static String nameStatic = "Egor";

    {
        System.out.println("init 2: " + name);
    }

    private final int id;
    private  int id2;
    private  int id3;
    private  int id4;


    public int getId() {
        return id;
    }

    public int getId2() {
        return id2;
    }

    public int getId3() {
        return id3;
    }

    public int getId4() {
        return id4;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public void setId3(int id3) {
        this.id3 = id3;
    }

    public void setId4(int id4) {
        this.id4 = id4;
    }

    public MethodsExample(String name, int id) {
        this.name = name;
        this.id = id;
        System.out.println("init 3: " + this.name);
    }

    static {
        System.out.println("Static init 2: " + nameStatic);
    }

    public MethodsExample(int id) {
        this("default", id);
        System.out.println("init 4: " + this.name);
    }

    //  ---------- method ----------
    public static final void bigMethod(String name, MethodsExample example) throws IOException {
        //doNothing
        print((short)1);
        print((short)1);
        print(1d);
    }

    //  ---------- Overloading and casting ----------

    public static void main(String[] args) {
        print(123L);
        print(123);
        print((byte) 123);
        print(123F);


//        print((short) 123, 123);
//        print(123, 123);
//        print(123, 123);
//        print(123L, 123);
    }

    public static void print(short a) {
        System.out.println("short arg: " + a);
    }

//    public static void print(long a, int b) {
//        System.out.println("int int arg: " + a + b);
//    }

    public static void print(int a, Long b) {
        System.out.println("long long arg: " + a + b);
    }


    public static void print(long a) {
        System.out.println("long arg: " + a);
    }

    public static void print(double a) {
        System.out.println("double arg: " + a);
    }

    //  ---------- static ----------

    public static final double PI = 3.14159265358979323846;

//    public static void main(String[] args) {
//        System.out.println("Pi = " + PI);
//        System.out.println("Pi = " + MethodsExample.PI);
//        System.out.println("Pi = " + new MethodsExample().PI);
//    }

    //  ---------- final ----------


    public final void changeId(final int id) {
//        this.id = id;
        final int temp = id;
//        id = 2;
//        temp = 3;
        List<String> list = new ArrayList<>();
        for (String s : list) {

        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
