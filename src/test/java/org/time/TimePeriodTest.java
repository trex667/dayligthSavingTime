package org.time;

import org.junit.Test;

import java.time.Duration;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TimePeriodTest {
    @Test
    public void testWinterToSummerTime() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-03-26T05:00+02:00[Europe/Berlin]");

        TimePeriod timePeriod = new TimePeriod(start, end);

        assertThat(timePeriod.getDuration(), is(equalTo(Duration.ofMinutes(180))));
    }

    @Test
    public void testSummerToWinterTime() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");
        ZonedDateTime end = ZonedDateTime.parse("2023-10-29T03:00+01:00[Europe/Berlin]");

        TimePeriod timePeriod = new TimePeriod(start, end);

        assertThat(timePeriod.getDuration(), is(equalTo(Duration.ofMinutes(180))));
    }
}