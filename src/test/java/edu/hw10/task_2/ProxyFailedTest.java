package edu.hw10.task_2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class ProxyFailedTest {
    @Test
    public void failedTest() {

        assertThrows(NullPointerException.class, () -> CacheProxy.create(null, FibCalculator.class));
        assertThrows(NullPointerException.class, () -> CacheProxy.create(new Object(), null));
        assertThrows(NullPointerException.class, () -> CacheProxy.create(new Object(), FibCalculator.class, null));

    }
}
