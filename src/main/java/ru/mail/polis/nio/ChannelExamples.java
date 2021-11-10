package ru.mail.polis.nio;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChannelExamples {

    public static void main(String[] args) throws Exception {
//        readableChannel();
//        writableChannel();
//        scatteringByteChannel();
//        gatheringByteChannel();
        pipeChannel();
    }

    public static void readableChannel() throws IOException {
        String urlString = "https://s3.o7planning.com/txt/utf8-file-without-bom.txt";
        URL url = new URL(urlString);
        byte[] array = new byte[15];
        int i = 0;
        try (InputStream inputStream = url.openStream();
             ReadableByteChannel channel = Channels.newChannel(inputStream)) {
            ByteBuffer buffer = ByteBuffer.allocate(10);
            int bytesRead;
            while ((bytesRead = channel.read(buffer)) > -1) {
                System.out.println(" --- bytesRead : " + bytesRead + " ---- ");
                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte x = buffer.get();
                    array[i++] = x;
                    int charCode = Byte.toUnsignedInt(x);
                    System.out.println((char) charCode + " --> " + charCode);
                }
                buffer.clear();
            }
        }
        System.out.println("--- " + new String(array, StandardCharsets.UTF_8));
    }

    public static void writableChannel() throws IOException {
        File outFile = new File("src/main/resources/out-file.txt");
        try (OutputStream outputStream = new FileOutputStream(outFile);
             WritableByteChannel channel = Channels.newChannel(outputStream)) {

            byte[] byteData = "JP日本-八洲".getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(byteData.length);
            buffer.put(byteData);
            buffer.flip();

            while (buffer.hasRemaining()) {
                channel.write(buffer);
            }
        }
    }

    public static void scatteringByteChannel() throws IOException {
        try (FileInputStream fis = new FileInputStream("src/main/resources/example.txt");
             ScatteringByteChannel channel = (ScatteringByteChannel) Channels.newChannel(fis)) {

            ByteBuffer buf1 = ByteBuffer.allocate(10);
            ByteBuffer buf2 = ByteBuffer.allocate(15);
            ByteBuffer buf3 = ByteBuffer.allocate(10);
            ByteBuffer buf4 = ByteBuffer.allocate(12);

            ByteBuffer[] buffers = new ByteBuffer[]{buf1, buf2, buf3, buf4};

            channel.read(buffers);

            for (int i = 0; i < buffers.length; i++) {
                System.out.println(" --- buffer[" + i + "] ---");
                ByteBuffer buffer = buffers[i];
                buffer.flip();
                while (buffer.hasRemaining()) {
                    int charCode = Byte.toUnsignedInt(buffer.get());
                    System.out.println((char) charCode + " --> " + charCode);
                }
            }
        }
    }

    public static void gatheringByteChannel() {
        try (FileInputStream fis = new FileInputStream("src/main/resources/example.txt");
             ScatteringByteChannel inChannel = (ScatteringByteChannel) Channels.newChannel(fis);
             FileOutputStream fos = new FileOutputStream("src/main/resources/example-out.txt");
             GatheringByteChannel outChannel = (GatheringByteChannel) Channels.newChannel(fos)) {

            ByteBuffer buf1 = ByteBuffer.allocate(10);
            ByteBuffer buf2 = ByteBuffer.allocate(15);
            ByteBuffer buf3 = ByteBuffer.allocate(10);
            ByteBuffer buf4 = ByteBuffer.allocate(15);

            ByteBuffer[] buffers = new ByteBuffer[]{buf1, buf2, buf3, buf4};

            long bytesRead;
            while ((bytesRead = inChannel.read(buffers)) != -1) {
                for (int i = 0; i < buffers.length; i++) {
                    System.out.println(" --- buffer[" + i + "] --- reads [" + bytesRead + "]");
                    buffers[i].flip();
                }
                long bytesWrite = outChannel.write(buffers);
                System.out.println(" --- " + bytesWrite + " bytes were written ");
                for (ByteBuffer buffer : buffers) {
                    buffer.clear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pipeChannel() throws IOException, InterruptedException {
        Pipe pipe = Pipe.open();

        ThreadA threadA = new ThreadA(pipe);
        ThreadB threadB = new ThreadB(pipe);

        threadA.start();
        threadB.start();
        threadA.join(); // Waits for this thread to die.
        threadB.join(); // Waits for this thread to die.
        System.out.println();
        System.out.println("Done!");
    }

    static class ThreadA extends Thread {
        private final Pipe pipe;

        public ThreadA(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try (Pipe.SinkChannel skChannel = this.pipe.sink()) { // try
                String[] messages = new String[] { "Hello\n", "Hi\n", "Bye\n" };

                ByteBuffer buffer = ByteBuffer.allocate(512);

                for (String msg : messages) {
                    buffer.clear();
                    buffer.put(msg.getBytes("UTF-8"));
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        skChannel.write(buffer);
                    }
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        private final Pipe pipe;

        public ThreadB(Pipe pipe) {
            this.pipe = pipe;
        }

        @Override
        public void run() {
            try (Pipe.SourceChannel srcChannel = this.pipe.source()) {
                ByteBuffer buffer = ByteBuffer.allocate(512);

                while (srcChannel.read(buffer) != -1) {
                    buffer.flip();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();

                    while (buffer.hasRemaining()) {
                        byte b = buffer.get();
                        if (b != '\n') {
                            os.write(b);
                        } else {
                            String s = os.toString("UTF-8");
                            System.out.println(s);
                        }
                    }
                    buffer.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}