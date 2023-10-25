package edu.hw3;

@SuppressWarnings("MagicNumber")
public class RomanNumber {
    public String convertToRoman(int number) {
        if (number <= 0 || number > 3999) {
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
