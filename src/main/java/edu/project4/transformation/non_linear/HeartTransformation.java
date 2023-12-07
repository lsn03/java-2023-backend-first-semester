package edu.project4.transformation.non_linear;

import edu.project4.skelet.Point;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.TransformationUtils;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        var sqrtFromSum = Math.sqrt(TransformationUtils.getSumSquare(point.x(), point.y()));

        var x = sqrtFromSum * Math.sin(sqrtFromSum * Math.atan(point.y() / point.x()));

        var y = -1.0 * sqrtFromSum * Math.cos(sqrtFromSum * Math.atan(point.y() / point.x()));
        return new Point(x, y);
    }
}
