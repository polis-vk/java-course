package ru.mail.polis.for_test;

import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

public class MockitoExample {

    @Test
    public void testClassicSimple() {
        DataService dataService = mock(DataService.class);

        List<String> data = new ArrayList<>();
        data.add("dataItem");
        when(dataService.getAllData()).thenReturn(data);

        assertEquals(Collections.singletonList("dataItem"), dataService.getAllData());
    }

    @Test
    public void testNonClassicSimple() {
        DataService dataService = mock(DataService.class);

        List<String> data = new ArrayList<>();
        data.add("dataItem");
        doReturn(data).when(dataService).getAllData();

        assertEquals(Collections.singletonList("dataItem"), dataService.getAllData());
    }

    @Test
    public void testWithAnyParams() {
        DataService dataService = mock(DataService.class);

        when(dataService.getDataById(anyInt())).thenReturn("dataItem");

        assertEquals("dataItem", dataService.getDataById(1));
    }

    @Test
    public void testWithParams() {
        DataService dataService = mock(DataService.class);

        when(dataService.getDataById(eq(1))).thenReturn("dataItem");

        assertEquals("dataItem", dataService.getDataById(1));
        assertNotEquals("dataItem", dataService.getDataById(2));

    }

    @Test
    public void testWithConditionParams() {
        DataService dataService = mock(DataService.class);

        when(dataService.getDataByString(argThat(arg -> arg == null || arg.length() < 2))).thenReturn("dataItem");

        assertEquals("dataItem", dataService.getDataByString("9"));
        assertNotEquals("dataItem", dataService.getDataByString("20"));
    }

    @Test(expected = NumberFormatException.class)
    public void testWithException() {
        DataService dataService = mock(DataService.class);

        when(dataService.getDataByString(any())).thenThrow(NumberFormatException.class);

        dataService.getDataByString("id");
    }

    @Test
    public void testHard() {
        DataService dataService = mock(DataService.class);

        when(dataService.getDataByStrings(any()))
                .thenAnswer(invocation -> invocation.<List<String>>getArgument(0).stream()
                        .map(id -> {
                            switch (id) {
                                case "1":
                                    return "dataItemA";
                                case "2":
                                    return "dataItemB";
                                default:
                                    return null;
                            }
                        })
                        .collect(Collectors.toList()));

        assertEquals(Arrays.asList("dataItemA", "dataItemB"), dataService.getDataByStrings(Arrays.asList("1", "2")));
    }

    @Test
    public void testMany() {
        DataService dataService = mock(DataService.class);

        when(dataService.getDataByString("a"))
                .thenReturn("valueA1", "valueA2");

//        assertEquals("valueA1", dataService.getDataByString("a"));
//        assertEquals("valueA2", dataService.getDataByString("a"));
        dataService.getDataByString("a");
        dataService.getDataById(1);

        InOrder order = inOrder(dataService);
        order.verify(dataService).getDataByString(anyString());
        order.verify(dataService).getDataById(anyInt());
//        verify(dataService, times(1)).getDataById(anyInt());
//        verify(dataService).getDataByString(anyString());
    }


    public class DataService {
        private final List<String> allData;

        public DataService(List<String> allData) {
            this.allData = allData;
        }

        public List<String> getAllData() {
            return allData;
        }

        public String getDataById(int id) {
            return allData.get(id);
        }

        public String getDataByString(String id) {
            return allData.get(Integer.parseInt(id));
        }

        public List<String> getDataByStrings(List<String> ids) {
            return ids.stream()
                    .map(Integer::parseInt)
                    .map(allData::get)
                    .collect(Collectors.toList());
        }
    }
}
