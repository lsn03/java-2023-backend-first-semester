package edu.hw1;

public class Task8 {
    public boolean knightBoardCapture(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] == 1 && checkThatHorseCanBeat(arr, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("MagicNumber")
    private boolean checkThatHorseCanBeat(int[][] arr, int posI, int posJ) {
        int[][] hourseMoves = {
                {-2, -1},
                {-2, 1},

                {-1, -2},
                {1, -2},

                {-1, 2},
                {1, 2},

                {2, -1},
                {2, 1}
        };

        for (int[] move : hourseMoves) {
            int x = posI + move[0];
            int y = posJ + move[1];

            if ((0 <= x && x < arr.length) && (0 <= y && y < arr.length) && arr[x][y] == 1) {
                return true;
            }

        }

        return false;
    }
}
