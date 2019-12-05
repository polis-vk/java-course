package ru.mail.polis.iostreams;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class InputOutputStreams {

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        int totalBytes = 0;
        byte[] buffer = new byte[1024];
        int blockSize;
        do {
            blockSize = inputStream.read(buffer);
            outputStream.write(buffer, 0, blockSize);
            totalBytes += blockSize;
        } while (blockSize > 0);
        outputStream.flush();
        return totalBytes;
    }


    public static void socketStreams() throws IOException {
        try (Socket socket = new Socket("ya.ru", 80)) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("GET / HTTP/1.0\r\n\r\n".getBytes());
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            int read = inputStream.read();
            while (read >= 0) {
                System.out.print((char) read);
                read = inputStream.read();
            }
        }
    }

    public static void readRate(InputStream stream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
        DataInputStream inputStream = new DataInputStream(bufferedInputStream);
        String name;
        int rate;
        while (inputStream.available() > 0) {
            name = inputStream.readUTF();
            rate = inputStream.readInt();
            System.out.println(name + " " + rate);
        }
    }
}
