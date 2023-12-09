package edu.project4.renderer;

import edu.project4.skelet.FractalImage;
import edu.project4.skelet.Pixel;
import edu.project4.skelet.Point;
import edu.project4.skelet.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class AbstractRenderer implements Renderer {
    protected static final int DEFAULT_SYMMETRY = 5;
    protected static final int STEP_UP = 10;
    protected static final int STEP_DOWN = -2;
    protected int symmetry;
    protected static final int HIGH_BOUND = 256;
    protected Random random;

    protected void updatePixel(FractalImage canvas, Rect world, Point targetPoint, Pixel sourcePixel) {

        var pixelForUpdate = getPixelCoordinate(world, targetPoint, canvas);

        int x = pixelForUpdate[0];
        int y = pixelForUpdate[1];

        if (canvas.contains(x, y)) {
            Pixel targetPixel = canvas.pixel(x, y);

            int updatedHitCount = targetPixel.hitCount() + 1;
            int updatedR = sourcePixel.r();
            int updatedG = sourcePixel.g();
            int updatedB = sourcePixel.b();

            if (targetPixel.hitCount() != 0) {
                updatedR = ((updatedR + targetPixel.r()) / 2 + direction());
                updatedG = ((updatedG + targetPixel.g()) / 2 + direction());
                updatedB = ((updatedB + targetPixel.b()) / 2 + direction());
            }


            Pixel newPixel = new Pixel(updatedR, updatedG, updatedB, updatedHitCount);
            canvas.data()[y][x] = newPixel;
        }

    }

    protected int direction() {
        var bool = random.nextBoolean();
        return bool ? STEP_UP : STEP_DOWN;
    }

    protected Pixel setPixelColor(Pixel pixel) {
        if (pixel.hitCount() == 0) {
            int r = generateRandomColorComponent();
            int g = generateRandomColorComponent();
            int b = generateRandomColorComponent();
            return new Pixel(r, g, b, 0);
        }
        return pixel;
    }

    protected Pixel mapRange(Rect world, Point pwr, FractalImage canvas) {
        if (!world.contains(pwr)) {
            return null;
        }

        var pixelCoordinate = getPixelCoordinate(world, pwr, canvas);

        int x = pixelCoordinate[0];
        int y = pixelCoordinate[1];


        return canvas.pixel(x, y);
    }

    protected int[] getPixelCoordinate(Rect world, Point point, FractalImage canvas) {
        int x = (int) ((point.x() - world.x()) / world.width() * canvas.width());
        int y = (int) ((point.y() - world.y()) / world.height() * canvas.height());
        return new int[]{x, y};
    }

    protected void validateRenderData(FractalImage canvas, Rect world, List<Transformation> variations,
                                      int samples, int iterPerSample) {
        Objects.requireNonNull(canvas);
        Objects.requireNonNull(world);
        Objects.requireNonNull(variations);
        if (variations.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (samples < 0 || iterPerSample < 0) {
            throw new IllegalArgumentException();
        }
    }

    protected int generateRandomColorComponent() {
        return random.nextInt(HIGH_BOUND);
    }

    protected Point rotate(Point point, double theta) {
        double x = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double y = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(x, y);
    }

    protected Transformation randomVariations(List<Transformation> variations) {

        int index = random.nextInt(0, variations.size());
        return variations.get(index);
    }

    protected Point randomPoint(Rect world) {
        double x = world.x() + random.nextDouble(world.width());
        double y = world.y() + random.nextDouble(world.height());
        return new Point(x, y);
    }

}
