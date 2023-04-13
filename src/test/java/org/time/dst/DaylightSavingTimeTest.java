package org.time.dst;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DaylightSavingTimeTest {

    @Test
    public void testPrintInstantToZonedDateTime() {
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-04-10T06:00:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-11-10T06:00:00Z"), ZoneId.of("Europe/Berlin")));
        // output
        // 2023-04-10T08:00+02:00[Europe/Berlin]
        // 2023-11-10T07:00+01:00[Europe/Berlin]

        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-03-26T00:59:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-03-26T01:00:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-03-26T01:01:00Z"), ZoneId.of("Europe/Berlin")));
        // output
        // 2023-03-26T01:59+01:00[Europe/Berlin]
        // 2023-03-26T03:00+02:00[Europe/Berlin]
        // 2023-03-26T03:01+02:00[Europe/Berlin]

        ZonedDateTime start = ZonedDateTime.ofInstant(Instant.parse("2023-03-26T00:59:00Z"), ZoneId.of("Europe/Berlin"));
        ZonedDateTime end = ZonedDateTime.ofInstant(Instant.parse("2023-03-26T01:00:00Z"), ZoneId.of("Europe/Berlin"));
        System.out.println(Duration.between(start, end).toMinutes());
        // output
        // 1

        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-10-29T00:59:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-10-29T01:00:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-10-29T01:01:00Z"), ZoneId.of("Europe/Berlin")));
        // output
        // 2023-10-29T02:59+02:00[Europe/Berlin]
        // 2023-10-29T02:00+01:00[Europe/Berlin]

        start = ZonedDateTime.ofInstant(Instant.parse("2023-10-29T00:59:00Z"), ZoneId.of("Europe/Berlin"));
        end = ZonedDateTime.ofInstant(Instant.parse("2023-10-29T01:00:00Z"), ZoneId.of("Europe/Berlin"));
        System.out.println(Duration.between(start, end).toMinutes());
        // output
        // 1
    }

    @Test
    public void testPrintZonedDateTimeInWinterTime() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-11-10T08:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-11-10T12:00+01:00[Europe/Berlin]");
        System.out.println(String.format("ZonedDateTime '%s' to '%s' and duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
//        output:
//        ZonedDateTime '2023-11-10T08:00+01:00[Europe/Berlin]' to '2023-11-10T12:00+01:00[Europe/Berlin]' and duration in minutes: 240
    }
    @Test
    public void testPrintZonedDateTimeInSummerTime() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-04-10T08:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-04-10T12:00+02:00[Europe/Berlin]");
        System.out.println(String.format("ZonedDateTime '%s' to '%s' and duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
//        output:
//        ZonedDateTime '2023-04-10T08:00+02:00[Europe/Berlin]' to '2023-04-10T12:00+02:00[Europe/Berlin]' and duration in minutes: 240
    }

    @Test
    public void testPrintInstantAndZonedDateTimeAroundWinterToSummerTimeSwitch() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        Instant utcStart = Instant.parse("2023-03-26T00:59:00Z");
        ZonedDateTime zonedDateTimeStart = ZonedDateTime.ofInstant(utcStart, ZoneId.of("Europe/Berlin"));
        Instant utcEnd = Instant.parse("2023-03-26T02:00:00Z");
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.ofInstant(utcEnd, ZoneId.of("Europe/Berlin"));

        System.out.println(String.format("Instant '%s' to '%s' and duration in minutes: %d", utcStart, utcEnd, Duration.between(utcStart, utcEnd).getSeconds() / 60));
        System.out.println(String.format("ZonedDateTime '%s' to '%s' and duration in minutes: %d", zonedDateTimeStart, zonedDateTimeEnd, Duration.between(zonedDateTimeStart, zonedDateTimeEnd).getSeconds() / 60));
//        output:
//        Instant '2023-03-26T00:59:00Z' to '2023-03-26T02:00:00Z' and duration in minutes: 61
//        ZonedDateTime '2023-03-26T01:59+01:00[Europe/Berlin]' to '2023-03-26T04:00+02:00[Europe/Berlin]' and duration in minutes: 61
    }

    @Test
    public void testPrintInstantAndZonedDateTimeAroundSummerToWinterTimeSwitch() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        Instant utcStart = Instant.parse("2023-10-29T00:59:00Z");
        ZonedDateTime zonedDateTimeStart = ZonedDateTime.ofInstant(utcStart, ZoneId.of("Europe/Berlin"));
        Instant utcEnd = Instant.parse("2023-10-29T02:00:00Z");
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.ofInstant(utcEnd, ZoneId.of("Europe/Berlin"));

        System.out.println(String.format("Instant '%s' to '%s' and duration in minutes: %d", utcStart, utcEnd, Duration.between(utcStart, utcEnd).getSeconds() / 60));
        System.out.println(String.format("ZonedDateTime '%s' to '%s' and duration in minutes: %d", zonedDateTimeStart, zonedDateTimeEnd, Duration.between(zonedDateTimeStart, zonedDateTimeEnd).getSeconds() / 60));
//        output:
//        Instant '2023-10-29T00:59:00Z' to '2023-10-29T02:00:00Z' and duration in minutes: 61
//        ZonedDateTime '2023-10-29T02:59+02:00[Europe/Berlin]' to '2023-10-29T03:00+01:00[Europe/Berlin]' and duration in minutes: 61

    }

    @Test
    public void testPrintInstantToZonedDateTimeDuringSummerToWinterTimeSwitchEveryMinute() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        Instant utcStart = Instant.parse("2023-10-29T00:59:00Z");
        Instant stopTime = Instant.parse("2023-10-29T02:00:00Z");
        while(utcStart.isBefore(stopTime)){
            System.out.println(String.format("'%s' := '%s'", utcStart, utcStart.atZone(ZoneId.of("Europe/Berlin"))));
            utcStart = utcStart.plus(1, ChronoUnit.MINUTES);
        }
    }

    @Test
    public void testPrintDurationWithStartInWinterAndEndInSummerTime() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-03-26T04:00+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        start = ZonedDateTime.parse("2023-03-26T00:00+01:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-03-27T00:00+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in hours: %d", start, end, Duration.between(start, end).toHours()));

    }

    @Test
    public void testPrintDurationWithStartInSummerAndEndInWinterTime() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T04:00+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        start = ZonedDateTime.parse("2023-10-29T00:00+02:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-10-30T00:00+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in hours: %d", start, end, Duration.between(start, end).toHours()));

    }

    @Test
    public void testPrintDurationWithStartInWinterAndEndInSwitchHour() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-03-26T02:30+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-03-26T02:30+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

    }

    @Test
    public void obelix() {
        LocalDateTime localDateTime = LocalDateTime.parse("2023-03-26T04:00");
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Europe/Berlin"));
        System.out.println(localDateTime);
        System.out.println(zonedDateTime);
    }

    @Test
    public void testPrintDurationWithStartInWinterTime() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        Duration duration = Duration.ofMinutes(90);
        ZonedDateTime end = start.plus(duration);
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
    }

    @Test
    public void testPrintDurationWithStartAndEndInFirstHourSummerToWinter() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        // first hour is the summer time hour:  2023-10-29T02:00+02:00[Europe/Berlin] until 2023-10-29T03:00+02:00[Europe/Berlin]
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T02:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T02:59+02:00[Europe/Berlin]");
        while(start.isBefore(end)){
            System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
            start = start.plus(1, ChronoUnit.MINUTES);
        }
    }

    @Test
    public void testPrintDurationWithStartAndEndInSecondHourSummerToWinter() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        // first hour is the summer time hour:  2023-10-29T02:00+02:00[Europe/Berlin] until 2023-10-29T03:00+02:00[Europe/Berlin]
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T02:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T02:59+01:00[Europe/Berlin]");
        while(start.isBefore(end)){
            System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
            start = start.plus(1, ChronoUnit.MINUTES);
        }
    }

    @Test
    public void testPrintDurationWithStartInFirstHourAndEndInSecondHourSummerToWinter() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        // first hour is the summer time hour:  2023-10-29T02:00+02:00[Europe/Berlin] until 2023-10-29T03:00+02:00[Europe/Berlin]
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T02:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T02:59+01:00[Europe/Berlin]");
        while(start.isBefore(end)){
            System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
            start = start.plus(1, ChronoUnit.MINUTES);
        }
    }

    @Test
    public void testPrintDurationWithStartBeforeSwitchAndEndInSecondHourSummerToWinter() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        // first hour is the summer time hour:  2023-10-29T02:00+02:00[Europe/Berlin] until 2023-10-29T03:00+02:00[Europe/Berlin]
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T02:30+01:00[Europe/Berlin]");
        while(start.isBefore(end)){
            System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
            start = start.plus(1, ChronoUnit.MINUTES);
        }
    }

    @Test
    public void test() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T02:30+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
    }

    @Test
    public void testPrintDurationWithStartInForstHourAndEndInAfterSummerToWinter() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        // first hour is the summer time hour:  2023-10-29T02:00+02:00[Europe/Berlin] until 2023-10-29T03:00+02:00[Europe/Berlin]
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T02:30+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T03:30+01:00[Europe/Berlin]");
        while(start.isBefore(end)){
            System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
            start = start.plus(1, ChronoUnit.MINUTES);
        }
    }


    @Test
    public void blafasel() {
//        Instant instant = Instant.parse("2023-03-26T05:00:00Z");
//
        ZoneId zoneId = ZoneId.of("Europe/Berlin");
        System.out.println(zoneId.getRules().getTransitionRules());
//        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
//        boolean isDST = zoneId.getRules().isDaylightSavings(instant);
//        Duration durationDST = zoneId.getRules().getDaylightSavings(instant);
//
//        System.out.println(instant);
//        System.out.println(zonedDateTime);
//        System.out.println(isDST);
//        System.out.println(durationDST.toMinutes());
//

//        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
//        Duration duration = Duration.ofMinutes(120);
//        System.out.println(String.format("Start '%s' plus duration in minutes '%d' results in '%s'", start, duration.toMinutes(), start.plus(duration)));


    }
}
