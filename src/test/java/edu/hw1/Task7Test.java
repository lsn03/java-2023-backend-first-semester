package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class Task7Test {

    @ParameterizedTest(name = "Test # {index}: n = {0}, shift = {1}")
    @CsvSource(value = {
            "-3, 1",
            "-6621, 4",
            "65541, -5",
            "12340, -1 ",
            "-123, -5",
            "123, 33",
    })
    public void testThatIllegalArgumentException(int n, int shift) {
//		Arrange
        Task7 task7 = new Task7();
//		Act
        assertThrows(IllegalArgumentException.class, () -> {
            task7.rotateLeft(n, shift);
        });
//		Assert
    }

    @ParameterizedTest(name = "Test # {index}: n = {0}, shift = {1}")
    @CsvSource(value = {
            "8, 1, 4",
            "17, 1, 24",
            "380, 1, 190",
            "380, 2, 95",
            "380, 3, 303",

    })
    public void testThatRotateRightWorkCorrect(int n, int shift, int exceptedResult) {
//		Arrange
        Task7 task7 = new Task7();
//		Act
        int res = task7.rotateRight(n, shift);
//		Assert
        assertEquals(exceptedResult, res);
    }

    @ParameterizedTest(name = "Test # {index}: n = {0}, shift = {1}")
    @CsvSource(value = {
            "16, 1, 1",
            "17, 2, 6",
            "37572, 1, 9609",
            "37572, 2, 19218",
            "37572, 3, 38436",

    })
    public void testThatRotateLeftWorkCorrect(int n, int shift, int exceptedResult) {
//		Arrange
        Task7 task7 = new Task7();
//		Act
        int res = task7.rotateLeft(n, shift);
//		Assert
        assertEquals(exceptedResult, res);
    }
}
