package Snowflake;

import java.time.LocalDateTime;

public class Entry {
    LocalDateTime timestamp;
    double temperature;

    public Entry(LocalDateTime timestamp, double temperature) {
        this.timestamp = timestamp;
        this.temperature = temperature;
    }
}
