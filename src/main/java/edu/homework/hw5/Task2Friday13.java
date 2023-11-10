package edu.homework.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Task2Friday13 {
    private Task2Friday13() {

    }

    private final static int MAX_MONTH_NUMERATION = 12;
    private final static int MIN_MONTH_NUMERATION = 1;
    private final static int FRIDAY_13_DAY = 13;

    public static List<LocalDate> getListOfFriday13(Integer year) {
        Objects.requireNonNull(year);
        if (year < 0) {
            throw new IllegalArgumentException();
        }

        List<LocalDate> resultList = new ArrayList<>();
        for (int month = MIN_MONTH_NUMERATION; month <= MAX_MONTH_NUMERATION; month++) {
            LocalDate date = LocalDate.of(year, month, FRIDAY_13_DAY);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                resultList.add(date);
            }
        }
        return resultList;
    }

    public static LocalDate getNextFriday13(String date) {
        validate(date);
        LocalDate currentDate = LocalDate.parse(date);

        LocalDate nextFriday13 = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextFriday13.getDayOfMonth() != FRIDAY_13_DAY) {
            nextFriday13 = nextFriday13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFriday13;
    }


    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        String pattern = "^\\d{4}-\\d{2}-\\d{2}";
        Matcher matcher = Pattern.compile(pattern).matcher(rawString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }
}
