package ru.mail.polis.classes.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class OldDateExample {

    public static void main(String[] args) {
        workWithCalendar();
    }

    public static void workWithCalendar() {
        Calendar calendar = new GregorianCalendar();
        System.out.println(calendar.getTime());

        calendar.add(Calendar.MONTH, -5);
        System.out.println(calendar.getTime());

        DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
        System.out.println(df.format(calendar.getTime()));
    }
}
