package edu.project2.maze_sekelet;

public record Coordinate(int row, int col) {
    private static final int MIN_AVAIVABLE_VALUE_FOR_ROW_AND_COL = -1;

    public Coordinate(int row, int col) {
        validateArgs(row, col);
        this.row = row;
        this.col = col;
    }

    private void validateArgs(int row, int col) {

        if (row < MIN_AVAIVABLE_VALUE_FOR_ROW_AND_COL || col < MIN_AVAIVABLE_VALUE_FOR_ROW_AND_COL) {
            throw new IllegalArgumentException("Incorrect Cell arguments");
        }
    }
}
