package ru.mail.polis.classes.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Generics {

    public static void containerNewFunction() {
        ContainerNew<String> container = new ContainerNew<>("str");
        String temp = container.getElement();
        System.out.println(temp);
    }

    public static void containerNewFunction2() {
        ContainerNew container = new ContainerNew("str");
        String temp = (String) container.getElement();
        System.out.println(temp);
    }

    public static void containerOldFunction() {
        ContainerOld container = new ContainerOld("str");
        String temp = (String) container.getElement();
        System.out.println(temp);
    }

    public static void main(String[] args) {
        List old = new ArrayList();
        old.add(1);
        old.add("two");
        Integer int00 = (Integer) old.get(0);
//        Integer int01 = (Integer) old.get(1); // runtime error!


        List<Integer> newList = new ArrayList<>();
        newList.add(1);
//        newList.add("two");
        Integer int10 = newList.get(0);
//        Integer int11 = newList.get(1);
    }


    private static <T> void restrictions() {
        Optional<String> stringOptional;
        Optional<Integer> integerOptional;
        Optional<int[]> inrArrayOptional;
//        Optional<int> intOptional;


//        T obj = new T();
//        T obj1 = new Object();
//        T[] array = new T[2];
//        boolean isT = (new Object()) instanceof T;
    }

    private static void invariance() {
        //ковариантность
        String[] strings = new String[] {"a", "b", "c"};
        Object[] arr = strings;

        //инвариантность
        List<Integer> ints = Arrays.asList(1,2,3);
//        List<Number> nums = ints;

        //ковариантность
        List<? extends Number> numsWithWildcard = ints;

        //контравариантность
        List<Number> nums = new ArrayList<>();
        List<? super Integer> intsWithWildcard = nums;

        //обратная совместимость
        List oldList = new ArrayList();
        List<String> stringList = new ArrayList<>();
        oldList = stringList;
    }


}
