package edu.hw8.my_thread_pool;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable);
}
