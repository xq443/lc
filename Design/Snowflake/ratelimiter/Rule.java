package Snowflake.ratelimiter;

public class Rule {
    int maxRequests;
    long timeWindow;

    public Rule(int maxRequests, long timeWindow) {
        this.maxRequests = maxRequests;
        this.timeWindow = timeWindow;
    }

}
