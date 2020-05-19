package ru.mail.polis.for_test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StringUtilTestParam {
    private final CharSequence testData;
    private final boolean expected;

    public StringUtilTestParam(final CharSequence testData, final boolean expected) {
        this.testData = testData;
        this.expected = expected;
    }

    @Test
    public void testIsEmpty() {
        final boolean actual = StringUtil.isEmpty(testData);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
                { null, true },
                { "", true },
                { " ", true },
                { "some string", false },
        });
    }
}
