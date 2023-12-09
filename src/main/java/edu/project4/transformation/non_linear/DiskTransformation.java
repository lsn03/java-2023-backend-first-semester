package edu.project4.transformation.non_linear;

import edu.project4.skelet.Point;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.TransformationUtils;

public class DiskTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        validate(point);
        var firstPath = (1.0 / Math.PI) * Math.atan(point.y() / point.x());
        var secondPath = Math.PI * Math.sqrt(TransformationUtils.getSumSquare(point.x(), point.y()));
        var x = firstPath * Math.sin(secondPath);
        var y = firstPath * Math.cos(secondPath);
        return new Point(x, y);
    }
}
