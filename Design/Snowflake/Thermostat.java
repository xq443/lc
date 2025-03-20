package Snowflake;

import java.time.LocalDateTime;
import java.util.List;

public interface Thermostat {
    void addTemperature(LocalDateTime timestamp, double temperature);
    double getTemperature(LocalDateTime timestamp) throws IllegalAccessException;
    List<Double> getTemperatureBetween(LocalDateTime start, LocalDateTime end);
    boolean removeTemperature(LocalDateTime timestamp);
}
