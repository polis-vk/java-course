package ru.mail.polis.classes;

public class ExtendedClasses {


    // ---------- extends ----------
    static class SuperClass {

        private final String surname;
        protected final String name;

        protected SuperClass(String surname, String name) {
            this.surname = surname;
            this.name = name;
        }

        public String myName() {
            return name + " " + surname;
        }

        protected SuperClass itIsMe(SuperClass s) {
            return this;
        }
    }

    static class SubClass extends SuperClass {
        private final int age;

        protected SubClass(String surname, String name, int age) {
            super(surname, name);
            this.age = age;
        }


        @Override
        public String myName() {
            return super.myName() + " " + age;
        }

        // @Override -- method doesn't override anything
        public String myName(String appeal) {
            return appeal + " " + myName();
        }

        protected SubClass itIsMe(Object s) {
            return this;
        }
    }

//    public static void main(String[] args) {
//        SuperClass i = new SubClass("Galkin", "Alexander", 27);
//        System.out.println(i.itIsMe(i).myName());
//        System.out.println( ((SubClass) i.itIsMe(i)).myName("Mr."));
//    }

    // ---------- abstract ----------

    public interface Speaking {
        void say();

        default void say(String str) {
            System.out.println(str);
        }
    }

    public static abstract class Pet implements Speaking {

        protected String name;
        protected int age;

        protected Pet(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public abstract String sayAndReturn();
    }

    static class Cat extends Pet {

        public Cat(String name, int age) {
            super(name, age);
        }

        @Override
        public String sayAndReturn() {
            String meow = "Meow! ";
            System.out.print(meow);
            return meow;
        }

        @Override
        public void say() {
            System.out.println("Meow!");
        }

//        @Override
//        public void say(String str) {
//            System.out.print(str);
//            say();
//        }

        public void kyc() {
            System.out.println("Кусь");
        }
    }

//    public static void main(String[] args) {
//        Cat cat = new Cat("Menson", 3);
//        cat.say();
//        System.out.println("return : " + cat.sayAndReturn());
//        cat.say("Мур ");
//        cat.kyc();
//
//        System.out.println("--------------");
//
//        Pet pet = new Cat("Barsik", 5);
//        pet.say();
//        System.out.println("return : " + pet.sayAndReturn());
//        pet.say("Мур ");
////        pet.kyc();
//
//        System.out.println("--------------");
//
//        Speaking speaking = new Cat("Barsik", 5);
//        speaking.say();
////        System.out.println("return : " + speaking.sayAndReturn());
//        speaking.say("Мур ");
////        speaking.kyc();
//
//    }

    // ----------  Anonymous ----------

    private static void anon(Runnable runnable) throws InterruptedException {
        Thread t = new Thread(runnable);
        t.start();
        Thread.sleep(1000);
    }


    public static void main(String[] args) throws InterruptedException {
        String str1 = "effectively final";
        String str2 = "not final";
        anon(new Runnable() {
            @Override
            public void run() {
                System.out.println(str1);
                System.out.println("Thread is running");
                System.out.println(str2);
            }
        });
        System.out.println(" ----------- ");

        anon(ExtendedClasses::printText);
        anon(() -> ExtendedClasses.printText(str1, "Thread is running", str2));
//        str2 = "not final 2";
    }

    private static void printText() {
        System.out.println("effectively final");
        System.out.println("Thread is running");
    }

    private static void printText(String s1, String main, String s2) {
        System.out.println(s1);
        System.out.println(main);
        System.out.println(s2);
    }
}
