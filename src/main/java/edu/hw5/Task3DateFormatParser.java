package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task3DateFormatParser {
    private static boolean isAnswerNotFound;

    private static Pattern pattern;
    private static Matcher matcher;
    private static String stringPattern;
    private static String regExp;
    private static String inputRawString;
    private static LocalDate answer;

    private Task3DateFormatParser() {

    }

    public static Optional<LocalDate> parseDate(String rawString) {
        validate(rawString);
        inputRawString = rawString;
        answer = null;
        isAnswerNotFound = true;

        commonPatternValidation();

//        Tomorrow, today, yesterday
        oneWordFormatValidation();


//        n day(s) ago
        nDaysFormatValidation();

        if (isAnswerNotFound) {
            return Optional.empty();
        } else {
            return Optional.of(answer);
        }


    }

    private static void buildPatternAndMatcher() {
        pattern = Pattern.compile(regExp);
        matcher = pattern.matcher(inputRawString);
    }

    private static void nDaysFormatValidation() {
        regExp = "^(\\d+)\\s+day(s)?\\s+ago$";
        buildPatternAndMatcher();


        if (matcher.matches() && isAnswerNotFound) {
            int daysAgo = Integer.parseInt(matcher.group(1));
            answer = LocalDate.now().minusDays(daysAgo);
            isAnswerNotFound = false;
        }
    }

    private static void oneWordFormatValidation() {
        if (isAnswerNotFound && "tomorrow".equalsIgnoreCase(inputRawString)) {
            answer = LocalDate.now().plusDays(1);
            isAnswerNotFound = false;
        } else if (isAnswerNotFound && "today".equalsIgnoreCase(inputRawString)) {
            answer = LocalDate.now();
            isAnswerNotFound = false;
        } else if (isAnswerNotFound && "yesterday".equalsIgnoreCase(inputRawString)) {
            answer = LocalDate.now().minusDays(1);
            isAnswerNotFound = false;
        }
    }

    private static void commonPatternValidation() {
        stringPattern = "yyyy-MM-d";
        regExp = "^\\d{4}-\\d{2}-\\d{1,2}$";
        buildPatternAndMatcher();
        if (matcher.matches()) {
            answer = LocalDate.parse(inputRawString, DateTimeFormatter.ofPattern(stringPattern));
            isAnswerNotFound = false;
        }

        //        dd/MM/yyyy
        stringPattern = "d/M/yyyy";
        regExp = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
        buildPatternAndMatcher();
        if (matcher.matches() && isAnswerNotFound) {
            answer = LocalDate.parse(inputRawString, DateTimeFormatter.ofPattern(stringPattern));
            isAnswerNotFound = false;
        }

        stringPattern = "d/M/yy";
        regExp = "^\\d{1,2}/\\d{1,2}/\\d{2}$";
        buildPatternAndMatcher();
        if (matcher.matches() && isAnswerNotFound) {
            answer = LocalDate.parse(inputRawString, DateTimeFormatter.ofPattern(stringPattern));
            isAnswerNotFound = false;
        }
    }

    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isEmpty() || rawString.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

}
