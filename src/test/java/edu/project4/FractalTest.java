package edu.project4;

import edu.project4.renderer.AbstractRenderer;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.skelet.FractalImage;
import edu.project4.skelet.ImageFormat;
import edu.project4.skelet.ImageUtils;
import edu.project4.skelet.Pixel;
import edu.project4.skelet.Rect;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.non_linear.DiskTransformation;
import edu.project4.transformation.non_linear.PolarTransformation;
import edu.project4.transformation.non_linear.SphereTransformation;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FractalTest {

    Rect world = new Rect(-2, -3.5, 5, 7.0);
    List<Transformation> variations = List.of(
            new AffineTransformation(),
            new PolarTransformation(),
            new DiskTransformation(),
            new SphereTransformation()
    );
    int samples = 300;
    int iterPerSamples = 40;
    int width = 300;
    int height = 300;
    int threads;
    int symmetry;

    @Test
    public void multiThreadFailed() {

        assertThrows(NullPointerException.class, () -> {
            new MultiThreadRenderer(1, 1, null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(100, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(2, -5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(-2, 1);
        });

    }

    @Test
    public void multiRenderFailed() {

        assertThrows(NullPointerException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    null, world, variations, samples, iterPerSamples
            );
        });
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    FractalImage.create(2, 2), null, variations, samples, iterPerSamples);
        });
        assertThrows(NullPointerException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    FractalImage.create(2, 2), world, null, samples, iterPerSamples);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    FractalImage.create(-2, -2), world, variations, samples, iterPerSamples);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    FractalImage.create(2, 2), world, new ArrayList<>(), samples, iterPerSamples);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    FractalImage.create(2, 2), world, variations, -5, iterPerSamples);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new MultiThreadRenderer(1, 1).render(
                    FractalImage.create(2, 2), world, variations, 5, -5);
        });


    }

    @Test
    public void gammaCorrectionTest() {
        assertThrows(NullPointerException.class, () -> {
            ImageUtils.gammaCorrection(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ImageUtils.gammaCorrection(new FractalImage(new Pixel[][]{}, 1, 2), -5);
        });
    }

    @Test
    public void generateCorrect() {
        assertDoesNotThrow(() -> {
            AbstractRenderer renderer = new MultiThreadRenderer(4, 5);
            FractalImage image = renderer.render(FractalImage.create(width, height), world, variations, samples, iterPerSamples);
            ImageUtils.save(image, Path.of("for_del.png"), ImageFormat.PNG);
            Files.delete(Path.of("for_del.png"));
        });


    }
}
