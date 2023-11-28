package edu.hw7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FactorialTest {
    @ParameterizedTest
    @CsvSource(value = {
            "5, 120",
            "6, 720",
            "20, 2432902008176640000",
    })
    public void testFactorial(int n, Long excepted) {
        assertEquals(excepted, ParallelFactorial.getFactorial(n).getAsLong());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "25",
            "21",
            "0",
            "-20",
            "50",
    })
    public void illegalArgException(int n) {
        assertThrows(IllegalArgumentException.class, () -> {
            ParallelFactorial.getFactorial(n);
        });
    }

    @Test
    public void nullPointerException() {
        assertThrows(NullPointerException.class, () -> ParallelFactorial.getFactorial(null));
    }
}
