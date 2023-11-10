package edu.homework.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task6SubSeqChecker {
    private Task6SubSeqChecker() {

    }

    public static boolean isSubsequence(String s, String t) {
        validate(s);
        validate(t);

        String regExp = ".*" + s + ".*";
        var pattern = Pattern.compile(regExp);
        var macther = pattern.matcher(t);
        return macther.matches();
    }

    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isEmpty() || rawString.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
