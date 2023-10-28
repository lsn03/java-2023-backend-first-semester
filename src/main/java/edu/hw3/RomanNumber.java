package edu.hw3;

import java.util.Objects;

@SuppressWarnings("MagicNumber")
public class RomanNumber {
    private static final int LOW_BORDER = 0;
    private static final int HIGH_BORDER = 3999;

    private RomanNumber() {

    }

    public static String convertToRoman(Integer number) {
        Objects.requireNonNull(number);

        if (number <= LOW_BORDER || number > HIGH_BORDER) {
            throw new IllegalArgumentException("Value should be in range [1,3999]");
        }

        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romanNumber = new StringBuilder();

        int i = 0;
        int numberWhile = number;
        while (numberWhile > 0) {
            if (numberWhile >= arabicValues[i]) {
                romanNumber.append(romanSymbols[i]);
                numberWhile -= arabicValues[i];
            } else {
                i++;
            }
        }

        return romanNumber.toString();
    }
}
