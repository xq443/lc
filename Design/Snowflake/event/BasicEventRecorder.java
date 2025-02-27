package Snowflake.event;

import java.util.ArrayList;
import java.util.List;

public class BasicEventRecorder implements EventRecorder {
    private final List<Event> events = new ArrayList<>();

    @Override
    public void record(Event event) {
        events.add(event);
    }

    @Override
    public int count(String eventType, long startMillis, long endMillis) {
        int cnt = 0;
        long start = (startMillis * 60000) / 60000;
        long end = (endMillis * 60000) / 60000;
        for (Event event : events) {
            if (event.type().equals(eventType)) {
                long time = (event.timestampMillis() * 60000) / 60000;
                if (time >= start && time <= end) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
