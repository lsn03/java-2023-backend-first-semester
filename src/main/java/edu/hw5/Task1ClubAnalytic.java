package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Task1ClubAnalytic {

    private static final StringBuilder STRING_BUILDER = new StringBuilder();
    private static String[] parsed;
    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd, HH:mm";
    private static long days;
    private static long hours;
    private static long minutes;

    private Task1ClubAnalytic() {

    }

    public static String getTime(String rawString) {
        validate(rawString);

        parsed = rawString.split(" - ");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        var startTime = LocalDateTime.parse(parsed[0], dateTimeFormatter);
        var endTime = LocalDateTime.parse(parsed[1], dateTimeFormatter);

        Duration duration = Duration.between(startTime, endTime);

        if (duration.isNegative()) {
            throw new IllegalArgumentException("The result of evaluation is negative");
        }

        days = duration.toDays();
        hours = duration.minusDays(days).toHours();
        minutes = duration.minusDays(days).minusHours(hours).toMinutes();

        return buildAnswer();
    }


    private static String buildAnswer() {
        STRING_BUILDER.setLength(0);

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

        STRING_BUILDER.append(daysAns).append(hoursAns).append(minutesAns);

        return STRING_BUILDER.toString();
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
