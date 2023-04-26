package org.time;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TimeDuration {
    private final ZonedDateTime startTime;
    private final Duration duration;

    public TimeDuration(ZonedDateTime startTime, long minutes) {
        Objects.requireNonNull(startTime, "Parameter startTime musnt't be null!");
        if (minutes <= 0) {
            throw new IllegalArgumentException("Parameter minutes must be greater than zero!");
        }
        this.startTime = startTime;
        this.duration = Duration.ofMinutes(minutes);
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public ZonedDateTime getEndTime() {
        return startTime.plus(duration);
    }
}
