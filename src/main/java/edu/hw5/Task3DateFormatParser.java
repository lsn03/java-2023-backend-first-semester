package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

public final class Task3DateFormatParser {

    private Task3DateFormatParser() {

    }

    public static Optional<LocalDate> parseDate(String rawString) {
        validate(rawString);

        LocalDate answer = null;
        boolean flag = true;

        String stringPattern = "yyyy-MM-d";
        String regExp = "^\\d{4}-\\d{2}-\\d{1,2}$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        if (matcher.matches()) {
            answer = LocalDate.parse(rawString, DateTimeFormatter.ofPattern(stringPattern));
            flag = false;
        }

        //        dd/MM/yyyy
        stringPattern = "d/M/yyyy";
        regExp = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
        pattern = Pattern.compile(regExp);
        matcher = pattern.matcher(rawString);
        if (matcher.matches() && flag) {
            answer = LocalDate.parse(rawString, DateTimeFormatter.ofPattern(stringPattern));
            flag = false;
        }

        stringPattern = "d/M/yy";
        regExp = "^\\d{1,2}/\\d{1,2}/\\d{2}$";
        pattern = Pattern.compile(regExp);
        matcher = pattern.matcher(rawString);
        if (matcher.matches() && flag) {
            answer = LocalDate.parse(rawString, DateTimeFormatter.ofPattern(stringPattern));
            flag = false;
        }

//        Tomorrow, today, yesterday

        if (flag && "tomorrow".equalsIgnoreCase(rawString)) {
            answer = LocalDate.now().plusDays(1);
            flag = false;
        } else if (flag && "today".equalsIgnoreCase(rawString)) {
            answer = LocalDate.now();
            flag = false;
        } else if (flag && "yesterday".equalsIgnoreCase(rawString)) {
            answer = LocalDate.now().minusDays(1);
            flag = false;
        }

//        n day(s) ago
        regExp = "^(\\d+)\\s+day(s)?\\s+ago$";

        pattern = Pattern.compile(regExp);
        matcher = pattern.matcher(rawString);

        if (matcher.matches() && flag) {
            int daysAgo = Integer.parseInt(matcher.group(1));
            answer = LocalDate.now().minusDays(daysAgo);
        }


        return answer == null ? Optional.empty() : Optional.of(answer);
    }

    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isEmpty() || rawString.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

}
