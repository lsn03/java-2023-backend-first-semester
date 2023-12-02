package edu.hw8.my_thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    private final int numThreads;
    private final Thread[] threads;
    private boolean isRunning = true;
    private final BlockingQueue<Runnable> queue;

    public FixedThreadPool(Integer numThreads) {
        validate(numThreads);
        this.numThreads = numThreads;
        threads = new Thread[numThreads];
        queue = new LinkedBlockingQueue<>();
    }

    private void validate(Integer numThreads) {
        if (numThreads < 0 || numThreads > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void start() {
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                while (isRunning) {
                    try {
                        var task = queue.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isRunning) {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        isRunning = false;
        for (var thread : threads) {
            thread.interrupt();
        }
    }

    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }
}
