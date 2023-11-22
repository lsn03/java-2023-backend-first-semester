package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task8Test {
    @ParameterizedTest
    @CsvSource(value = {
            "111",
            "100",
            "1",
            "10001",
    })
    public void firstTaskValid(String rawString) {
        assertTrue(Task8.firstTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "11",
            "1001",
            "1a",
            "1000112",
            "1000",
    })
    public void firstTaskNotValid(String rawString) {
        assertFalse(Task8.firstTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "000",
            "1000",
            "10100",
            "01001",
    })
    public void thirdTaskValid(String rawString) {
        assertTrue(Task8.thirdTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "11",
            "1001",
            "1a",
            "10112",
            "10000",
            "1a000",
    })
    public void thirdTaskNotValid(String rawString) {
        assertFalse(Task8.thirdTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "000",
            "1000",
            "10100",
            "01001",
    })
    public void fourthTaskValid(String rawString) {
        assertTrue(Task8.fourthTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "11",
            "1001f",
            "111f",
            "111",
            "10000sa",
            "1a000a",
    })
    public void fourthTaskNotValid(String rawString) {
        assertFalse(Task8.fourthTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "101",
            "1010",
            "101",
            "101010101",
            "1",
            "10001"
    })
    public void sevenTaskValid(String rawString) {
        assertTrue(Task8.sevenTask(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "01011",
            "101f",
            "110",
            "10000sa",
            "1a000a",
            "100110",
            "100111111110",
    })
    public void sevenTaskNotValid(String rawString) {
        assertFalse(Task8.sevenTask(rawString));
    }
}
