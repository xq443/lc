package Snowflake.HitCounter;

import java.util.concurrent.locks.ReentrantLock;

class HitCounterReentrantLock {
    private final int[] times;
    private final int[] counts;
    private final ReentrantLock lock;  // Mutex lock

    public HitCounterReentrantLock() {
        times = new int[300];  // Store timestamps
        counts = new int[300]; // Store hit counts per second
        lock = new ReentrantLock();
    }

    public void hit(int timestamp) {
        lock.lock(); // Acquire lock first
        try {
            int index = timestamp % 300;
            if (times[index] != timestamp) {
                times[index] = timestamp;
                counts[index] = 1;
            } else {
                counts[index]++;
            }
        } finally {
            lock.unlock(); // Always release lock
        }
    }

    public int getHits(int timestamp) {
        lock.lock(); // Acquire lock in the same order
        try {
            int totalHits = 0;
            for (int i = 0; i < 300; i++) {
                if (timestamp - times[i] < 300) {
                    totalHits += counts[i];
                }
            }
            return totalHits;
        } finally {
            lock.unlock(); // Always release lock
        }
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();

        // Simulating multithreaded access
        Runnable task1 = () -> {
            for (int i = 0; i < 100; i++) {
                counter.hit(i);
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("Hits at " + i + ": " + counter.getHits(i));
            }
        };

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();
    }
}

// Interruptible locking prevents deadlocks.
// More control with tryLock() and lockInterruptibly().