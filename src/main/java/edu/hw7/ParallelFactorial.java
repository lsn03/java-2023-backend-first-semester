package edu.hw7;

import java.util.Objects;
import java.util.OptionalLong;
import java.util.stream.LongStream;

public final class ParallelFactorial {
    private static final int MAX_N = 20;
    private static final int MIN_N = 1;

    private ParallelFactorial() {
    }

    public static OptionalLong getFactorial(Integer n) {
        validate(n);

        return LongStream.rangeClosed(1, n).parallel().reduce((left, right) -> Math.multiplyExact(left, right));

    }

    private static void validate(Integer n) {
        Objects.requireNonNull(n);
        if (n < MIN_N || n > MAX_N) {
            throw new IllegalArgumentException();
        }
    }

}
