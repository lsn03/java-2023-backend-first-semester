package edu.hw5;

import edu.homework.hw5.Task1ClubAnalytic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class Task1ClubAnalyticTest {

    static Stream<Arguments> validTimeProvider() {
        return Stream.of(
                Arguments.of("2022-03-12, 20:20 - 2022-03-12, 23:50", "3ч 30м"),
                Arguments.of("2022-04-01, 21:30 - 2022-04-02, 01:20", "3ч 50м"),
                Arguments.of("2022-04-01, 21:30 - 2022-04-03, 01:20", "1д 3ч 50м"),
                Arguments.of("2020-04-01, 21:30 - 2022-04-03, 01:20", "731д 3ч 50м")
        );
    }

    @ParameterizedTest
    @MethodSource("validTimeProvider")
    public void getValidTime(String source, String answer) {

        System.out.println(source + "\t ans= " + answer);
        assertEquals(answer, Task1ClubAnalytic.getTime(source));

    }

    @Test
    public void illegalArgumentChecker() {
        assertThrows(IllegalArgumentException.class, () -> {
            Task1ClubAnalytic.getTime("2022-04-02, 21:30 - 2022-04-01, 01:20");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task1ClubAnalytic.getTime("2022-04-02, 21:30 - 2022-04-01, 01:20");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task1ClubAnalytic.getTime("2022-04-02, 21:30 -   2022-04-01, 01:20");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task1ClubAnalytic.getTime("2022-04-02, 21:30  2022-04-01, 01:20");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Task1ClubAnalytic.getTime("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task1ClubAnalytic.getTime("    ");
        });
    }
    @Test
    public void nullChecker(){
        assertThrows(NullPointerException.class,() -> {
            Task1ClubAnalytic.getTime(null);
        });
    }


}
