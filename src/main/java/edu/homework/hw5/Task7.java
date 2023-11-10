package edu.homework.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task7 {
    private Task7() {

    }

    public static boolean validateMoreThan3SymbolsAndThirdIsZero(String rawString) {
        validate(rawString);
        String regExp = "^[01]{2}0[01]*$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        return matcher.matches();
    }

    public static boolean validateStringBeginAndEndWithEqualsSymbols(String rawString) {
        validate(rawString);
        String regExp = "^(1[01]*1|0[01]*0)$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        return matcher.matches();
    }

    public static boolean validateStringLenghtInRange(String rawString) {
        validate(rawString);
        String regExp = "^[01]{1,3}$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        return matcher.matches();
    }

    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isBlank() || rawString.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
}
