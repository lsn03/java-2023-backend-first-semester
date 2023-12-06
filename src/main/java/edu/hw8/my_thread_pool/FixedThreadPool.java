package edu.hw8.my_thread_pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedThreadPool implements ThreadPool {

    private final int numThreads;
    private final Thread[] threads;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
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
        isRunning.set(true);
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(this::processTask);
            threads[i].start();
        }
    }


    @Override
    public void execute(Runnable runnable) {
        if (isRunning.get() && !Thread.currentThread().isInterrupted()) {
            try {
                queue.put(runnable);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void close() {
        isRunning.set(false);
        for (var thread : threads) {
            thread.interrupt();
        }
    }


    public static FixedThreadPool create(int numThreads) {
        return new FixedThreadPool(numThreads);
    }

    private void processTask() {
        while (isRunning.get()) {
            try {
                var task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
