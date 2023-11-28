package edu.hw7;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CounterTest {
    int numThreads = 2;
    int numIncrementsPerThread = 1_000_000;

    @Test
    public void testFunctionallity() {

        Counter counter = new Counter(numThreads, numIncrementsPerThread);

        int expectedValue = numThreads * numIncrementsPerThread;
        assertDoesNotThrow(counter::runIncrementThreads);
        assertEquals(expectedValue, counter.getValue());
    }

}
