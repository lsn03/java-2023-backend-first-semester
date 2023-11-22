package edu.hw5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

public class Task2Friday13Test {
    private int year;
    List<LocalDate> expectedList;

    @Test
    public void validateFriday13At1925Year() {
        year = 1925;
        expectedList = List.of(
                LocalDate.of(year, 2, 13),
                LocalDate.of(year, 3, 13),
                LocalDate.of(year, 11, 13)
        );
        var actual = Task2Friday13.getListOfFriday13(year);
        assertEquals(expectedList, actual);
    }

    @Test
    public void validateFriday13At2023Year() {
        year = 2023;
        expectedList = List.of(
                LocalDate.of(year, 1, 13),
                LocalDate.of(year, 10, 13)

        );
        var actual = Task2Friday13.getListOfFriday13(year);
        assertEquals(expectedList, actual);
    }

    @Test
    public void getFriday13AtConcreteYear() {
        String date = "2020-01-01";
        LocalDate expected = LocalDate.of(2020, 3, 13);
        var actual = Task2Friday13.getNextFriday13(date);
        assertEquals(expected, actual);
    }

    @Test
    public void getFriday13InNextYear() {
        String date = "2023-11-13";
        LocalDate expected = LocalDate.of(2024, 9, 13);
        var actual = Task2Friday13.getNextFriday13(date);
        assertEquals(expected, actual);
    }

    @Test
    public void negativeTests() {


        assertThrows(NullPointerException.class, () -> {
            Task2Friday13.getListOfFriday13(null);
        });

        assertThrows(NullPointerException.class, () -> {
            Task2Friday13.getNextFriday13(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task2Friday13.getListOfFriday13(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task2Friday13.getNextFriday13(" ");

        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task2Friday13.getNextFriday13("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Task2Friday13.getNextFriday13("abra");
        });
    }
}
