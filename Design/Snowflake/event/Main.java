package Snowflake.event;

public class Main {
    public static void main(String[] args) {
        EventRecorder recorder = new BasicEventRecorder();
        // Record some events
        recorder.record(new BasicEvent("eventA", 1629873600000L));  // timestamp: 2021-08-25 10:00:00
        recorder.record(new BasicEvent("eventA", 1629873660000L));  // timestamp: 2021-08-25 10:01:00
        recorder.record(new BasicEvent("eventB", 1629873720000L));  // timestamp: 2021-08-25 10:02:00

        // Count events of type "eventA" between 2021-08-25 10:00:00 and 2021-08-25 10:01:00
        long startMillis = 1629873600000L;  // 2021-08-25 10:00:00
        long endMillis = 1629873660000L;    // 2021-08-25 10:01:00
        int count = recorder.count("eventA", startMillis, endMillis);

        System.out.println("Count of eventA: " + count);  // Output: Count of eventA: 2
    }
}
