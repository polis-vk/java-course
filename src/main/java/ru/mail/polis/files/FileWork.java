package ru.mail.polis.files;

import java.io.File;
import java.io.IOException;

public class FileWork {

    public static void main(String[] args) throws IOException {
        File file = new File("src" + File.separator + "java" + File.separator + "java");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        File file1 = new File("src" + File.separator + "java" + File.separator + "file1");
        file1.createNewFile();
        file1  = new File("src" + File.separator + "java" + File.separator + "file2.txt");
        file1.createNewFile();

        System.out.println(removeDirectory(new File("src" + File.separator + "java")));

    }

    public static int removeDirectory(File file) {
        if (file == null || !file.exists()) {
            return -1;
        }
        if (file.isFile()) {
            file.delete();
            return 1;
        }
        int result = 0;
        for (File temp : file.listFiles()) {
            result += removeDirectory(temp);
        }
        file.delete();
        return result + 1;
    }
}
