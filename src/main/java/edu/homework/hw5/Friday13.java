package edu.homework.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

//TODO: final private constructor
public class Friday13 {
    public static List<LocalDate> getListOfFriday13(int year) {
        List<LocalDate> resultList = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            LocalDate date = LocalDate.of(year, month, 13);
            if (date.getDayOfWeek() == DayOfWeek.FRIDAY) {
                resultList.add(date);
            }
        }
        return resultList;
    }

    public static LocalDate getNextFriday13(String date) {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        LocalDate currentDate = LocalDate.parse(date);

        LocalDate nextFriday13 = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextFriday13.getDayOfMonth() != 13) {
            nextFriday13 = nextFriday13.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFriday13;
    }

    public static void main(String[] args) {
        System.out.println(getListOfFriday13(1925));
        System.out.println(getNextFriday13("1925-12-01"));
    }
}
