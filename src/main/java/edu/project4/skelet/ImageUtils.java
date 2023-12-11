package edu.project4.skelet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import javax.imageio.ImageIO;


public final class ImageUtils {
    private static final int HIGH_BOUND = 256;
    private static final double DEFAULT_GAMMA = 4.2;
    public static final int SHIFT_TO_RED = 16;
    public static final int SHIFT_TO_GREEN = 8;

    private ImageUtils() {
    }

    public static void gammaCorrection(FractalImage fractalImage) {
        gammaCorrection(fractalImage, DEFAULT_GAMMA);
    }

    public static void gammaCorrection(FractalImage canvas, double gamma) {
        validate(canvas, gamma);

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


        for (int i = 0; i < canvas.height(); i++) {
            for (int j = 0; j < canvas.width(); j++) {
                Pixel pixel = canvas.pixel(j, i);
                double hitCountLog = Math.log10(pixel.hitCount());

                double maxLog = Math.max(hitCountLog, max);
                double normal = hitCountLog / maxLog;

                double gammaCorrection = Math.pow(normal, 1.0 / gamma);

                int r = (int) ((pixel.r() * gammaCorrection)) % HIGH_BOUND;
                int g = (int) ((pixel.g() * gammaCorrection)) % HIGH_BOUND;
                int b = (int) ((pixel.b() * gammaCorrection)) % HIGH_BOUND;

                canvas.data()[i][j] = new Pixel(r, g, b, pixel.hitCount());
            }
        }

    }


    public static void save(FractalImage image, Path filename, ImageFormat format) {
        validate(image, filename, format);

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
                int rgb = (pixel.r() << SHIFT_TO_RED) | (pixel.g() << SHIFT_TO_GREEN) | pixel.b();
                bufferedImage.setRGB(i, j, rgb);
            }
        }
        return bufferedImage;
    }

    private static void validate(FractalImage canvas, Path filename, ImageFormat format) {
        validate(canvas, 1);

        Objects.requireNonNull(filename);
        Objects.requireNonNull(format);
        String fileNameString = filename.toString();
        if (fileNameString.isBlank() || fileNameString.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }


    private static void validate(FractalImage canvas, double gamma) {
        Objects.requireNonNull(canvas);
        if (gamma < 0) {
            throw new IllegalArgumentException();
        }
    }
}
