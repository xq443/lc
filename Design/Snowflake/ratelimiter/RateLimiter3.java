package Snowflake.ratelimiter;


import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter3 {
    // Store the request timestamps
    private final Deque<Long> requestsPerSecond;
    private final Deque<Long> requestsPerTenSeconds;

    // Maximum requests allowed
    private final int maxRequestsPerSecond = 3;
    private final int maxRequestsPerTenSeconds = 20;

    // Time window duration
    private final long secondWindow = 1000L;  // 1 second in milliseconds
    private final long tenSecondWindow = 10000L;  // 10 seconds in milliseconds

    // Atomic counters to track requests in the last second and last 10 seconds
    private final AtomicInteger requestsInLastSecond;
    private final AtomicInteger requestsInLastTenSeconds;

    public RateLimiter3() {
        requestsPerSecond = new LinkedList<>();
        requestsPerTenSeconds = new LinkedList<>();

        // Initialize atomic counters
        requestsInLastSecond = new AtomicInteger(0);
        requestsInLastTenSeconds = new AtomicInteger(0);
    }

    // Method to check if a request can be processed
    public boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Remove requests older than 1 second for per-second rate limit
        while (!requestsPerSecond.isEmpty() && currentTime - requestsPerSecond.peek() >= secondWindow) {
            requestsPerSecond.poll();
        }

        // Remove requests older than 10 seconds for per-10-seconds rate limit
        while (!requestsPerTenSeconds.isEmpty() && currentTime - requestsPerTenSeconds.peek() >= tenSecondWindow) {
            requestsPerTenSeconds.poll();
        }

        // Check if we are exceeding per-second limit (more than 3 requests in the last second)
        if (requestsPerSecond.size() >= maxRequestsPerSecond) {
            return false;  // Reject request
        }

        // Check if we are exceeding per-10-seconds limit (more than 20 requests in the last 10 seconds)
        if (requestsPerTenSeconds.size() >= maxRequestsPerTenSeconds) {
            return false;  // Reject request
        }

        // Record the request in both windows
        requestsPerSecond.add(currentTime);
        requestsPerTenSeconds.add(currentTime);

        // Increment atomic counters for requests in the last second and last 10 seconds
        requestsInLastSecond.incrementAndGet();
        requestsInLastTenSeconds.incrementAndGet(); // perform operations atomically,
        // meaning they handle multiple threads attempting to modify the same integer at the same time.

        return true;  // Allow request
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter3 rateLimiter = new RateLimiter3();

        // Simulating requests
        long lastSecondTime = System.currentTimeMillis();
        long lastTenSecondTime = System.currentTimeMillis();

        for (int i = 0; i < 25; i++) {
            long currentTime = System.currentTimeMillis();

            // Check if one second has passed and update requestsInLastSecond
            if (currentTime - lastSecondTime >= 1000) {
                System.out.println("Requests in the last second: " + rateLimiter.requestsInLastSecond.get());
                rateLimiter.requestsInLastSecond.set(0);  // Reset counter
                lastSecondTime = currentTime;
            }

            // Check if ten seconds have passed and update requestsInLastTenSeconds
            if (currentTime - lastTenSecondTime >= 10000) {
                System.out.println("Requests in the last 10 seconds: " + rateLimiter.requestsInLastTenSeconds.get());
                rateLimiter.requestsInLastTenSeconds.set(0);  // Reset counter
                lastTenSecondTime = currentTime;
            }

            // Process the request and track whether it's allowed
            if (rateLimiter.allowRequest()) {
                System.out.println("Request " + (i + 1) + " allowed");
            } else {
                System.out.println("Request " + (i + 1) + " rejected");
            }

            Thread.sleep(300);  // Simulate a slight delay between requests
        }

        // Final count after the simulation
        System.out.println("Final requests in the last second: " + rateLimiter.requestsInLastSecond.get());
        System.out.println("Final requests in the last 10 seconds: " + rateLimiter.requestsInLastTenSeconds.get());
    }
}
