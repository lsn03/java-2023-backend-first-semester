package edu.project4.transformation;

import edu.project4.skelet.Point;
import static edu.project4.transformation.TransformationUtils.getSumSquare;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation implements Transformation {

    private double a, b, c, d, e, f;
    private final Random random = ThreadLocalRandom.current();

    public AffineTransformation() {
        do {
            a = generateRandom();
            b = generateRandom();
            d = generateRandom();
            e = generateRandom();
            c = generateRandom();
            f = generateRandom();
        }
        while (!isValid(a, b, d, e));

    }


    @Override
    public Point apply(Point point) {
        double x = a * point.x() + b * point.y() + c;
        double y = d * point.x() + e * point.y() + f;
        return new Point(x, y);
    }

    private double generateRandom() {
        return random.nextDouble(-1, 1.01);
    }

    private boolean isValid(double a, double b, double d, double e) {
        return getSumSquare(a, d) < 1
                && getSumSquare(b, e) < 1
                && (getSumSquare(a, b) + getSumSquare(d, e) < 1 + Math.pow(a * e - b * d, 2));
    }

}
