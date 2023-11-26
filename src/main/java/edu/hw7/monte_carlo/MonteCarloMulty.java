package edu.hw7.monte_carlo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloMulty extends MonteCarlo {

    private int numThreads;


    public MonteCarloMulty(int numThreads, long iterations, double radius, Coordinate circleCenter) {
        super(iterations,
                radius,
                circleCenter);
        this.numThreads = numThreads;

    }

    public MonteCarloMulty(int numThreads, long iterations, double radius) {
        this(numThreads, iterations, radius, new Coordinate(0, 0));
    }

    @Override
    public void setIterations(long iterations) {
        super.setIterations(iterations);
    }

    public void setNumThreads(int numThreads) {
        this.numThreads = numThreads;
    }

    @Override
    public Duration getDurationBetween() {
        return super.getDurationBetween();
    }


    @Override
    public void solve() {
        LocalDateTime start = LocalDateTime.now();

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);


        CountDownLatch latch = new CountDownLatch(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                long pointsInsideLocal = runMonteCarlo(iterations / numThreads);
                synchronized (MonteCarloMulty.this) {
                    pointsInside += pointsInsideLocal;
                }
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();

        currentPi = calculatePi();

        LocalDateTime end = LocalDateTime.now();
        durationBetween = Duration.between(start, end);

        Report report = new Report(numThreads, currentPi, iterations, durationBetween);
        report.showReport();

        executorService.close();
    }

    private long runMonteCarlo(long iterations) {
        long pointsInsideLocal = 0;

        for (int i = 0; i < iterations; i++) {
            Coordinate coordinate = generateCoordinate();
            if (checkThatCoordinateInside(coordinate)) {
                pointsInsideLocal++;
            }
        }

        return pointsInsideLocal;
    }

    @Override
    protected double calculatePi() {
        return super.calculatePi();
    }

    @Override
    protected Coordinate generateCoordinate() {

        double x = ThreadLocalRandom.current().nextDouble(-radius, radius);
        double y = ThreadLocalRandom.current().nextDouble(-radius, radius);
        return new Coordinate(x, y);
    }

    @Override
    protected boolean checkThatCoordinateInside(Coordinate coordinate) {
        return super.checkThatCoordinateInside(coordinate);
    }


}
