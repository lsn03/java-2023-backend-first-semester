package edu.hw1;


public class Task7 {
    private double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    private int correctModulo(int a, int b) {
        return (b + a % b) % b;
    }

    private void checkTheCorrectInputData(int n, int shift) {
        final int MAX_AVAILABLE_SHIFT = 32;
        if (n < 0 || shift < 0 || shift > MAX_AVAILABLE_SHIFT) {
            throw new IllegalArgumentException("The value n and shift must be positive");
        }
    }

    public int rotateLeft(int n, int shift) {
        checkTheCorrectInputData(n, shift);
        int res = 0;
        int module = (int) Math.floor(log2(n)) + 1;
        for (int i = 0; i < module; i++) {
            res |= ((n >> i) & 1) << ((i + shift) % module);

        }
        return res;
    }

    public int rotateRight(int n, int shift) {
        checkTheCorrectInputData(n, shift);

        int res = 0;

        int module = (int) Math.floor(log2(n)) + 1;
        for (int i = 0; i < module; i++) {
            res |= ((n >> i) & 1) << (correctModulo(i - shift, module));
        }
        return res;
    }
}
