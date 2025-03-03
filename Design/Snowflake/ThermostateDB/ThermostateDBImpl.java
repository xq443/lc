package Snowflake.ThermostateDB;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ThermostateDBImpl implements ThermostatDB {
    Map<LocalDateTime, Double> map;

    public ThermostateDBImpl() {
        this.map = new TreeMap<>(); // sorted map by default
    }

    @Override
    public void addTemperature(LocalDateTime timestamp, double temperature) {
        map.put(timestamp, temperature);
    }
    // TC: O(logN)
    // SC: O(N)

    @Override
    public double getTemperature(LocalDateTime timestamp) throws IllegalAccessException {
        if(map.containsKey(timestamp)) {
            return map.get(timestamp);
        } else {
            throw new IllegalAccessException("Temperature data is not found for the given timestamp");
        }
    }
    // TC: O(logN)
    // SC: O(1)

    @Override
    public List<Double> getTemperatureBetween(LocalDateTime start, LocalDateTime end) {
        List<Double> ret = new ArrayList<>();
        for(Map.Entry<LocalDateTime, Double> entry : map.entrySet()) {
            LocalDateTime curr = entry.getKey();
            if(!curr.isAfter(end) && !curr.isBefore(start)) {
                ret.add(entry.getValue());
            }
        }
        return ret;
    }
    // TC: O(N)
    // SC: O(N)
}
