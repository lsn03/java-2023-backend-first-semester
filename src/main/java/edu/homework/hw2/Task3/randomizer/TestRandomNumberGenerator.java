package edu.homework.hw2.Task3.randomizer;

public class TestRandomNumberGenerator implements RandomNumberGenerator {
    private final int[] values;
    private int index = 0;
    private final int errorFrequency;

    public TestRandomNumberGenerator(int[] values, int errorFrequency) {
        this.values = values;
        this.errorFrequency = errorFrequency;
    }

    @Override
    public int nextInt() {
        if (index < values.length) {
            return values[index++];
        }
        return 0;
    }

    @Override
    public boolean isError(int value) {
        return value % errorFrequency == 0;
    }
}
