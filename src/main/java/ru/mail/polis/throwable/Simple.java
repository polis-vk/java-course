package ru.mail.polis.throwable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Simple {
    public static void tryWithResources() throws IOException {
        try (InputStream is = new FileInputStream("a.txt")) {
            readFromInputStream(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tryMethod() throws IOException {
        InputStream is = null;
        try {
            is = new FileInputStream("a.txt");
            readFromInputStream(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            is.close();
        }
    }

    private static void readFromInputStream(InputStream is) throws IOException {
    }
}
