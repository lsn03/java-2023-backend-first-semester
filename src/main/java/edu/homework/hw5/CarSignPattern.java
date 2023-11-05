package edu.homework.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO: final private constructor
public class CarSignPattern {

    public static boolean isSignValid(String raw) {
        String regExp = "^[А-Я]{1}\\d{3}[А-Я]{2}\\d{2,3}$";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(raw);

        return matcher.matches();
    }

    public static void main(String[] args) {
        System.out.println(isSignValid("А123ВЕ777"));
        System.out.println(isSignValid("О777ОО177"));
        System.out.println(isSignValid("А123ВЕ77"));

        System.out.println(isSignValid("123АВЕ777"));
        System.out.println(isSignValid("А123ВЕ7777"));
    }
}
