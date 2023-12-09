package edu.project4.skelet;

public record Pixel(int r, int g, int b, int hitCount) {

    public static final int MAX_VALUE = 255;

    public Pixel {
        validate(r, g, b, hitCount);
    }

    private void validate(int r, int g, int b, int hitCount) {
        if (r < 0 || g < 0 || b < 0) {
            throw new IllegalArgumentException();
        }
        if (r > MAX_VALUE || g > MAX_VALUE || b > MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        if (hitCount < 0) {
            throw new IllegalArgumentException();
        }
    }
}
