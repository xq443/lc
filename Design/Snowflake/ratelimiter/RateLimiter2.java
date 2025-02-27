package Snowflake.ratelimiter;

import java.util.*;
import java.util.concurrent.*;

public class RateLimiter2 {
    private final ConcurrentHashMap<String, Deque<Long>> requestMapPerSecond;
    private final ConcurrentHashMap<String, Deque<Long>> requestMapPerTenSeconds;

    private final int maxRequestsPerSecond = 3;
    private final int maxRequestsPerTenSeconds = 20;

    private final long secondWindow = 1000L;  // 1 second in milliseconds
    private final long tenSecondWindow = 10000L;  // 10 seconds in milliseconds

    public RateLimiter2() {
        requestMapPerSecond = new ConcurrentHashMap<>();
        requestMapPerTenSeconds = new ConcurrentHashMap<>();
    }

    // Method to check if a request can be processed
    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        // Initialize the deque if not present
        requestMapPerSecond.putIfAbsent(userId, new LinkedList<>());
        requestMapPerTenSeconds.putIfAbsent(userId, new LinkedList<>());

        Deque<Long> requestsPerSecond = requestMapPerSecond.get(userId);
        Deque<Long> requestsPerTenSeconds = requestMapPerTenSeconds.get(userId);

        // Remove outdated timestamps from requestsPerSecond (older than 1 second)
        while (!requestsPerSecond.isEmpty() && currentTime - requestsPerSecond.peek() >= secondWindow) {
            requestsPerSecond.poll();
        }

        // Remove outdated timestamps from requestsPerTenSeconds (older than 10 seconds)
        while (!requestsPerTenSeconds.isEmpty() && currentTime - requestsPerTenSeconds.peek() >= tenSecondWindow) {
            requestsPerTenSeconds.poll();
        }

        // Check if the number of requests in the last second exceeds the limit
        if (requestsPerSecond.size() >= maxRequestsPerSecond) {
            return false;  // Reject request
        }

        // Check if the number of requests in the last 10 seconds exceeds the limit
        if (requestsPerTenSeconds.size() >= maxRequestsPerTenSeconds) {
            return false;  // Reject request
        }

        // Record the request timestamp in both time windows
        requestsPerSecond.add(currentTime);
        requestsPerTenSeconds.add(currentTime);

        return true;  // Allow request
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter2 rateLimiter = new RateLimiter2();

        // Simulate concurrent requests from different users
        Runnable task = () -> {
            for (int i = 0; i < 5; i++) {
                String userId = "user1";  // Example user ID
                boolean isAllowed = rateLimiter.allowRequest(userId);
                System.out.println(userId + " Request " + (i + 1) + ": " + (isAllowed ? "Allowed" : "Rejected"));
                try {
                    Thread.sleep(200);  // Simulate a delay between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Start 3 threads simulating requests for the same user
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();

        // Wait for threads to complete
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
