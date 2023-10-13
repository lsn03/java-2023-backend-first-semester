package edu.hw1;


public class Task2 {
    @SuppressWarnings("MagicNumber")
    public int countDigits(int number) {
        if (number == 0) {
            return 1;
        } else if (number == Integer.MIN_VALUE) {
            return -1;
        }

        int myNumber = number;

        myNumber = Math.abs(myNumber);

        int cnt = 0;

        while (myNumber > 0) {
            myNumber /= 10;
            cnt += 1;
        }
        return cnt;
    }

}
