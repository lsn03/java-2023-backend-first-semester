package edu.hw9.stats;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class StatsCollector {
    private final Map<String, List<Double>> metrics;
    private final Lock lock;
    private final ExecutorService executorService;

    public StatsCollector(Integer threads) {
        validateThreads(threads);
        metrics = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
        executorService = Executors.newFixedThreadPool(threads);
    }

    public void push(String metricName, double[] values) {
        validateMetrics(metricName, values);

        lock.lock();
        try {

            metrics.computeIfAbsent(metricName, k -> new ArrayList<>()).addAll(Arrays.stream(values).boxed().collect(Collectors.toList()));


        } finally {
            lock.unlock();
        }
    }


    public List<Metrics> stats() {

        List<Metrics> result = new ArrayList<>();

        List<Future<Metrics>> tasks = new ArrayList<>();

        for (Map.Entry<String, List<Double>> entry : metrics.entrySet()) {
            Callable<Metrics> task = getMetricsCallable(entry);

            tasks.add(executorService.submit(task));

        }

        try {


            for (Future<Metrics> future : tasks) {
                result.add(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }

        return result;
    }

    private Callable<Metrics> getMetricsCallable(Map.Entry<String, List<Double>> entry) {
        String metricName = entry.getKey();

        List<Double> values = entry.getValue();

        return () -> {
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;

            for (double value : values) {
                sum += value;
                min = Math.min(min, value);
                max = Math.max(max, value);
            }

            double average = values.isEmpty() ? 0 : sum / values.size();

            return new Metrics(metricName, sum, average, min, max);
        };
    }

    private void validateMetrics(String metricName, double[] values) {
        Objects.requireNonNull(metricName);
        Objects.requireNonNull(values);

        if (metricName.isBlank() || metricName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (values.length == 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateThreads(Integer threads) {
        Objects.requireNonNull(threads);
        if (threads < 0 || threads > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException();
        }
    }
}
