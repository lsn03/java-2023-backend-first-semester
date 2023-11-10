package edu.hw5;

import edu.homework.hw5.Task7;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task7Test {
    @ParameterizedTest
    @CsvSource(value = {
            "0000",
            "00011100",
            "0000001",
            "010",
            "100",
            "110",
            "0101111101",
            "1001111101",
            "1101111101",

    })
    public void firstTaskIsValid(String rawString) {
        assertTrue(Task7.validateMoreThan3SymbolsAndThirdIsZero(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0010",
            "01111100",
            "01",
            "011",
            "10",
            "1",

    })
    public void firstTaskIsNotValid(String rawString) {
        assertFalse(Task7.validateMoreThan3SymbolsAndThirdIsZero(rawString));
    }


    @ParameterizedTest
    @CsvSource(value = {
            "0000",
            "00011100",
            "010",
            "1001111101",
            "11",
    })
    public void secondTaskIsValid(String rawString) {
        assertTrue(Task7.validateStringBeginAndEndWithEqualsSymbols(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0011",
            "11111100",
            "01",
            "011",
            "10",


    })
    public void secondTaskIsNotValid(String rawString) {
        assertFalse(Task7.validateStringBeginAndEndWithEqualsSymbols(rawString));
    }

    @Test
    public void nullChecker() {
        assertThrows(NullPointerException.class, () -> {
            Task7.validateMoreThan3SymbolsAndThirdIsZero(null);
        });

        assertThrows(NullPointerException.class, () -> {
            Task7.validateStringBeginAndEndWithEqualsSymbols(null);
        });

        assertThrows(NullPointerException.class, () -> {
            Task7.validateStringLenghtInRange(null);
        });
    }
    @Test
    public void emptyBlankChecker() {
        String blank = "   ";
        String empty = "";
        assertThrows(IllegalArgumentException.class, () -> {
            Task7.validateMoreThan3SymbolsAndThirdIsZero(blank);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task7.validateMoreThan3SymbolsAndThirdIsZero(empty);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task7.validateStringBeginAndEndWithEqualsSymbols(blank);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task7.validateStringBeginAndEndWithEqualsSymbols(empty);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task7.validateStringLenghtInRange(blank);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task7.validateStringLenghtInRange(empty);
        });
    }
    @ParameterizedTest
    @CsvSource(value = {
            "000",
            "001",
            "01",
            "1",
            "11",
    })
    public void thirdTaskIsValid(String rawString) {
        assertTrue(Task7.validateStringLenghtInRange(rawString));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0011",
            "11111100",
            "01a",
            "saba",
            "10vvv",

    })
    public void thirdTaskIsNotValid(String rawString) {
        assertFalse(Task7.validateStringLenghtInRange(rawString));
    }

}
