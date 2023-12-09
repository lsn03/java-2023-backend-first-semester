package edu.project4.transformation.non_linear;

import edu.project4.skelet.Point;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.TransformationUtils;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        validate(point);
        var sum = TransformationUtils.getSumSquare(point.x(), point.y());
        var x = (point.x() / (sum));
        var y = (point.y() / (sum));
        return new Point(x, y);
    }

}
