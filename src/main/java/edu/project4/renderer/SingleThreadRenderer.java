package edu.project4.renderer;

import edu.project4.skelet.FractalImage;
import edu.project4.skelet.Pixel;
import edu.project4.skelet.Point;
import edu.project4.skelet.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SingleThreadRenderer extends AbstractRenderer {


    public SingleThreadRenderer(long seed, int symmetry) {
        random = new Random(seed);
        this.symmetry = symmetry;

    }

    public SingleThreadRenderer() {
        this(DEFAULT_SYMMETRY);
    }

    public SingleThreadRenderer(int symmetry) {
        random = ThreadLocalRandom.current();
        this.symmetry = symmetry;
    }

    @Override
    public FractalImage render(FractalImage canvas, Rect world, List<Transformation> variations,
                               int samples, int iterPerSample) {

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

                    if (!world.contains(rotatedMainPoint)) {
                        continue;
                    }

                    updatePixel(canvas, world, rotatedMainPoint, sourcePixel);

                    sourcePixel = mapRange(world, rotatedMainPoint, canvas);
                }
            }
        }

        return canvas;
    }


}
