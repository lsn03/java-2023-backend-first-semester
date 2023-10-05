package edu.hw1;


public class Task2 {
	@SuppressWarnings("MagicNumber")
	public int countDigits(int number) {

		int cnt = 0;
		if (number == 0) {
			return 1;
		}
		int myNumber = number;
		if (number == Integer.MIN_VALUE) {
			return -1;
		}


		if (myNumber < 0) {
			myNumber *= -1;
		}

		while (myNumber > 0) {
			myNumber /= 10;
			cnt += 1;
		}
		return cnt;
	}

}
