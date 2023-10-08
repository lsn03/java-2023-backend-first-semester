package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class Task6Test {

    @ParameterizedTest(name = "{0} given - {1} expected")
    @CsvSource(value = {
            "3524, 3",
            "6621, 5",
            "6554, 4",
            "1234, 3"
    })
    public void testExampleOneFromTinkoff(int number, int expected) {
//		Arrange
        Task6 task6 = new Task6();
//		Act
        int result = task6.countK(number);
//		Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest(name = "Test # {index}: {0}")
    @CsvSource(value = {
            "-3",
            "-6621",
            "65541",
            "12340",
            "123",
            "999",
            "10000"
    })
    public void testThatIllegalArgumentException(int number) {
//		Arrange
        Task6 task6 = new Task6();
//		Act
        assertThrows(IllegalArgumentException.class, () -> {
            task6.countK(number);
        });
//		Assert

    }
}
