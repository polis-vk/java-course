package ru.mail.polis.for_test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Rule
    public final Timeout timeout = new Timeout(100, TimeUnit.SECONDS);

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final Map<String, byte[]> toHexStringData = new HashMap<>();

    @Before
    public void setUpToHexStringData() {
        toHexStringData.put("", new byte[0]);
        toHexStringData.put("01020d112d7f", new byte[] { 1, 2, 13, 17, 45, 127 });
        toHexStringData.put("00fff21180", new byte[] { 0, -1, -14, 17, -128 });
    }

    @Ignore
    @Test
    public void anotherInfinity() {
        while (true);
    }

    @Test
    public void testToHexString() {
        for (Map.Entry<String, byte[]> entry : toHexStringData.entrySet()) {
            final byte[] testData = entry.getValue();
            final String expected = entry.getKey();
            final String actual = StringUtil.toHexString(testData);
            assertEquals(expected, actual);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testToHexString_NPE() {
        StringUtil.toHexString(null);
    }

    @Test
    public void testFileWriting() throws IOException {
        final File log = folder.newFile("debug.log");
        final FileWriter logWriter = new FileWriter(log);
        logWriter.append("Hello, ");
        logWriter.append("World!!!");
        logWriter.flush();
        logWriter.close();
    }

    @Test
    public void testExpectedException() {
        thrown.expect(NullPointerException.class);
        StringUtil.toHexString(null);
    }

}