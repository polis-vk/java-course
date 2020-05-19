package ru.mail.polis.for_test;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class StringUtilTestTheory {
    @DataPoints
    public static Object[][] isEmptyData = new Object[][] {
            { "", true },
            { " ", false },
            { "some string", false },
    };

    @DataPoint
    public static Object[] nullData = new Object[] { null, true };

    @Theory
    public void testEmpty(final Object... testData) {
        final boolean actual = StringUtil.isEmpty((CharSequence) testData[0]);
        assertEquals(testData[1], actual);
    }
}
