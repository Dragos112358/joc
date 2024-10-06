package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static BufferedImage colorize(BufferedImage image, Color newColor, Color clothingColor, int tolerance) {
        BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);

                // Verifică dacă pixelul se încadrează în toleranța de culoare a hainelor
                if (col.getAlpha() > 0 && isColorSimilar(col, clothingColor, tolerance)) {
                    result.setRGB(x, y, newColor.getRGB());
                } else {
                    result.setRGB(x, y, rgba);
                }
            }
        }
        return result;
    }

    private static boolean isColorSimilar(Color color, Color targetColor, int tolerance) {
        return Math.abs(color.getRed() - targetColor.getRed()) <= tolerance &&
                Math.abs(color.getGreen() - targetColor.getGreen()) <= tolerance &&
                Math.abs(color.getBlue() - targetColor.getBlue()) <= tolerance;
    }
}
