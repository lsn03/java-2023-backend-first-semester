package edu.hw2.task1;

import edu.hw2.Task1.Expr;
import edu.hw2.Task1.Expr.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1ExprTest {
    @ParameterizedTest(name = "{0} given - {1} expected")
    @CsvSource(value = {
            "0, 0",
            "20, 20",
            "-30, -30",
            "9999, 9999"
    })
    public void testThatTheConstantWork(double value, double excpectedValue) {
//        Arrange
        var expr = new Constant(value);
//        Act
        var result = expr.evaluate();
//        Assert
        assertEquals(excpectedValue, result);
    }

    @ParameterizedTest(name = "{0} given - {1} expected")
    @CsvSource(value = {
            "0, -0",
            "20, -20",
            "-30, 30",
            "9999, -9999"
    })
    public void testThatNegateWork(double value, double excpectedValue) {
//        Arrange
        var constant = new Constant(value);
//        Act
        var action = new Negate(constant);
        var result = action.evaluate();
//        Assert
        assertEquals(excpectedValue, result);
    }

    @ParameterizedTest(name = "{0} left; {1} right; sum = {2}")
    @CsvSource(value = {
            "0, 0, 1",
            "20, 2, 400",
            "-30, 3, -27000",
            "30, 0, 1",
            "-30, 1, -30",

    })
    public void testThatExponentWork(double left, int exponent, double expectedValue) {
//        Arrange
        var left1 = new Constant(left);
//        Act
        var action = new Exponent(left1, exponent);
        var result = action.evaluate();
//        Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest(name = "{0} left; {1} right; sum = {2}")
    @CsvSource(value = {
            "0, -0, 0",
            "20, -20, 0",
            "-30, 30, 0",
            "30, 30, 60",
            "-30, -30, -60",
            "-40, 30, -10",
    })
    public void testThatAdditionWork(double left, double right, double expectedValue) {
//        Arrange
        var left1 = new Constant(left);
        var right1 = new Constant(right);
//        Act
        var action = new Addition(left1, right1);
        var result = action.evaluate();
//        Assert
        assertEquals(expectedValue, result);
    }

    @ParameterizedTest(name = "{0} left; {1} right; sum = {2}")
    @CsvSource(value = {
            "0, -0, -0",
            "0, 0, 0",
            "-0, -0, 0",
            "20, -20, -400",
            "-30, 30, -900",
            "30, 30, 900",
            "-30, -30, 900",
            "-40, 30, -1200",
    })
    public void testThatMultiplicationWork(double left, double right, double expectedValue) {
//        Arrange
        var left1 = new Constant(left);
        var right1 = new Constant(right);
//        Act
        var action = new Multiplication(left1, right1);
        var result = action.evaluate();
//        Assert
        assertEquals(expectedValue, result);
    }

}
