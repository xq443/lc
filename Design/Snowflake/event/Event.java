package Snowflake.event;

public interface Event {
    String type ();
    long timestampMillis ();
}