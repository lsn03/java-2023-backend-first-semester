package edu.hw1;

import java.util.Arrays;

public class Task6 {
    private int cnt = 0;

    public int countK(int number) {
        final int LOW_BORDER_OF_NUMBER = 1000;
        final int HIGH_BORDER_OF_NUMBER = 9999;
        if (number < LOW_BORDER_OF_NUMBER || number > HIGH_BORDER_OF_NUMBER) {
            throw new IllegalArgumentException("Value should be in range [1000,9999]");
        }

        kaperkarRec(number);
        return cnt;
    }


    private int kaperkarRec(int n) {

        if (n == 0) {
            return 0;
        }

        final int MAX_ARRAY_LENGHT = 4;
        int[] sortedDigits = new int[MAX_ARRAY_LENGHT];

        String str = String.valueOf(n);

        for (int i = 0; i < sortedDigits.length; i++) {
            sortedDigits[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }

        Arrays.sort(sortedDigits);

        final int THAUSEND_FOR_NORMAL_FORM = 1000; // 1234 = 1*1000
        final int THUNDREED_FOR_NORMAL_FORM = 100; // 1234 = 2*100
        final int DECADE_FOR_NORMAL_FORM = 10; // 1234 = 3*10
        // 1 * 1000 + 2 * 100 + 3 * 10 + 4 = 1234

        final int FOURTH_DIGIT_IN_NUMBER = 3;
        final int THIRD_DIGIT_IN_NUMBER = 2;
        final int SECOND_DIGIT_IN_NUMBER = 1;
        final int FIRST_DIGIT_IN_NUMBER = 0;

        int sortedA = sortedDigits[FIRST_DIGIT_IN_NUMBER] * THAUSEND_FOR_NORMAL_FORM
                + sortedDigits[SECOND_DIGIT_IN_NUMBER] * THUNDREED_FOR_NORMAL_FORM
                + sortedDigits[THIRD_DIGIT_IN_NUMBER] * DECADE_FOR_NORMAL_FORM
                + sortedDigits[FOURTH_DIGIT_IN_NUMBER];
        int sortedB = sortedDigits[FOURTH_DIGIT_IN_NUMBER] * THAUSEND_FOR_NORMAL_FORM
                + sortedDigits[THIRD_DIGIT_IN_NUMBER] * THUNDREED_FOR_NORMAL_FORM
                + sortedDigits[SECOND_DIGIT_IN_NUMBER] * DECADE_FOR_NORMAL_FORM
                + sortedDigits[FIRST_DIGIT_IN_NUMBER];

        int diff = Math.abs(sortedA - sortedB);

        if (diff == n) {
            return diff;
        }


        cnt++;
        return kaperkarRec(diff);
    }
}
