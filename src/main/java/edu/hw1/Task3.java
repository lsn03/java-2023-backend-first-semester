package edu.hw1;

public class Task3 {
	public boolean isNestable(int[] a, int[] b) {

		if (a == null || b == null || a.length == 0 || b.length == 0) {
			return false;
		}
		boolean expressionOne = getMinValueInArray(a) > getMinValueInArray(b);
		boolean expressionTwo = getMaxValueInArray(a) < getMaxValueInArray(b);
		return expressionOne && expressionTwo;
	}

	private int getMinValueInArray(int[] x) {
		int res = Integer.MAX_VALUE;

		for (int i = 0; i < x.length; i++) {
			if (x[i] < res) {
				res = x[i];
			}
		}
		return res;
	}

	private int getMaxValueInArray(int[] x) {
		int res = Integer.MIN_VALUE;

		for (int i = 0; i < x.length; i++) {
			if (res < x[i]) {
				res = x[i];
			}
		}
		return res;
	}

}
