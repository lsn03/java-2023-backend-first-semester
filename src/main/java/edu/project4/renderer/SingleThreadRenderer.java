package edu.project4.renderer;

import edu.project4.skelet.FractalImage;
import edu.project4.skelet.Pixel;
import edu.project4.skelet.Point;
import edu.project4.skelet.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadRenderer implements Renderer {

    public static final double symmetry = 5;
    public static final int HIGH_BOUND = 256;
    private final Random random;

    public SingleThreadRenderer(long seed) {
        random = new Random(seed);
    }

    public SingleThreadRenderer() {
        random = ThreadLocalRandom.current();
    }

    @Override
    public FractalImage render(FractalImage canvas, Rect world, List<Transformation> variations, int samples, int iterPerSample, long seed) {

        for (int num = 0; num < samples; ++num) {
            Point mainPoint = randomPoint(world);

            Pixel sourcePixel = mapRange(world, mainPoint, canvas);

            sourcePixel = setPixelColor(sourcePixel);

            for (int step = 0; step < iterPerSample; ++step) {
                Transformation variation = randomVariations(variations);

                mainPoint = variation.apply(mainPoint);

                double theta2 = 0.0;
                for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, s++) {

                    Point rotatedMainPoint = rotate(mainPoint, theta2);

                    if (!world.contains(rotatedMainPoint)) continue;

                    synchronized (canvas) {
                        updatePixel(canvas, world, rotatedMainPoint, sourcePixel);
                    }
                    sourcePixel = mapRange(world, rotatedMainPoint, canvas);
                }
            }
        }

        gammaCorrection(canvas);


        return canvas;
    }

    private void gammaCorrection(FractalImage canvas) {
        double max = Double.MIN_VALUE;

        for (int i = 0; i < canvas.height(); i++) {
            for (int j = 0; j < canvas.width(); j++) {
                Pixel pixel = canvas.pixel(j, i);
                if (pixel.hitCount() != 0) {
                    double normal = Math.log10(pixel.hitCount());
                    if (normal > max) {
                        max = normal;
                    }
                }
            }
        }


        double gamma = 4.2;
        for (int i = 0; i < canvas.height(); i++) {
            for (int j = 0; j < canvas.width(); j++) {
                Pixel pixel = canvas.pixel(j, i);
                double hitCountLog = Math.log10(pixel.hitCount());

                double maxLog = Math.max(hitCountLog, max);
                double normal = hitCountLog / maxLog;

                double gammaCorrection = Math.pow(normal, 1.0 / gamma);

                int r = (int) ((pixel.r() * gammaCorrection)) % 256;
                int g = (int) ((pixel.g() * gammaCorrection)) % 256;
                int b = (int) ((pixel.b() * gammaCorrection)) % 256;

                canvas.data()[i][j] = new Pixel(r, g, b, pixel.hitCount());
            }
        }
    }

    private void updatePixel(FractalImage canvas, Rect world, Point targetPoint, Pixel sourcePixel) {

        var pixelForUpdate = getPixelCoordinate(world, targetPoint, canvas);

        int x = pixelForUpdate[0];
        int y = pixelForUpdate[1];
//        int x = (int) ((sourcePoint.x() - world.x()) / world.width() * canvas.width());
//        int y = (int) ((sourcePoint.y() - world.y()) / world.height() * canvas.height());

        if (canvas.contains(x, y)) {
            Pixel targetPixel = canvas.pixel(x, y);

            int updatedHitCount = targetPixel.hitCount() + 1;
            int updatedR = sourcePixel.r();
            int updatedG = sourcePixel.g();
            int updatedB = sourcePixel.b();

//            updatedR = (updatedR + direction()) % HIGH_BOUND;
//            updatedG = (updatedG + direction()) % HIGH_BOUND;
//            updatedB = (updatedB + direction()) % HIGH_BOUND;

            Pixel newPixel = new Pixel(updatedR, updatedG, updatedB, updatedHitCount);
            canvas.data()[y][x] = newPixel;
        }

    }

    private int direction() {
        var bool = random.nextBoolean();
        return bool ? 2 : -2;
    }

    private Pixel setPixelColor(Pixel pixel) {
        if (pixel.hitCount() == 0) {
            int r = generateRandomColorComponent();
            int g = generateRandomColorComponent();
            int b = generateRandomColorComponent();
            return new Pixel(r, g, b, 0);
        }
        return pixel;
    }

    private Pixel mapRange(Rect world, Point pwr, FractalImage canvas) {
        if (!world.contains(pwr)) {
            return null;
        }

        var pixelCoordinate = getPixelCoordinate(world, pwr, canvas);

        int x = pixelCoordinate[0];
        int y = pixelCoordinate[1];


        return canvas.pixel(x, y);
    }

    private int[] getPixelCoordinate(Rect world, Point point, FractalImage canvas) {
        int x = (int) ((point.x() - world.x()) / world.width() * canvas.width());
        int y = (int) ((point.y() - world.y()) / world.height() * canvas.height());
        return new int[]{x, y};
    }


    private int generateRandomColorComponent() {
        return random.nextInt(HIGH_BOUND);
    }

    public Point rotate(Point point, double theta) {
        double x = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        double y = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(x, y);
    }

    private Transformation randomVariations(List<Transformation> variations) {

        int index = random.nextInt(0, variations.size());
        return variations.get(index);
    }

    private Point randomPoint(Rect world) {
        double x = world.x() + random.nextDouble(world.width());
        double y = world.y() + random.nextDouble(world.height());
        return new Point(x, y);
    }

}
