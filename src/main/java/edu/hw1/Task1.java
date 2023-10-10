package edu.hw1;


public class Task1 {
    private final static int MAX_SECONDS_IN_MINUTE = 60;
    private final static int MIN_SECONDS_IN_MINUTE = 0;

    private boolean isValidInput(String string) {
        boolean notNullOrEmpty = string != null && !string.isBlank();
        boolean arraySizeIsTwoAndFormatIsGood = false;

        if (notNullOrEmpty) {
            String[] parts = string.split(":");
            arraySizeIsTwoAndFormatIsGood = !parts[0].isBlank() && parts[1].length() == 2 && parts.length == 2;
        }

        return notNullOrEmpty && arraySizeIsTwoAndFormatIsGood;


    }

    public int minutesToSeconds(String string) {
        int res = -1;
        if (isValidInput(string)) {

            String[] parts = string.split(":");

            int mm = Integer.parseInt(parts[0]);
            int ss = Integer.parseInt(parts[1]);

            if (ss < MAX_SECONDS_IN_MINUTE && ss >= MIN_SECONDS_IN_MINUTE && mm >= 0) {
                try {
                    res = Math.multiplyExact(mm, MAX_SECONDS_IN_MINUTE) + ss;
                } catch (ArithmeticException ignored) {

                }

            }
        }
        return res;
    }
}
