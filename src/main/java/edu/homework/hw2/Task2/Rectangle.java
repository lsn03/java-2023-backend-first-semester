package edu.homework.hw2.Task2;

public final class Rectangle implements Shape {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(width, height);
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle{" + "width=" + width + ", height=" + height + '}';
    }
}
