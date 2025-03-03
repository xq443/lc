package Snowflake.ratelimiter;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RateLimiterConfig {
    public Map<String, Rule> rateLimitRules;
    public Map<String, Deque<Long>> request;


    public RateLimiterConfig() {
        this.rateLimitRules = new HashMap<>();
        this.request = new HashMap<>();
    }

    public void registerRule(String ruleName, int maxRequests, long timeWindow) {
        rateLimitRules.put(ruleName, new Rule(maxRequests, timeWindow));
        request.put(ruleName, new LinkedList<>());
    }

    public boolean allowRequest(String ruleName) {
        Rule rule = rateLimitRules.get(ruleName);
        if(rule == null) {
            throw new IllegalArgumentException("Snowflake.ratelimiter.Rule " + ruleName + " not found");
        }
        long curr = System.currentTimeMillis();
        Deque<Long> queue = request.getOrDefault(ruleName, new LinkedList<>());

        while(!queue.isEmpty() && curr - rule.timeWindow > queue.peekFirst()) {
            queue.removeFirst();
        }
        if(queue.size() < rule.maxRequests) {
            queue.offerLast(curr);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        RateLimiterConfig config = new RateLimiterConfig();
        config.registerRule("3_requests_per_second", 3, 1000);  // 3 requests per second
        config.registerRule("5_requests_per_minute", 5, 60000); // 5 requests per minute
        // Test requests for each rule
        for (int i = 0; i < 10; i++) {
            if (config.allowRequest("3_requests_per_second")) {
                System.out.println("Request " + (i + 1) + " allowed for 3 requests per second");
            } else {
                System.out.println("Request " + (i + 1) + " denied for 3 requests per second");
            }
            try {
                Thread.sleep(200); // Simulate delay between requests
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 10; i++) {
            if (config.allowRequest("5_requests_per_minute")) {
                System.out.println("Request " + (i + 1) + " allowed for 5 requests per minute");
            } else {
                System.out.println("Request " + (i + 1) + " denied for 5 requests per minute");
            }
            try {
                Thread.sleep(200); // Simulate delay between requests
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
