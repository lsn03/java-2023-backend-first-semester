package edu.hw3;

public class RomanNumber {
    public String convertToRoman(int number) {
        if (number <= 0 || number > 3999) {
            throw new IllegalArgumentException("Value should be in range [1,3999]");
        }

        int[] arabicValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romanNumber = new StringBuilder();

        int i = 0;
        while (number > 0) {
            if (number >= arabicValues[i]) {
                romanNumber.append(romanSymbols[i]);
                number -= arabicValues[i];
            } else {
                i++;
            }
        }

        return romanNumber.toString();
    }

    public static void main(String[] args) {
        RomanNumber rn = new RomanNumber();
        System.out.println(rn.convertToRoman(5));
        System.out.println(rn.convertToRoman(10));
        System.out.println(rn.convertToRoman(15));
        System.out.println(rn.convertToRoman(19));
        System.out.println(rn.convertToRoman(147));
    }
}
