package edu.homework.hw1;

public class Task5 {
    public boolean isPalindromeDescendant(int input) {
        if (input < 0) {
            throw new IllegalArgumentException("Value must be positive");
        }

        String value = Integer.toString(input);

        if (checkThatPalindrome(value)) {
            return true;
        } else {
            String str = getPotomok(value);
            while ((!checkThatPalindrome(str)) && !str.isEmpty()) {
                str = getPotomok(str);
            }
            return checkThatPalindrome(str);
        }


    }

    private String getPotomok(String string) {
        if (string.length() <= 1) {
            return "";
        }
        String result = "";
        for (int i = 0; i < string.length() - 1; i += 2) {
            int sum = (Integer.parseUnsignedInt(String.valueOf(string.charAt(i)))
                    + Integer.parseUnsignedInt(String.valueOf(string.charAt(i + 1))));
            result += String.valueOf(sum);
        }
        if (string.length() % 2 == 1) {
            result += string.charAt(string.length() - 1);
        }
        return result;
    }

    private boolean checkThatPalindrome(String string) {
        if (string.length() <= 1) {
            return false;
        }
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - i - 1)) {
                return false;
            }
        }

        return true;
    }

}
