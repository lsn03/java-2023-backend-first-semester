package edu.hw8.my_thread_pool;

import java.util.Objects;

public final class Fibonnachi {
    private static final int MAX_N = 40;

    private Fibonnachi() {

    }

    public static long calculate(Integer n) {
        validate(n);
        return calculateFibonacci(n);
    }

    private static void validate(Integer n) {
        Objects.requireNonNull(n);
        if (n < 0 || n > MAX_N) {
            throw new IllegalArgumentException();
        }
    }

    private static long calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
    }
}
