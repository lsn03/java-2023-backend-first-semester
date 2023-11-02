package edu.hw4;

public class Range {
    private int min;
    private int max;

    public Range(int min, int max) {
        validate(min, max);

        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        validate(min, this.max);
        this.min = min;
    }

    public void setMax(int max) {
        validate(this.min, max);
        this.max = max;
    }

    private void validate(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min cannot be more then Max");
        }
        if (min < 0) {
            throw new IllegalArgumentException("Min cannot be negative");
        }
    }

}
