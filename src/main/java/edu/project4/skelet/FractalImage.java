package edu.project4.skelet;

import java.util.Objects;

public record FractalImage(Pixel[][] data, int width, int height) {


    public FractalImage {
        validate(data, width, height);
    }


    public static FractalImage create(int width, int height) {
        validate(width, height);
        Pixel[][] data = new Pixel[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i][j] = new Pixel(0, 0, 0, 0);
            }
        }

        return new FractalImage(data, width, height);
    }


    public boolean contains(int x, int y) {
        return (0 <= x && x < width) && (0 <= y && y < height);
    }

    public Pixel pixel(int x, int y) {
        return contains(x, y) ? data[y][x] : null;
    }

    private static void validate(Pixel[][] data, int width, int height) {
        Objects.requireNonNull(data);
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
    }

    private static void validate(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException();
        }
    }
}
