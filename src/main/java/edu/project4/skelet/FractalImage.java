package edu.project4.skelet;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
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
        return data[y][x];
    }
}
