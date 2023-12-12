package edu.hw10.task_2;

public class FibImpl implements FibCalculator {


    public static final int START = 3;

    @Override
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
}
