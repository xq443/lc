package Snowflake.ratelimiter;

import java.util.Deque;
import java.util.LinkedList;

public class RateLimiter {
    // Store the request timestamps
    private final Deque<Long> requestsPerSecond;
    private final Deque<Long> requestsPerTenSeconds;

    // Maximum requests allowed
    // 每秒不超过 3 req，每 10 秒不超过 20 个
    private final int maxRequestsPerSecond = 3;
    private final int maxRequestsPerTenSeconds = 20;

    // Time window duration
    private final long secondWindow = 1000L;  // 1 second in milliseconds
    private final long tenSecondWindow = 10000L;  // 10 seconds in milliseconds

    public RateLimiter() {
        requestsPerSecond = new LinkedList<>();
        requestsPerTenSeconds = new LinkedList<>();
    }

    // Method to check if a request can be processed
    // Mutual Exclusion (Mutex): The main idea behind synchronized is to provide mutual exclusion, meaning only one thread can execute the synchronized code at a time.
    // Locks: When a thread enters a synchronized block or method, it acquires a lock on the object (or class) being synchronized.
    // Other threads trying to access the same synchronized block or method must wait until the lock is released by the current thread.
    // This can lead to thread contention and context switching, which can slow down performance, especially when there is a high number of threads competing for the same lock.
    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        // Remove requests older than 1 second for per-second rate limit
        while (!requestsPerSecond.isEmpty() && currentTime - requestsPerSecond.peek() >= secondWindow) {
            requestsPerSecond.poll(); // remove the first element
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
        requestsPerSecond.add(currentTime); // add to the last position
        requestsPerTenSeconds.add(currentTime);

        return true;  // Allow request
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter();

        // Simulating requests
        long lastSecondTime = System.currentTimeMillis();
        long lastTenSecondTime = System.currentTimeMillis();
        int requestsInLastSecond = 0;
        int requestsInLastTenSeconds = 0;

        for (int i = 0; i < 25; i++) {
            long currentTime = System.currentTimeMillis();

            // Check if one second has passed and update requestsInLastSecond
            if (currentTime - lastSecondTime >= 1000) {
                System.out.println("Requests in the last second: " + requestsInLastSecond);
                requestsInLastSecond = 0;
                lastSecondTime = currentTime;
            }

            // Check if ten seconds have passed and update requestsInLastTenSeconds
            if (currentTime - lastTenSecondTime >= 10000) {
                System.out.println("Requests in the last 10 seconds: " + requestsInLastTenSeconds);
                requestsInLastTenSeconds = 0;
                lastTenSecondTime = currentTime;
            }

            // Process the request and track whether it's allowed
            if (rateLimiter.allowRequest()) {
                requestsInLastSecond++;
                requestsInLastTenSeconds++;
                System.out.println("Request " + (i + 1) + " allowed");
            } else {
                System.out.println("Request " + (i + 1) + " rejected");
            }

            Thread.sleep(300);  // Simulate a slight delay between requests
        }

        // Final count after the simulation
        System.out.println("Final requests in the last second: " + requestsInLastSecond);
        System.out.println("Final requests in the last 10 seconds: " + requestsInLastTenSeconds);
    }
}
