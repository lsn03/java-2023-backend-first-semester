package edu.hw7.monte_carlo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloMulty extends MonteCarlo {


    public MonteCarloMulty(long iterations, double radius, Coordinate circleCenter) {
        super(iterations,
                radius,
                circleCenter,
                null);
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

    @Override
    public void solve(){

        int numThreads = 6;
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

        double currentPi = calculatePi();

        System.out.println(currentPi + "\t" + (Math.PI - currentPi));

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
    double calculatePi() {
        return super.calculatePi();
    }

    public static void main(String[] args) {
        MonteCarlo monteCarlo = new MonteCarloMulty(1_000_000_000, 2, new Coordinate(0, 0));
        LocalDateTime start = LocalDateTime.now();
        monteCarlo.solve();
        LocalDateTime end = LocalDateTime.now();
        System.out.println(Duration.between(start, end));
    }
}
