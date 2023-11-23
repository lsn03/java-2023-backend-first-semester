package edu.hw7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger value = new AtomicInteger(0);
    private final int NUM_THREADS;
    private final int INCREMENTS_PER_THREAD;

    public Counter(int NUM_THREADS, int INCREMENTS_PER_THREAD) {
        this.NUM_THREADS = NUM_THREADS;
        this.INCREMENTS_PER_THREAD = INCREMENTS_PER_THREAD;
    }

    public int getValue() {
        return value.get();
    }

    public void runIncrementThreads() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(NUM_THREADS);

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    value.incrementAndGet();
                }
                latch.countDown();
            });
            threads[i].start();
        }

        latch.await();
    }
}
