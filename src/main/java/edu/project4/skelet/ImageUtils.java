package edu.project4.skelet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage bufferedImage = convertToBufferedImage(image);

        try {
            ImageIO.write(bufferedImage, format.name().toLowerCase(), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static BufferedImage convertToBufferedImage(FractalImage image) {
        BufferedImage bufferedImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.width(); i++) {
            for (int j = 0; j < image.height(); j++) {
                Pixel pixel = image.pixel(i, j);
                int rgb = (pixel.r() << 16) | (pixel.g() << 8) | pixel.b();
                bufferedImage.setRGB(i, j, rgb);
            }
        }
        return bufferedImage;
    }
}
