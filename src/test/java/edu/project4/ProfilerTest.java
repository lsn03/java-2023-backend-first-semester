package edu.project4;

import edu.project4.renderer.AbstractRenderer;
import edu.project4.renderer.MultiThreadRenderer;
import edu.project4.skelet.FractalImage;
import edu.project4.skelet.Rect;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.non_linear.DiskTransformation;
import edu.project4.transformation.non_linear.PolarTransformation;
import edu.project4.transformation.non_linear.SphereTransformation;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ProfilerTest {
    Rect world = new Rect(-2, -3.5, 5, 7.0);
    List<Transformation> variations = List.of(
            new AffineTransformation(),
            new PolarTransformation(),
            new DiskTransformation(),
            new SphereTransformation()
    );
    int samples = 30000;
    int iterPerSamples = 4000;
    int width = 3000;
    int height = 3000;

    int symmetry = 5;

    @ParameterizedTest
    @CsvSource(value = {
            "1",
            "2",
            "4",
            
    })
    public void threadsPerfomanceTest(int threads) {
        AbstractRenderer renderer = new MultiThreadRenderer(threads, symmetry);
        LocalDateTime start = LocalDateTime.now();
        FractalImage image = renderer.render(
                FractalImage.create(width, height),
                world, variations, samples, iterPerSamples);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println("threads = " + threads + " height = " + height + " width = " + width + " duration: " + duration.toMillis() / 1000.0 + " s");

    }
}
