package edu.hw2.Task3.randomizer;

import java.util.Random;

public class RealRandomNumberGenerator implements RandomNumberGenerator {
    private final Random random = new Random();

    @Override
    public int nextInt() {
        return random.nextInt();
    }
}
