package edu.project2.maze_sekelet;

public record Coordinate(int row, int col) {
    public Coordinate(int row, int col) {
        validateArgs(row,col);
        this.row = row;
        this.col = col;
    }

    private void validateArgs(int row, int col) {
//        -1 because DFS can call 0 - 1 = -1
        if (row < -1 || col < -1) {
            throw new IllegalArgumentException("Incorrect Cell arguments");
        }
    }
}
