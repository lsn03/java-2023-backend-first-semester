package edu.project4.transformation;

import edu.project4.skelet.Point;
import java.util.Objects;
import java.util.function.Function;

public interface Transformation extends Function<Point, Point> {
    @Override
    default Point apply(Point point) {
        return null;
    }

    default void validate(Point point) {
        Objects.requireNonNull(point);
    }
}

