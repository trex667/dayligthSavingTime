package org.time;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimePeriodInstantTest {
    @Test
    public void testWinterToSummerTime() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        Instant start = Instant.parse("2023-03-26T00:00:00Z");
        Instant end = Instant.parse("2023-03-26T03:00:00Z");

        TimePeriodInstant timePeriod = new TimePeriodInstant(start, end);

        assertThat(timePeriod.getDuration(), is(equalTo(Duration.ofMinutes(180))));
    }

    @Test
    public void testSummerToWinterTime() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        Instant start = Instant.parse("2023-10-28T23:00:00Z");
        Instant end = Instant.parse("2023-10-29T02:00:00Z");

        TimePeriodInstant timePeriod = new TimePeriodInstant(start, end);

        assertThat(timePeriod.getDuration(), is(equalTo(Duration.ofMinutes(180))));
    }
}