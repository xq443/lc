package Snowflake.HitCounter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class HitCounterLock {
    private final int[] times;
    private final int[] counts;
    private final Object lock; // Lock object for synchronization

    public HitCounterLock() {
        times = new int[300];  // Store timestamps
        counts = new int[300]; // Store hit counts per second
        lock = new Object();
    }

    public void hit(int timestamp) {
        synchronized (lock) {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                // Reset the slot if it holds an old timestamp
                times[index] = timestamp;
                counts[index] = 1;
            } else {
                // Increment count for the same timestamp
                counts[index]++;
            }
        }
    }

    public int getHits(int timestamp) {
        int totalHits = 0;
        synchronized (lock) {
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) { // Count only valid hits
                    totalHits += counts[i];
                }
            }
        }
        return totalHits;
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

        // Single-threaded test cases
//        counter.hit(1);
//        counter.hit(2);
//        counter.hit(3);
//        System.out.println("Hits at t=4: " + counter.getHits(4)); // Expected: 3
//
//        counter.hit(300);
//        System.out.println("Hits at t=300: " + counter.getHits(300)); // Expected: 4
//
//        System.out.println("Hits at t=301: " + counter.getHits(301)); // Expected: 3 (hit at t=1 expires)

        // Multithreaded test case
        int numThreads = 5;       // Number of threads running in parallel
        int hitsPerThread = 100;  // Each thread sends 100 hits
        ExecutorService executor = Executors.newFixedThreadPool(numThreads); // The pool reuses threads for executing tasks instead of creating new ones every time.
        CountDownLatch latch = new CountDownLatch(numThreads); // ensure that all threads complete their execution before we check the hit count.

        for (int i = 0; i < numThreads; i++) {
            executor.execute(() -> {
                for (int j = 0; j < hitsPerThread; j++) {
                    counter.hit(500); // Simulating concurrent hits at timestamp 500
                }
                latch.countDown();
            });
        }

        try {
            latch.await(); // Wait for all threads to complete
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        executor.shutdown();
        System.out.println("Hits at t=500 after multithreading: " + counter.getHits(500));
        // Expected: 5 * 100 = 500
    }
}
// Threads block indefinitely if contention is high.
// No timeout or fairness control.
// No way to interrupt a waiting thread.