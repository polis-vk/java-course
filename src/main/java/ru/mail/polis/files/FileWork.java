package ru.mail.polis.files;

import java.io.File;

public class FileWork {

    public static void main(String[] args) {
        File file = new File("src" + File.separator + "java");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
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
