package edu.hw7.monte_carlo;

import java.util.random.RandomGenerator;

abstract class MonteCarlo {

    protected long pointsInside;
    protected long iterations;
    protected double radius;
    protected Coordinate circleCenter;
    protected RandomGenerator randomGenerator;

    public MonteCarlo(long iterations, double radius, Coordinate circleCenter, RandomGenerator randomGenerator) {
        this.iterations = iterations;
        this.radius = radius;
        this.circleCenter = circleCenter;
        this.randomGenerator = randomGenerator;
    }

    public abstract void solve();

    protected abstract Coordinate generateCoordinate();

    protected boolean checkThatCoordinateInside(Coordinate coordinate) {
        return Math.pow(circleCenter.x() - coordinate.x(), 2) + Math.pow(circleCenter.y() - coordinate.y(), 2) <= Math.pow(radius, 2);
    }

    double calculatePi() {
        return (4d * pointsInside) / iterations;
    }
}
