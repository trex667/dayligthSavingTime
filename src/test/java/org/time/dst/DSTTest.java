package org.time.dst;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DSTTest {
    @Test
    public void testConvertInstantToZonedDateTime() {
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-04-10T06:00:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-11-10T06:00:00Z"), ZoneId.of("Europe/Berlin")));

        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-03-26T00:59:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-03-26T01:00:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-03-26T01:01:00Z"), ZoneId.of("Europe/Berlin")));

        ZonedDateTime start = ZonedDateTime.ofInstant(Instant.parse("2023-03-26T00:59:00Z"), ZoneId.of("Europe/Berlin"));
        ZonedDateTime end = ZonedDateTime.ofInstant(Instant.parse("2023-03-26T01:00:00Z"), ZoneId.of("Europe/Berlin"));
        System.out.println(Duration.between(start, end).toMinutes());

        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-10-29T00:59:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-10-29T01:00:00Z"), ZoneId.of("Europe/Berlin")));
        System.out.println(ZonedDateTime.ofInstant(Instant.parse("2023-10-29T01:01:00Z"), ZoneId.of("Europe/Berlin")));

        start = ZonedDateTime.ofInstant(Instant.parse("2023-10-29T00:59:00Z"), ZoneId.of("Europe/Berlin"));
        end = ZonedDateTime.ofInstant(Instant.parse("2023-10-29T01:00:00Z"), ZoneId.of("Europe/Berlin"));
        System.out.println(Duration.between(start, end).toMinutes());
    }

    // start and end in the same time offset
    @Test
    public void example_1() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-04-10T08:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-04-10T12:00+02:00[Europe/Berlin]");
        System.out.println(String.format("ZonedDateTime '%s' to '%s' and duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        start = ZonedDateTime.parse("2023-11-10T08:00+01:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-11-10T12:00+01:00[Europe/Berlin]");
        System.out.println(String.format("ZonedDateTime '%s' to '%s' and duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
    }

    // start in winter time and end in summer time
    @Test
    public void example_2() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-03-26T04:00+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        start = ZonedDateTime.parse("2023-03-26T00:00+01:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-03-27T00:00+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in hours: %d", start, end, Duration.between(start, end).toHours()));
    }

    // start in summer time and end in winter time
    @Test
    public void example_3() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T04:00+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        start = ZonedDateTime.parse("2023-10-29T00:00+02:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-10-30T00:00+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in hours: %d", start, end, Duration.between(start, end).toHours()));
    }

    //  end time in the gap from winter time to summer time
    @Test
    public void example_4() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-03-26T02:30+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
    }

    // end time in the gap from summer time to winter time
    @Test
    public void example_5() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T02:30+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));

        start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        end = ZonedDateTime.parse("2023-10-29T02:30+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, end, Duration.between(start, end).toMinutes()));
    }

    @Test
    public void testStartAndDuration() {
        Duration duration = Duration.ofMinutes(120);

        // summer time
        ZonedDateTime start = ZonedDateTime.parse("2023-04-10T08:00+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, start.plus(duration), duration.toMinutes()));

        // winter time
        start = ZonedDateTime.parse("2023-11-10T08:00+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, start.plus(duration), duration.toMinutes()));

        // start in summer time and plus duration end in winter time
        duration = Duration.ofMinutes(240);
        start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, start.plus(duration), duration.toMinutes()));

        // start in winter time and plus duration end in summer time
        start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, start.plus(duration), duration.toMinutes()));

        // start in the first hour of the switch summer to winter
        start = ZonedDateTime.parse("2023-10-29T02:30+02:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, start.plus(duration), duration.toMinutes()));

        // start in the second hour of the switch summer to winter
        start = ZonedDateTime.parse("2023-10-29T02:30+01:00[Europe/Berlin]");
        System.out.println(String.format("Start '%s' stop '%s' duration in minutes: %d", start, start.plus(duration), duration.toMinutes()));
    }
}
