package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameImageCollection {
    static private BufferedImage mainImage = null;
    static void Init(ClassLoader classLoader){
        try {
            mainImage = scale(Objects.requireNonNull(getImageFromResource(classLoader, "img/ship.png")),0.25);
        } catch (NullPointerException exception) {
            System.out.println("GameImageCollection " + exception.getMessage());
        }
    }
    private static BufferedImage getImageFromResource(ClassLoader classLoader,String resourceName) {
        try {
            return ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream(resourceName)));
        } catch (IOException ioException) {
            return null;
        }
    }

    private static BufferedImage scale(BufferedImage before, double scale) {
        int w = before.getWidth();
        int h = before.getHeight();
        // Create a new image of the proper size
        int w2 = (int) (w * scale);
        int h2 = (int) (h * scale);
        BufferedImage after = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_ARGB);
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scale, scale);
        AffineTransformOp scaleOp
                = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

        Graphics2D g2 = (Graphics2D) after.getGraphics();
        // Here, you may draw anything you want into the new image, but we're
        // drawing a scaled version of the original image.
        g2.drawImage(before, scaleOp, 0, 0);
        g2.dispose();
        return after;
    }


    public static BufferedImage getMainImage() {
        return mainImage;
    }
}
