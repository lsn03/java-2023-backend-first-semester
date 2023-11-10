package edu.homework.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Task1ClubAnalytic {
    private Task1ClubAnalytic() {

    }

    public static String getTime(String rawString) {
        validate(rawString);

        var parsed = rawString.split(" - ");

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


    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isEmpty() || rawString.isBlank()) {
            throw new IllegalArgumentException();
        }
        String regExp = "\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2}\\s-\\s\\d{4}-\\d{2}-\\d{2},\\s\\d{2}:\\d{2}";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(rawString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }
    }


}
