package edu.homework.hw5;

import java.util.Objects;
import java.util.regex.Pattern;

public class Task8 {
    private Task8() {

    }

    public static boolean firstTask(String rawString) {
        validate(rawString);
        String regExp = "^([01]{2})*[01]$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        return matcher.matches();
    }

    public static boolean thirdTask(String rawString) {
        validate(rawString);
        String regExp = "^(1*0){3}1*$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        return matcher.matches();
    }

    public static boolean fourthTask(String rawString) {
        validate(rawString);
        String regExp = "^(?!11$|111$)[01]+$";
        var pattern = Pattern.compile(regExp);
        var matcher = pattern.matcher(rawString);
        return matcher.matches();
    }

    public static boolean sevenTask(String rawString) {
        validate(rawString);
        String regExp = "^(?!.*11)[01]+$";
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
