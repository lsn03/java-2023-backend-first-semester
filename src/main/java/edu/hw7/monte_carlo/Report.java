package edu.hw7.monte_carlo;

import java.time.Duration;

public class Report {
    public static final double SECONDS_IN_MILLIS = 1000.0;
    public static final int DEFAULT_NUM_THREADS = 1;
    private final int numThreads;
    private final double myPi;
    private final long iterations;
    private final double seconds;
    public static final String TEMPLATE = "Количество потоков: %d,"
            + " число итераций: %d,"
            + " погрешность: %.15f,"
            + " время выполнения: %f секунд";

    public Report(int numThreads, double myPi, long iterations, Duration duration) {
        this.numThreads = numThreads;
        this.myPi = myPi;
        this.iterations = iterations;
        this.seconds = duration.toMillis() / SECONDS_IN_MILLIS;
    }

    public Report(double myPi, long iterations, Duration duration) {
        this(DEFAULT_NUM_THREADS, myPi, iterations, duration);
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void showReport() {
        String result = String.format(TEMPLATE, numThreads, iterations, myPi - Math.PI, seconds);
        System.out.println(result);
    }
}
