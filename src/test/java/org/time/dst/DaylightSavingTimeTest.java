package org.time.dst;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DaylightSavingTimeTest {

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
}
