package edu.hw7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    private final AtomicInteger value = new AtomicInteger(0);
    private final int numThreads;
    private final int incrementsPerThread;

    public Counter(int numThreads, int incrementsPerThread) {
        this.numThreads = numThreads;
        this.incrementsPerThread = incrementsPerThread;
    }

    public int getValue() {
        return value.get();
    }

    public void runIncrementThreads() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(numThreads);

        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    value.incrementAndGet();
                }
                latch.countDown();
            });
            threads[i].start();
        }

        latch.await();
    }
}
