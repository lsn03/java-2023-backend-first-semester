package edu.hw2.Task3.randomizer;

public class TestRandomNumberGenerator implements RandomNumberGenerator {
    private final int[] values;
    private int index = 0;

    public TestRandomNumberGenerator(int[] values) {
        this.values = values;
    }

    @Override
    public int nextInt() {
        if (index < values.length) {
            return values[index++];
        }
        return 0;
    }
}
