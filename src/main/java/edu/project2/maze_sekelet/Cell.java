package edu.project2.maze_sekelet;

import java.util.Objects;

public record Cell(int row, int col, Type type) {

    public static final char WALL_CHAR = '\u2591';
    public static final char PASSAGE_CHAR = '\u2593';

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

    public char getCellChar() {
        return switch (type) {
            case WALL -> WALL_CHAR;
            case PASSAGE -> PASSAGE_CHAR;
        };
    }

    private void validateArgs(int row, int col, Type type) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException("Incorrect Cell arguments");
        }
        Objects.requireNonNull(type);
    }
}
