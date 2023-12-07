package edu.project4;

import edu.project4.renderer.SingleThreadRenderer;
import edu.project4.skelet.FractalImage;
import edu.project4.skelet.ImageFormat;
import edu.project4.skelet.ImageUtils;
import edu.project4.skelet.Rect;
import edu.project4.transformation.AffineTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.transformation.non_linear.DiskTransformation;
import edu.project4.transformation.non_linear.HeartTransformation;
import edu.project4.transformation.non_linear.PolarTransformation;
import edu.project4.transformation.non_linear.SinusoidTransformation;
import edu.project4.transformation.non_linear.SphereTransformation;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SingleThreadRenderer renderer = new SingleThreadRenderer();
        Rect world = new Rect(-2, -3.5, 5, 7.0);
        List<Transformation> variations = List.of(
                new AffineTransformation(),
                new PolarTransformation(),
//                new SinusoidTransformation(),
//                new HeartTransformation(),
                new DiskTransformation(),

                new SphereTransformation()
        );

        int samples = 300;
        int iterPerSamples = 4000;
        FractalImage fractalImage = renderer.render(FractalImage.create(3000, 3000),
                world, variations, samples, iterPerSamples);
        ImageUtils.save(fractalImage, Paths.get("output.png"), ImageFormat.PNG);
    }
}
