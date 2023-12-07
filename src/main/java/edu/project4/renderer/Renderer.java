package edu.project4.renderer;

import edu.project4.skelet.FractalImage;
import edu.project4.skelet.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(FractalImage canvas, Rect world,
                        List<Transformation> variations, int samples, int iterPerSample);
}
