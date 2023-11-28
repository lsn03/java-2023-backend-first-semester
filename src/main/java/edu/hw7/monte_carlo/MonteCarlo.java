package edu.hw7.monte_carlo;

import java.time.Duration;

public abstract class MonteCarlo {

    public static final double MAGIC_NUMBER = 4d;
    protected long pointsInside;
    protected long iterations;
    protected double radius;
    protected double currentPi;
    protected Coordinate circleCenter;
    protected Duration durationBetween;

    public MonteCarlo(long iterations, double radius, Coordinate circleCenter) {
        this.iterations = iterations;
        this.radius = radius;
        this.circleCenter = circleCenter;

    }

    public abstract void solve();

    public void setIterations(long iterations) {
        this.iterations = iterations;
    }

    public Duration getDurationBetween() {
        return durationBetween;
    }

    public double getCurrentPi() {
        return currentPi;
    }

    protected abstract Coordinate generateCoordinate();

    protected boolean checkThatCoordinateInside(Coordinate coordinate) {
        return (Math.pow(circleCenter.x() - coordinate.x(), 2)
                + Math.pow(circleCenter.y() - coordinate.y(), 2)) <= Math.pow(radius, 2);
    }

    protected double calculatePi() {
        return (MAGIC_NUMBER * pointsInside) / iterations;
    }
}
