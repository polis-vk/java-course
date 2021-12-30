package ru.mail.polis.nio;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.mail.polis.nio.selector.EchoClient;
import ru.mail.polis.nio.selector.EchoServer;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.Pipe;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EchoTest {

    Process server;
    EchoClient client;

    @Before
    public void setup() throws IOException, InterruptedException {
//        server = EchoServer.start();
        client = EchoClient.start();
    }

    @Test
    public void givenServerClient_whenServerEchosMessage_thenCorrect() {
        String resp1 = client.sendMessage("hello");
        String resp2 = client.sendMessage("world");
        String resp3 = client.sendMessage("POISON_PILL");

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("world", resp3);
    }

    @Test
    public void whenWakeUpCalledOnSelector_thenBlockedThreadReturns() throws IOException, InterruptedException {
        String resp3 = client.sendMessage("POISON_PILL");
        Pipe pipe = Pipe.open();
        Selector selector = Selector.open();
        SelectableChannel channel = pipe.source();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ);

        List<String> invocationStepsTracker = Collections.synchronizedList(new ArrayList<>());

        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            invocationStepsTracker.add(">> Count down");
            latch.countDown();
            try {
                invocationStepsTracker.add(">> Start select");
                selector.select();
                invocationStepsTracker.add(">> End select");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        invocationStepsTracker.add(">> Start await");
        latch.await();
        invocationStepsTracker.add(">> End await");

        invocationStepsTracker.add(">> Wakeup thread");
        selector.wakeup();
        //clean up
        channel.close();

        assertEquals(invocationStepsTracker,
                Arrays.asList(">> Start await",
                        ">> Count down",
                        ">> Start select",
                        ">> End await",
                        ">> Wakeup thread",
                        ">> End select"
                ));

    }

    @After
    public void teardown() throws IOException {
        server.destroy();
        EchoClient.stop();
    }
}
