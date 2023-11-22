package edu.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public final class Task6SubSeqChecker {
    private Task6SubSeqChecker() {

    }

    public static boolean isSubsequence(String s, String t) {
        validate(s, t);

        String regExp = ".*" + s + ".*";

        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(t);
        return matcher.matches();
    }

    private static void validate(String one, String two) {

        Objects.requireNonNull(one);
        Objects.requireNonNull(two);

        if (one.isEmpty() || one.isBlank() || two.isEmpty() || two.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

}
