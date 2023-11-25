package edu.hw7.monte_carlo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class MonteCarloSingle extends MonteCarlo {

    public MonteCarloSingle(long iterations, double radius, Coordinate circleCenter) {
        super(iterations, radius, circleCenter, new Random());
    }

    public MonteCarloSingle(long iterations, double radius) {
        this(iterations, radius, new Coordinate(0, 0));
    }

    public void setIterations(long iterations) {
        this.iterations = iterations;
    }

    @Override
    public void solve() {
        Coordinate coordinate;

        for (int i = 0; i < iterations; i++) {
            coordinate = generateCoordinate();
            if (checkThatCoordinateInside(coordinate)) {
                pointsInside++;
            }
        }
        double currentPi = calculatePi();
        System.out.println(currentPi + "\t" + (Math.PI - currentPi));
    }

    @Override
    protected Coordinate generateCoordinate() {
        double x = randomGenerator.nextDouble(-radius, radius);
        double y = randomGenerator.nextDouble(-radius, radius);
        return new Coordinate(x, y);
    }

    @Override
    protected boolean checkThatCoordinateInside(Coordinate coordinate) {
        return super.checkThatCoordinateInside(coordinate);
    }

    @Override
    double calculatePi() {
        return super.calculatePi();
    }

    public static void main(String[] args) {
        MonteCarlo monteCarlo = new MonteCarloSingle(2, 1_000_000_000);
        LocalDateTime start = LocalDateTime.now();
        monteCarlo.solve();
        LocalDateTime end = LocalDateTime.now();
        System.out.println(Duration.between(start, end));
    }
}