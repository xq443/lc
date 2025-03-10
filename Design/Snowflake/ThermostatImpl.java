package Snowflake;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ThermostatImpl implements Thermostat {
    List<Entry> entries;

    public ThermostatImpl() {
        this.entries = new ArrayList<>();
    }

    @Override
    public void addTemperature(LocalDateTime timestamp, double temperature) {
        entries.add(new Entry(timestamp, temperature));
        entries.sort((e1, e2) -> e1.timestamp.compareTo(e2.timestamp));
    }

    @Override
    public double getTemperature(LocalDateTime timestamp) throws IllegalAccessException {
        int idx = binarySearch(timestamp);
        if (idx != -1) {
            return entries.get(idx).temperature;
        } else {
            throw new IllegalAccessException("Temperature data is not found for the given timestamp");
        }
    }

    @Override
    public List<Double> getTemperatureBetween(LocalDateTime start, LocalDateTime end) {
        List<Double> ret = new ArrayList<>();
        int startIdx = binarySearch(start);
        int endIdx = binarySearch2(end);

        if (startIdx == -1 || endIdx == -1 || startIdx > endIdx) {
            return ret;
        }

        for (int i = startIdx; i <= endIdx; i++) {
            LocalDateTime curr = entries.get(i).timestamp;
            if (!curr.isBefore(start) && !curr.isAfter(end)) {
                ret.add(entries.get(i).temperature);
            }
        }
        return ret;
    }

    // Find the first index >= start Timestamp
    public int binarySearch(LocalDateTime timestamp) {
        int left = 0, right = entries.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            LocalDateTime midTime = entries.get(mid).timestamp;
            if (midTime.isEqual(timestamp)) {
                return mid;
            } else if (midTime.isBefore(timestamp)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left; // If not found, return the insertion point where the timestamp should go
    }

    // Find the last index <= end Timestamp
    public int binarySearch2(LocalDateTime timestamp) {
        int left = 0, right = entries.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            LocalDateTime midTime = entries.get(mid).timestamp;
            if (midTime.isEqual(timestamp)) {
                return mid;
            } else if (midTime.isBefore(timestamp)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right; // If not found, return the last index before the timestamp
    }

    public static void main(String[] args) {
        try {
            ThermostatImpl thermostatDB = new ThermostatImpl();

            // Adding some temperature data
            thermostatDB.addTemperature(LocalDateTime.of(2025, 3, 6, 10, 0, 0), 22.5);
            thermostatDB.addTemperature(LocalDateTime.of(2025, 3, 6, 12, 0, 0), 24.0);
            thermostatDB.addTemperature(LocalDateTime.of(2025, 3, 6, 14, 0, 0), 23.5);
            thermostatDB.addTemperature(LocalDateTime.of(2025, 3, 6, 16, 0, 0), 25.0);

            // Retrieve specific temperature value at a timestamp
            LocalDateTime timestampToRetrieve = LocalDateTime.of(2025, 3, 6, 12, 0, 0);
            double temperature = thermostatDB.getTemperature(timestampToRetrieve);
            System.out.println("Temperature at " + timestampToRetrieve + ": " + temperature + "°C");

            // Retrieve temperatures between two timestamps
            LocalDateTime start = LocalDateTime.of(2025, 3, 6, 10, 0, 0);
            LocalDateTime end = LocalDateTime.of(2025, 3, 6, 15, 0, 0);
            System.out.println("Temperatures between " + start + " and " + end + ":");
            for (double temp : thermostatDB.getTemperatureBetween(start, end)) {
                System.out.println(temp + "°C");
            }

            // Attempt to retrieve a temperature for a non-existent timestamp
            LocalDateTime nonExistentTimestamp = LocalDateTime.of(2025, 3, 6, 18, 0, 0);
            try {
                thermostatDB.getTemperature(nonExistentTimestamp);
            } catch (IllegalAccessException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Entry class to hold timestamp and temperature
    public static class Entry {
        private final LocalDateTime timestamp;
        private final double temperature;

        public Entry(LocalDateTime timestamp, double temperature) {
            this.timestamp = timestamp;
            this.temperature = temperature;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public double getTemperature() {
            return temperature;
        }
    }

    // Adding an entry:
        // Time Complexity: O(N log N) (due to sorting)
        //Space Complexity: O(N) (for the list of entries)
    // Finding a specific timestamp:
        // Time Complexity: O(log N)
        // Space Complexity: O(1)
    //In-range search:
        // Time Complexity: O(log N + M) (binary search for the range + iterating through the range of elements)
        // Space Complexity: O(M) (for storing the range results)
}
