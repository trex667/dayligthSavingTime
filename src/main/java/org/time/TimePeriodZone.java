package org.time;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Objects;

public class TimePeriodZone {
    private final ZonedDateTime startTime;
    private final ZonedDateTime endTime;

    public TimePeriodZone(ZonedDateTime startTime, ZonedDateTime endTime) {
        Objects.requireNonNull(startTime, "Parameter startTime musnt't be null!");
        Objects.requireNonNull(endTime, "Parameter endTime musnt't be null!");
        if(startTime.compareTo(endTime) >= 0){
            throw new IllegalArgumentException("Paramter endTime must be greater than parameter startTime!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public Duration getDuration(){
        return Duration.between(startTime, endTime);
    }
}
