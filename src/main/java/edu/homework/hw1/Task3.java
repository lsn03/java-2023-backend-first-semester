package edu.homework.hw1;

public class Task3 {

    public boolean isNestable(int[] a, int[] b) {

        if (a == null || b == null || a.length == 0 || b.length == 0) {
            return false;
        }
        int[] valueInA = getMinMaxValueInArrya(a);
        int minInA = valueInA[0];
        int maxInA = valueInA[1];

        int[] valueInB = getMinMaxValueInArrya(b);
        int minInB = valueInB[0];
        int maxInB = valueInB[1];

        boolean expressionOne = minInA > minInB;
        boolean expressionTwo = maxInA < maxInB;

        return expressionOne && expressionTwo;
    }

    private int[] getMinMaxValueInArrya(int[] x) {
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < x.length; i++) {
            if (x[i] < minValue) {
                minValue = x[i];
            }
            if (maxValue < x[i]) {
                maxValue = x[i];
            }
        }
        return new int[]{minValue, maxValue};
    }
}
