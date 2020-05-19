package ru.mail.polis.for_test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class StringUtilTestJUnit5 {

    private final Map<String, byte[]> toHexStringData = new HashMap<>();

    @BeforeEach
    public void setUpToHexStringData() {
        toHexStringData.put("", new byte[0]);
        toHexStringData.put("01020d112d7f", new byte[] { 1, 2, 13, 17, 45, 127 });
        toHexStringData.put("00fff21180", new byte[] { 0, -1, -14, 17, -128 });
    }

    @Test
    @RepeatedTest(value = 5)
    public void testToHexString() {
        for (Map.Entry<String, byte[]> entry : toHexStringData.entrySet()) {
            final byte[] testData = entry.getValue();
            final String expected = entry.getKey();
            final String actual = StringUtil.toHexString(testData);
            assertEquals(expected, actual);
        }
    }

    @Test
    public void testToHexStringAssertAll() {
        String actual1 = StringUtil.toHexString(new byte[] { 1, 2, 13, 17, 45, 127 });
        String actual2 = StringUtil.toHexString(new byte[] { 0, -1, -14, 17, -128 });
        assertAll(
                () -> assertEquals("01020d112d7f", actual1),
                () -> assertEquals("00fff21180", actual2),
                () -> assertNotEquals(actual1, actual2)
        );
    }

    @ParameterizedTest
    @MethodSource("testToHexStringParam")
    public void testToHexStringParam(String actual, byte[] testData) {
        assertEquals(actual, StringUtil.toHexString(testData));
    }

    public static Stream<Arguments> testToHexStringParam() {
        return Stream.of(Arguments.arguments("01020d112d7f", new byte[] { 1, 2, 13, 17, 45, 127 }),
                Arguments.arguments("00fff21180", new byte[] { 0, -1, -14, 17, -128 }));
    }

    @Test
    public void testExpectedException() {
        Throwable exception = assertThrows(NullPointerException.class, () -> StringUtil.toHexString(null));
        assertNull(exception.getMessage());
    }
}
