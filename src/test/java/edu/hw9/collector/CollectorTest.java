package edu.hw9.collector;

import edu.hw9.stats.StatsCollector;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CollectorTest {

    public static final int N = 1_000_000;

    @Test
    public void failed() {
        assertThrows(NullPointerException.class, () -> new StatsCollector(null));
        assertThrows(IllegalArgumentException.class, () -> new StatsCollector(-5));
        assertThrows(IllegalArgumentException.class, () -> new StatsCollector(100));

        var collector = new StatsCollector(1);
        assertThrows(NullPointerException.class, () -> collector.push(null, null));
        assertThrows(NullPointerException.class, () -> collector.push("def", null));
        assertThrows(IllegalArgumentException.class, () -> collector.push("", new double[]{}));
    }

    @Test
    public void successTests() {
        int threads = 2;
        StatsCollector collector = new StatsCollector(threads);

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < N; i++) {
            int finalI = i;
            executorService.submit(() -> {

                String metricName = "metric " + finalI + " " + Thread.currentThread().getName();
                double[] values = generate();
                collector.push(metricName, values);


            });
        }
        executorService.shutdown();

        try {

            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        } catch (InterruptedException e) {
           throw new RuntimeException(e);
        }
        var stats = collector.stats();
        assertEquals(N, stats.size());
    }


    private static double[] generate() {
        int size = 100;
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = ThreadLocalRandom.current().nextDouble();
        }
        return arr;
    }
}
