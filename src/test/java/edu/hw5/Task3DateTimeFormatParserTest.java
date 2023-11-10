package edu.hw5;

import edu.homework.hw5.Task3DateFormatParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import java.util.Optional;

public class Task3DateTimeFormatParserTest {
    /*

2020-10-10
2020-12-2
1/3/1976
1/3/20
tomorrow
today
yesterday
1 day ago
2234 days ago



    */
    private String date;

    //     "today",
//             "yesterday",
//             "1 day ago",
//             "2234 days ago",
    @Test
    public void tomorrowDateSuccessfullyParsed() {
        date = "tomorrow";
        Optional<LocalDate> expected = Optional.of(LocalDate.now().plusDays(1));

        var result = Task3DateFormatParser.parseDate(date);

        assertEquals(expected, result);
    }

    @Test
    public void todayDateSuccessfullyParsed() {
        date = "today";
        Optional<LocalDate> expected = Optional.of(LocalDate.now());

        var result = Task3DateFormatParser.parseDate(date);

        assertEquals(expected, result);
    }

    @Test
    public void yesterdayDateSuccessfullyParsed() {
        date = "yesterday";
        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(1));

        var result = Task3DateFormatParser.parseDate(date);

        assertEquals(expected, result);
    }

    @Test
    public void oneDayAgoDateSuccessfullyParsed() {
        date = "1 day ago";
        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(1));

        var result = Task3DateFormatParser.parseDate(date);

        assertEquals(expected, result);
    }

    @Test
    public void nAgoDateSuccessfullyParsed() {
        date = "122 days ago";
        Optional<LocalDate> expected = Optional.of(LocalDate.now().minusDays(122));

        var result = Task3DateFormatParser.parseDate(date);

        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "2020-10-10, 2020-10-10",
            "2020-12-2, 2020-12-02",
            "1/3/1976, 1976-03-01",
            "1/3/20, 2020-03-01",
    })
    public void dateSuccessfullyParsed(String source, String expectedString) {
        Optional<LocalDate> expected = Optional.of(LocalDate.parse(expectedString));

        var result = Task3DateFormatParser.parseDate(source);

        assertEquals(expected, result);
    }

    @Test
    public void wrongFormat() {
        var result = Task3DateFormatParser.parseDate("abpoba");
        assertFalse(result.isPresent());


    }

    @Test
    public void negativeTests() {
        assertThrows(NullPointerException.class, () -> {
            Task3DateFormatParser.parseDate(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task3DateFormatParser.parseDate(" ");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task3DateFormatParser.parseDate("");
        });
    }
}
