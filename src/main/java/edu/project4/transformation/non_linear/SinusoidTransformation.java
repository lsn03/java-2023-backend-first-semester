package edu.project4.transformation.non_linear;

import edu.project4.skelet.Point;
import edu.project4.transformation.Transformation;

public class SinusoidTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        var x = Math.sin(point.x());
        var y = Math.sin(point.y());
        return new Point(x, y);
    }
}
