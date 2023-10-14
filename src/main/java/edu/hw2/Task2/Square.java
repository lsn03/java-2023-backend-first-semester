package edu.hw2.Task2;

public class Square implements Shape {
    private final int width;
    private final int height;

    public Square(int side) {
        width = side;
        height = side;
    }

    public Square setHeight(int height) {
        return new Square(height);
    }

    public Square setWidth(int width) {
        return new Square(width);
    }

    @Override
    public double area() {
        return width * height;
    }
}
