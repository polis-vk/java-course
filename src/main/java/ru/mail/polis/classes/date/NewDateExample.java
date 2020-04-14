package ru.mail.polis.classes.date;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.ValueRange;

public class NewDateExample {

    public static void main(String[] args) {
//        timeZone();
//        instanceMethods();
//        temporalAccessorMethods();
//        temporalMethods();
//        zonedDataTimeMethod();
        durationMethods();
    }

    public static void timeZone() {
        ZoneId zoneId1 = ZoneId.of("Europe/Moscow");
        System.out.println(zoneId1);

        ZoneId zoneId2 = ZoneId.of("+03:00:00");
        System.out.println(zoneId2);
    }

    public static void instanceMethods() {
        Instant instant1 = Clock.system(ZoneId.of("Europe/Paris")).instant();
        System.out.println(instant1.getEpochSecond());
        System.out.println(instant1.getNano());
        System.out.println(instant1.toEpochMilli());
        System.out.println(instant1.toString());


        Instant instant2 = Clock.systemUTC().instant();
        System.out.println(instant2.toString());

        Instant instant3 = Clock.systemDefaultZone().instant();
        System.out.println(instant3.toString());
    }

    public static void temporalAccessorMethods() {
        TemporalAccessor ta = Clock.systemUTC().instant();
        System.out.println(ta.getLong(ChronoField.INSTANT_SECONDS));

        ValueRange vr = ta.range(ChronoField.INSTANT_SECONDS);
        System.out.println(vr.getMinimum());
        System.out.println(vr.getMaximum());

        System.out.println(ta.isSupported(ChronoField.INSTANT_SECONDS));
        System.out.println(ta.isSupported(ChronoField.DAY_OF_MONTH));
    }

    public static void temporalMethods() {
        Temporal t1 = Clock.systemUTC().instant();
        Temporal t2 = t1.plus(1, ChronoUnit.DAYS);

        System.out.println(24 * 60 * 60);
        System.out.println(t2.getLong(ChronoField.INSTANT_SECONDS) - t1.getLong(ChronoField.INSTANT_SECONDS));

        System.out.println(Duration.between(t1, t2).get(ChronoUnit.SECONDS));
        System.out.println(t1.until(t2, ChronoUnit.HOURS));
    }

    public static void zonedDataTimeMethod() {
        LocalDateTime ldt = LocalDateTime.of(2017, 4, 14, 0, 0, 0, 0);
//        ZonedDateTime zdt = ZonedDateTime.from(ldt);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("Europe/Moscow"));
        System.out.println(zdt);

//        ZonedDateTime.of(2005, 2, 29,
//                2, 30, 0, 0, ZoneId.of("Europe/Moscow"));
//        ZonedDateTime.of(2005, 2, 20,
//                2, 30, 60, 0, ZoneId.of("Europe/Moscow"));
    }

    public static void durationMethods() {
        Period period = Period.of(0, 0, 1);
        Duration duration = Duration.of(1, ChronoUnit.DAYS);

        ZonedDateTime zdt1 = ZonedDateTime.of(2005, 10, 30, 0, 0, 0, 0, ZoneId.of("Europe/Moscow"));

        ZonedDateTime ztd2 = zdt1.plus(period);
        System.out.println(ztd2);

        ZonedDateTime ztd3 = zdt1.plus(duration);
        System.out.println(ztd3);
    }
}
