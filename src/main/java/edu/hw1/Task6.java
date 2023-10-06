package edu.hw1;

import java.util.Arrays;

public class Task6 {
	private int cnt = 0;
	public int countK(int number) {

		if(number<1000 || number>=10000){
			throw new IllegalArgumentException("Value should be in range [1000,9999]");
		}

		kaperkarRec(number, 0);
		return cnt;
	}

	private int kaperkarRec(int n, int prev) {

		if (n == 0) {
			return 0;
		}

		prev = n;

		int[] sortedDigits = new int[4];
		String str = String.valueOf(n);

		for (int i = 0; i < sortedDigits.length; i++) {
			sortedDigits[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
		}

		Arrays.sort(sortedDigits);

		int sortedA = sortedDigits[0] * 1000 + sortedDigits[1] * 100 + sortedDigits[2] * 10 + sortedDigits[3];
		int sortedB = sortedDigits[3] * 1000 + sortedDigits[2] * 100 + sortedDigits[1] * 10 + sortedDigits[0];

		int diff = Math.abs(sortedA - sortedB);

		if (diff == prev) {
			return diff;
		}
		cnt++;
		return kaperkarRec(diff, prev);
	}
}
