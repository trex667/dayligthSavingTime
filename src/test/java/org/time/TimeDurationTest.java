package org.time;

import org.junit.Test;

import java.time.Duration;
import java.time.ZonedDateTime;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeDurationTest {

    @Test
    public void testWinterToSummerTime() {
        // switch from winter to summer in zone Europe/Berlin: 2023-03-26T02:00 -> 2023-03-26T03:00
        ZonedDateTime start = ZonedDateTime.parse("2023-03-26T01:00+01:00[Europe/Berlin]");

        TimeDuration timeDuration = new TimeDuration(start, 180);

        assertThat(timeDuration.getDuration(), is(equalTo(Duration.ofMinutes(180))));
        assertThat(timeDuration.getEndTime(), is(equalTo(ZonedDateTime.parse("2023-03-26T05:00+02:00[Europe/Berlin]"))));
    }

    @Test
    public void testSummerToWinterTime() {
        // switch from summer to winter in zone Europe/Berlin: 2023-10-29T03:00 -> 2023-10-29T02:00
        ZonedDateTime start = ZonedDateTime.parse("2023-10-29T01:00+02:00[Europe/Berlin]");

        TimeDuration timeDuration = new TimeDuration(start, 180);

        assertThat(timeDuration.getDuration(), is(equalTo(Duration.ofMinutes(180))));
        assertThat(timeDuration.getEndTime(), is(equalTo(ZonedDateTime.parse("2023-10-29T03:00+01:00[Europe/Berlin]"))));
    }
}