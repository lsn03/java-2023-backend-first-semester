package edu.hw7.monte_carlo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class MonteCarloSingle extends MonteCarlo {

    public MonteCarloSingle(long iterations, double radius, Coordinate circleCenter) {
        super(iterations, radius, circleCenter);
    }

    public MonteCarloSingle(long iterations, double radius) {
        this(iterations, radius, new Coordinate(0, 0));
    }

    @Override
    public void setIterations(long iterations) {
        super.setIterations(iterations);
    }

    @Override
    public Duration getDurationBetween() {
        return super.getDurationBetween();
    }

    @Override
    public void solve() {
        LocalDateTime start = LocalDateTime.now();
        Coordinate coordinate;
        for (int i = 0; i < iterations; i++) {
            coordinate = generateCoordinate();
            if (checkThatCoordinateInside(coordinate)) {
                pointsInside++;
            }
        }
        currentPi = calculatePi();
        LocalDateTime end = LocalDateTime.now();
        durationBetween = Duration.between(start, end);
        Report report = new Report(currentPi, iterations, durationBetween);
        report.showReport();
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
    protected double calculatePi() {
        return super.calculatePi();
    }


}
