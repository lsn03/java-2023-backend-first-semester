package edu.hw8;

import edu.hw8.hacker.MultiThreadPasswordGenerator;
import edu.hw8.hacker.SingleThreadPasswordGenerator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SingleMultiThreadTest {
    private final Map<String, String> expectedFoundPasswords = Map.of(
            "first_pass", "1234",
            "second_pass", "abcd"
    );

    @Test
    public void testSingle() {
        SingleThreadPasswordGenerator singleThreadPasswordGenerator = new SingleThreadPasswordGenerator();
        LocalDateTime start = LocalDateTime.now();
        singleThreadPasswordGenerator.launch();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("length = 4, threads =  1, time= " + duration.toMillis() / 1000.0 + " s");
        assertEquals(expectedFoundPasswords, singleThreadPasswordGenerator.getFoundPasswords());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1",
            "2",


    })
    public void testMulti(int threads) {
        MultiThreadPasswordGenerator generator = new MultiThreadPasswordGenerator(threads);
        LocalDateTime start = LocalDateTime.now();
        generator.launch();
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("length = 4, threads = " + threads + " , time = " + duration.toMillis() / 1000.0 + " s");
        assertEquals(expectedFoundPasswords, generator.getFoundPasswords());
    }

    @Test
    public void failedSingleTest() {
        assertThrows(NullPointerException.class, () -> {
            new SingleThreadPasswordGenerator(1, null);
        });
        assertThrows(NullPointerException.class, () -> {
            new SingleThreadPasswordGenerator(null, new HashMap<>());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new SingleThreadPasswordGenerator(50000, Map.of("1", "1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new SingleThreadPasswordGenerator(-2, Map.of("1", "1"));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new SingleThreadPasswordGenerator(1, Map.of());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new SingleThreadPasswordGenerator(1, Map.of());
        });
    }

    @Test
    public void failedMultiTest() {
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadPasswordGenerator(null);
        });
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadPasswordGenerator(null, null, null);
        });
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadPasswordGenerator(1, 1, null);
        });
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadPasswordGenerator(1, null, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadPasswordGenerator(50000, 1, Map.of("1", "1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadPasswordGenerator(-50000, 1, Map.of("1", "1"));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadPasswordGenerator(2, -1, Map.of("1", "1"));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadPasswordGenerator(2, 15000, Map.of());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadPasswordGenerator(2, 1, Map.of());
        });


    }
}
