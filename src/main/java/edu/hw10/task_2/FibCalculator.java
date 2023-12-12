package edu.hw10.task_2;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
