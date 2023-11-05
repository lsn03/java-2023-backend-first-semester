package edu.homework.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

//TODO: final private constructor
public class ClubAnalytic {
    public static String getTime(String rawString) {
        var parsed = parseString(rawString);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        var startTime = LocalDateTime.parse(parsed[0], dateTimeFormatter);
        var endTime = LocalDateTime.parse(parsed[1], dateTimeFormatter);

        Duration duration = Duration.between(startTime, endTime);

        if (duration.isNegative()) {
            throw new IllegalArgumentException("The result of evaluation is negative");
        }

        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours();
        long minutes = duration.minusDays(days).minusHours(hours).toMinutes();

        return buildAnswer(days, hours, minutes);
    }

    private static String buildAnswer(long days, long hours, long minutes) {
        StringBuilder stringBuilder = new StringBuilder();
        String daysAns = "";
        String hoursAns = "";
        String minutesAns = "";
        if (days != 0) {
            daysAns = days + "д ";
        }
        if (hours != 0) {
            hoursAns = hours + "ч ";
        }
        if (minutes != 0) {
            minutesAns = minutes + "м";
        }
        stringBuilder.append(daysAns).append(hoursAns).append(minutesAns);
        return stringBuilder.toString();
    }

    private static String[] parseString(String rawString) {
        Objects.requireNonNull(rawString);
        var result = rawString.split(" - ");
        if (result.length != 2) {
            throw new IllegalArgumentException("Incorrect input format. Required = ");
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getTime("2022-03-12, 20:20 - 2022-03-12, 23:50"));
        System.out.println(getTime("2022-04-01, 21:30 - 2022-04-02, 01:20"));
        System.out.println(getTime("2022-04-01, 21:30 - 2022-04-03, 01:20"));
        System.out.println(getTime("2020-04-01, 21:30 - 2022-04-03, 01:20"));
        System.out.println(getTime("2022-04-02, 21:30 - 2022-04-01, 01:20"));
    }
}
