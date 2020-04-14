package ru.mail.polis.classes;

import java.util.Arrays;

public class ArraysAndStrings {

    // ---------- arrays ----------

    private static void arraysWork(int n) {
        int[] array1 = new int[n];
        int[] array2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        array1[0] = array2[0] + array2[3];
//        try {
            int elevenElement = array1[array2.length];
            System.out.println(elevenElement);
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("array1.length < " + (array2.length + 1));
//        }
    }

    private static void forEachArrays() {
        int[] array = {3, 5, 7, 3, 7, 3, 9, 3};
        Arrays.sort(array);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        String result = sb.toString();
        System.out.println(result);

//        for (int element : array) {
//            System.out.println(element);
//        }
    }

    public static void main(String[] args) {
//        arraysWork(3);
        forEachArrays();
    }

    // ---------- String init ----------


    private static void initString() {
        String s1 = null;
        String s2 = "I am String";
        String s3 = new String("I am String");
        String s4 = s2 + s3;
        String s5 = "I" +  " am" + " String";
        String s6 = new String(new char[] {'i', ' ', 'a', 'm', ' ', 'S', 't', 'r', 'i', 'n', 'g'});

    }

    // ---------- String concat ----------

    private static void concatString() {
//        String s1 = "str" + "ing";
//        System.out.println(s1);
//        System.out.println("str" + 100 + false);
//        System.out.println("str" + 100 + 50);
//        System.out.println(100 + 50 + "str");
//        System.out.println(100 + 50 + "str" + 100 + 50);
//        System.out.println("str".concat("ing"));

        System.out.println("str" == "str");
        System.out.println("str" == new String("str"));
        System.out.println("str" == new String(new char[] {'s', 't', 'r'}));



    }

//    public static void main(String[] args) {
//        concatString();
//    }

    // ---------- String methods ----------

    private static void methodsString() {
        String s1 = "s1 is String";
        String s2 = s1.toUpperCase();
        System.out.println("upper: " + s2);
        String s3 = s1.substring(3, 5);
        System.out.println("sub: " + s3);
        String s4 = s1.replace(' ', '|');
        System.out.println("replace: " + s4);
        int i = s1.indexOf('S');
        System.out.println("index: " + i);
        char c = s1.charAt(i);
        System.out.println("char: " + c);;

    }

//    public static void main(String[] args) {
//        methodsString();
//    }

}
