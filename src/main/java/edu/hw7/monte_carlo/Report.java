package edu.hw7.monte_carlo;

import java.time.Duration;

public class Report {
    private final int numThreads;
    private final double myPi;
    private final long iterations;
    private final double seconds;
    public static final String TEMPLATE = "Количество потоков: %d," +
            " число итераций: %d," +
            " погрешность: %.15f," +
            " время выполнения: %f секунд";

    public Report(int numThreads, double myPi, long iterations, Duration duration) {
        this.numThreads = numThreads;
        this.myPi = myPi;
        this.iterations = iterations;
        this.seconds = duration.toMillis() / 1000.0;
    }

    public Report(double myPi, long iterations, Duration duration) {
        this(1, myPi, iterations, duration);
    }

    public void showReport() {
        String result = String.format(TEMPLATE, numThreads, iterations, myPi - Math.PI, seconds);
        System.out.println(result);
    }
}
