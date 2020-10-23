package ru.mail.polis.iostreams;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectStreams {


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        People sergeev = new People(99, "Sergeev",
                new Dog("Mike", "German Shepherd"), "Vova");
        System.out.println("sergeev = " + sergeev);
//
        Path path = Paths.get("object.bin");
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(sergeev);
//            sergeev.setDog(new Dog("Loki", "German Shepherd"));
//            System.out.println("sergeev = " + sergeev);
//            outputStream.writeObject(sergeev);
        }

        People newSergeev1;
//        People newSergeev2;
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))) {
            newSergeev1 = (People) inputStream.readObject();
//            newSergeev2 = (People) inputStream.readObject();
        }

        System.out.println("newSergeev1 = " + newSergeev1);
//        System.out.println("newSergeev2 = " + newSergeev2);
    }

    public static class HomoSapiens implements Externalizable {
        private int age;
        private String firstName;

        public HomoSapiens() {}

        public HomoSapiens(int age, String firstName) {
            if (age > 100) {
                throw new IllegalArgumentException();
            }
            this.age = age;
            this.firstName = firstName;
        }

        public int getAge() {
            return age;
        }

        public String getFirstName() {
            return firstName;
        }

        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(age);
            out.writeUTF(firstName);
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            age = in.readInt();
            if (age > 100) {
                throw new IllegalArgumentException();
            }
            firstName = in.readUTF();
        }
    }

    public static class Animal implements Serializable {
        private final String alias;

        public Animal(String alias) {
            this.alias = alias;
        }

        public String getAlias() {
            return alias;
        }

        @Override
        public String toString() {
            return "Animal{" +
                    "alias='" + alias + '\'' +
                    '}';
        }
    }

    public static class Dog extends Animal  {
        private final String breed;

        public Dog(String alias, String breed) {
            super(alias);
            this.breed = breed;
        }

        public String getBreed() {
            return breed;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "alias='" + getAlias() + '\'' +
                    ", breed='" + breed + '\'' +
                    '}';
        }
    }

    public static class People extends HomoSapiens implements Serializable {
        private String lastName;
        private Dog dog;

        public People() {
            super();
        }

        public People(int age, String lastName, Dog dog, String firstName) {
            super(age, firstName);
            this.lastName = lastName;
            this.dog = dog;
        }

        public String getLastName() {
            return lastName;
        }

        public Dog getDog() {
            return dog;
        }

        public void setDog(Dog dog) {
            this.dog = dog;
        }

        @Override
        public String toString() {
            return "People{" +
                    "age=" + getAge() +
                    ", lastName='" + lastName + '\'' +
                    ", firstName='" + getFirstName() + '\'' +
                    ", dog=" + dog +
                    '}';
        }
    }
}
