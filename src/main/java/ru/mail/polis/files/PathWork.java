package ru.mail.polis.files;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class PathWork {

    public static long directoryCount(Path dir) throws IOException {
        long count = 0;
        try (DirectoryStream<Path> directoryStream =
                     Files.newDirectoryStream(dir, path -> path.startsWith("polis"));
             Reader reader = Files.newBufferedReader(Paths.get("sdsd"))) {
            for (Path directory : directoryStream) {
                count++;
            }
        }

        try (DirectoryStream<Path> directoryStream =
                     Files.newDirectoryStream(dir, path -> path.startsWith("polis"))) {
            try (Reader reader = Files.newBufferedReader(Paths.get("sdsd"))) {

            }
        }

        DirectoryStream<Path> directoryStream = null;

        try {
            directoryStream = Files.newDirectoryStream(dir, path -> path.startsWith("polis"));
            for (Path directory : directoryStream) {
                count++;
            }
        } finally {
            if (directoryStream != null) {
                try {
                    directoryStream.close();
                } catch (IOException e) {

                }
            }
        }

        return count;
    }

    public static long filesCount(Path dir) throws IOException {
        int count = 0;
        AtomicInteger atomicCount = new AtomicInteger();
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {

            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                atomicCount.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
//                count++;
                atomicCount.incrementAndGet();
                return FileVisitResult.CONTINUE;
            }
        });
//        return count;
        return atomicCount.get();
//        return 0;
    }
}
