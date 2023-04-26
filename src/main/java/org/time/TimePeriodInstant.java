package org.time;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class TimePeriodInstant {

    private final Instant startTime;
    private final Instant endTime;

    public TimePeriodInstant(Instant startTime, Instant endTime) {
        Objects.requireNonNull(startTime, "Parameter startTime musnt't be null!");
        Objects.requireNonNull(endTime, "Parameter endTime musnt't be null!");
        if (startTime.compareTo(endTime) >= 0) {
            throw new IllegalArgumentException("Paramter endTime must be greater than parameter startTime!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
