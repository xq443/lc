package Snowflake.event;

public class BasicEvent implements Event {
    private final String type;
    private final long timestampMillis;
    public BasicEvent(String type, long timestampMillis) {
        this.type = type;
        this.timestampMillis = timestampMillis;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public long timestampMillis() {
        return timestampMillis;
    }
}
