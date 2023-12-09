package edu.project4.transformation.non_linear;

import edu.project4.skelet.Point;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.TransformationUtils;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        validate(point);
        var x = Math.atan(point.y() / point.x()) / Math.PI;
        var y = Math.sqrt(TransformationUtils.getSumSquare(point.y(), point.x())) - 1;
        return new Point(x, y);
    }
}
