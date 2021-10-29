package ru.mail.polis.iostreams;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ObjectStreams {


    public static void writePeople(DataOutputStream out, People p) throws IOException {
        out.writeInt(p.getAge());
        out.writeUTF(p.getLastName());
        writeDog(out, p.dog);
        out.writeUTF(p.getFirstName());
    }

    public static void writeDog(DataOutputStream out, Dog p) throws IOException {
        out.writeUTF(p.alias);
        out.writeUTF(p.breed);
    }

    public static People readPeople(DataInputStream out) throws IOException {
        return new People(out.readInt(), out.readUTF(), readDog(out), out.readUTF());

    }

    public static Dog readDog(DataInputStream out) throws IOException {
        return new Dog(out.readUTF(), out.readUTF());
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        People sergeev = new People(99, "Sergeev",
                new Dog("Mike", "German Shepherd"), "Vova");
        People breznev = new People(10, "Breznev",
                null, "Fedor");
        System.out.println("sergeev = " + sergeev);
//
        Path path = Paths.get("object.bin");
        try (ObjectOutputStream outputStream =
                     new ObjectOutputStream(Files.newOutputStream(path))) {
            outputStream.writeObject(sergeev);
//             writePeople(outputStream, sergeev);

        }

        People newSergeev1;
//        People newSergeev2;
        try (ObjectInputStream inputStream =
                     new ObjectInputStream(Files.newInputStream(path))) {
            newSergeev1 = (People) inputStream.readObject();
//            newSergeev2 = (People) inputStream.readObject();
//            (People) inputStream.readObject();
        }

        System.out.println("newSergeev1 = " + newSergeev1);
//        System.out.println("newSergeev2 = " + newSergeev2);
    }

    public static class HomoSapiens implements Externalizable {
        protected int age;
        protected String firstName;

        public HomoSapiens() {
        }

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
            System.out.println("!!!!!!!!");
        }

        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            age = in.readInt();
            System.out.println("---------");

            if (age > 100) {
                throw new IllegalArgumentException();
            }
            firstName = in.readUTF();
        }

    }

    public static class Animal implements Externalizable {
        protected String alias;

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

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(alias);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            alias = in.readUTF();
        }
    }

    public static class Dog extends Animal  {
        private String breed;

        public Dog() {
            super("Pes");
        }

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

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(breed);
            super.writeExternal(out);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            breed = in.readUTF();
            super.readExternal(in);
        }
    }

    public static class People extends HomoSapiens  {
        private String lastName;
        private Dog dog;
        private transient String sign;

//        private void writeObject(ObjectOutputStream out) throws IOException {
//            System.out.println("!!!!!");
//            out.writeUTF(lastName);
//            out.writeUTF(getFirstName());
//            out.writeInt(getAge());
//            out.writeObject(dog);
//        }
//
//
//        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//            lastName = in.readUTF();
//            firstName = in.readUTF();
//            age = in.readInt();
//            dog = (Dog) in.readObject();
//            sign = lastName + getFirstName();
//            if (age > 100) {
//                throw new IllegalArgumentException();
//            }
//
//        }

        public People() {
        }

        public People(int age, String lastName, Dog dog, String firstName) {
            super(age, firstName);
            this.lastName = lastName;
            this.dog = dog;
            sign = lastName + getFirstName();
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF(lastName);
            out.writeObject(dog);
            super.writeExternal(out);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            lastName = in.readUTF();
            dog = (Dog) in.readObject();
            super.readExternal(in);
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
                    ", firstName='" + getFirstName() + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", dog=" + dog +
                    ", sign='" + sign + '\'' +
                    '}';
        }
    }
}
