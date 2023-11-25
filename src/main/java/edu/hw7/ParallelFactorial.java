package edu.hw7;

import java.util.Objects;
import java.util.OptionalLong;
import java.util.stream.LongStream;

public final class ParallelFactorial {
    private ParallelFactorial() {
    }

    public static OptionalLong getFactorial(Integer n) {
        validate(n);

        return LongStream.rangeClosed(1, n).parallel().reduce((left, right) -> Math.multiplyExact(left, right));

    }

    private static void validate(Integer n) {
        Objects.requireNonNull(n);
        if (n < 1 || n > 20) {
            throw new IllegalArgumentException();
        }
    }

}
