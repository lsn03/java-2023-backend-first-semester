package edu.project2.maze_sekelet;

import java.util.Objects;

public record Cell(int row, int col, Type type) {
    public enum Type {
        WALL,
        PASSAGE
    }

    public Cell(int row, int col, Type type) {
        validateArgs(row, col, type);
        this.row = row;
        this.col = col;
        this.type = type;
    }

    private void validateArgs(int row, int col, Type type) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Incorrect Cell arguments");
        }
        Objects.requireNonNull(type);
    }
}
