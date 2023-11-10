package edu.hw5;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class Task5CarSignPattern {

    private Task5CarSignPattern() {

    }

    public static boolean isSignValid(String rawString) {
        validate(rawString);

        String avaiableSymbols = "АВЕКМНОРСТУХ";
        String regExp = "^[" + avaiableSymbols + "]{1}\\d{3}[" + avaiableSymbols + "]{2}\\d{2,3}$";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(rawString);

        return matcher.matches();
    }

    private static void validate(String rawString) {
        Objects.requireNonNull(rawString);
        if (rawString.isEmpty() || rawString.isBlank()) {
            throw new IllegalArgumentException();
        }
    }


}
