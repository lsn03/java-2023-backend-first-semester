package edu.project4.renderer;

import edu.project4.skelet.FractalImage;
import edu.project4.skelet.Pixel;
import edu.project4.skelet.Point;
import edu.project4.skelet.Rect;
import edu.project4.transformation.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MultiThreadRenderer extends AbstractRenderer {
    private final int threads;

    public MultiThreadRenderer(int threads) {
        this(threads, DEFAULT_SYMMETRY);

    }

    public MultiThreadRenderer(int threads, int symmetry) {
        this(threads, symmetry, ThreadLocalRandom.current());
    }

    public MultiThreadRenderer(int threads, int symmetry, Random random) {
        validateConstructorArgs(random, threads, symmetry);
        this.random = random;
        this.threads = threads;
        this.symmetry = symmetry;
    }


    @Override
    public FractalImage render(FractalImage canvas, Rect world, List<Transformation> variations,
                               int samples, int iterPerSample) {
        super.validateRenderData(canvas, world, variations, samples, iterPerSample);

        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        List<Future<Void>> futures = new ArrayList<>();

        int pointPerThread = samples / threads;
        for (int i = 0; i < threads; i++) {
            int start = i * pointPerThread;
            int end = (i + 1) * pointPerThread;
            Callable<Void> renderTask = () -> {
                renderPoint(canvas, world, variations, start, end, iterPerSample);
                return null;
            };
            futures.add(executorService.submit(renderTask));
        }
        for (Future<Void> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdown();
        return canvas;
    }


    private void renderPoint(FractalImage canvas, Rect world, List<Transformation> variations,
                             int start, int end, int iterPerSample) {
        for (int num = start; num < end; ++num) {
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

                    var pixelForUpdate = getPixelCoordinate(world, rotatedMainPoint, canvas);

                    int x = pixelForUpdate[0];
                    int y = pixelForUpdate[1];

                    synchronized (canvas.pixel(x, y)) {
                        updatePixel(canvas, world, rotatedMainPoint, sourcePixel);
                    }
                    sourcePixel = mapRange(world, rotatedMainPoint, canvas);
                }
            }
        }
    }



    private void validateConstructorArgs(Random random, int threads, int symmetry) {
        Objects.requireNonNull(random);
        if (threads < 0 || threads > Runtime.getRuntime().availableProcessors()) {
            throw new IllegalArgumentException();
        }
        if (symmetry < 0) {
            throw new IllegalArgumentException();
        }
    }
}
