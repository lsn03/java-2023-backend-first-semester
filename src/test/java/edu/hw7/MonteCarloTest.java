package edu.hw7;

import edu.hw7.monte_carlo.MonteCarlo;
import edu.hw7.monte_carlo.MonteCarloMulty;
import edu.hw7.monte_carlo.MonteCarloSingle;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MonteCarloTest {
    @ParameterizedTest
    @CsvSource(value = {
            "10000000",
            "100000000",
            "1000000000",
    })
    public void testSingleMonte(long iterations) {
        MonteCarlo monteCarlo = new MonteCarloSingle(iterations, 2);
        assertDoesNotThrow(monteCarlo::solve);
        assertTrue(Math.abs(monteCarlo.getCurrentPi() - Math.PI) <= 1e-2);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "10000000, 2",
            "10000000, 3",
            "10000000, 4",
            "10000000, 5",
            "10000000, 6",
            "100000000, 2",
            "100000000, 3",
            "100000000, 4",
            "100000000, 5",
            "100000000, 6",
            "1000000000, 2",
            "1000000000, 3",
            "1000000000, 4",
            "1000000000, 5",
            "1000000000, 6",
    })
    public void testMultyMonte(long iterations, int treads) {
        MonteCarlo monteCarlo = new MonteCarloMulty(treads, iterations, 2);
        assertDoesNotThrow(monteCarlo::solve);
        assertTrue(Math.abs(monteCarlo.getCurrentPi() - Math.PI) <= 1e-2);
    }
}
