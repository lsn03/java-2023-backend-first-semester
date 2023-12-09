package edu.hw9.stats;

public class Metrics {
    private final String metricName;
    private final double sum;
    private final double average;
    private final double max;
    private final double min;

    public Metrics(String metricName, double sum, double average, double max, double min) {
        this.metricName = metricName;
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public String getMetricName() {
        return metricName;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return average;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Metrics{"
                + "metricName='" + metricName + '\''
                + ", sum=" + sum
                + ", average=" + average
                + ", max=" + max
                + ", min=" + min
                + '}';
    }
}
