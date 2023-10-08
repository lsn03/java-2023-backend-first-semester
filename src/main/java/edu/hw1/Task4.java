package edu.hw1;

public class Task4 {
    public String fixString(String string) {

        if (string == null) {
            throw new NullPointerException();
        }

        char[] chars = new char[string.length()];


        for (int i = 1; i < string.length(); i += 2) {
            chars[i] = string.charAt(i - 1);
            chars[i - 1] = string.charAt(i);
        }

        if (string.length() % 2 == 1) {
            chars[string.length() - 1] = string.charAt(string.length() - 1);
        }

        return new String(chars);

    }
}
