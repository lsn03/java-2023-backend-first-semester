package edu.homework.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task4PasswordValidator {
    private Task4PasswordValidator() {

    }

    public static boolean solve(String rawPassword) {
        validate(rawPassword);

        String chars = "~!@#$%^&|";
        String regExp = ".*[" + Pattern.quote(chars) + "].*";
        return rawPassword.matches(regExp);
    }

    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isEmpty() || rawString.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
