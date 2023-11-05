package edu.homework.hw2.Task3.randomizer;

import java.util.Random;

public class RealRandomNumberGenerator implements RandomNumberGenerator {
    private final Random random = new Random();
    private final int errorFrequency;

    public RealRandomNumberGenerator(int errorFrequency) {
        this.errorFrequency = errorFrequency;
    }

    @Override
    public int nextInt() {
        return random.nextInt();
    }

    @Override
    public boolean isError(int value) {
        return value % errorFrequency == 0;
    }
}
