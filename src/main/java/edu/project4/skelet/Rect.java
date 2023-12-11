package edu.project4.skelet;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point p) {
        double xMax = x + width;
        double yMax = y + height;

        return p.x() >= x && p.x() <= xMax && p.y() >= y && p.y() <= yMax;
    }
}
