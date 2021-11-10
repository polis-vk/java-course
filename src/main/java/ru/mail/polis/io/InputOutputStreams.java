package ru.mail.polis.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

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

    public static void main(String[] args) throws IOException {
//        socketStreams();
        urlStream();
    }

    public static void socketStreams() throws IOException {


        try (Socket socket = new Socket("ya.ru", 80)) {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("GET / HTTP/1.1\nHost: ya.ru\n\n".getBytes());
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            int read = inputStream.read();
            while (read >= 0) {
                System.out.print((char) read);
                read = inputStream.read();
            }
        }
    }

    public static void urlStream() {
        URL url = null;
        BufferedReader br;
        String line;
        try {
            url = new URL("https://ya.ru");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (InputStream is = url.openStream()) {
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readRate(InputStream stream) throws IOException {
        DataInputStream inputStream = new DataInputStream(new BufferedInputStream(stream));
        String name;
        int rate;
        while (inputStream.available() > 0) {
            name = inputStream.readUTF();
            rate = inputStream.readInt();
            System.out.println(name + " " + rate);
        }
    }
}
