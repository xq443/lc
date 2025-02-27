package Snowflake.event;

interface EventRecorder {
    void record(Event event);
    // one minute granularity only
    int count(String eventType, long startMillis, long endMillis);
}