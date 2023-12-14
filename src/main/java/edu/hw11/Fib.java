package edu.hw11;

public class Fib {
    public static final int START = 3;
    public long fib(int number) {
        if (number <= 1) {
            return number;
        }
        long fib1 = 1;
        long fib2 = 1;
        for (int i = START; i <= number; i++) {
            var temp = fib1 + fib2;
            fib1 = fib2;
            fib2 = temp;
        }
        return fib2;
    }

    public static void main(String[] args) {
        new Fib().fib(5);
    }
}
