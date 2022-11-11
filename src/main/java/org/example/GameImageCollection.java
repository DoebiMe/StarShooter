package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Objects;

public class GameImageCollection {
    static private BufferedImage mainImage = null;

    static private LinkedHashSet<IdAndImg> asteroids = null;

    static void Init(ClassLoader classLoader){
        uploadMainImage(classLoader);
        uploadAsteroidImages(classLoader);
        uploadUFOsImages(classLoader);
    }

    private static void uploadMainImage(ClassLoader classLoader) {
        try {
            mainImage = scale(Objects.requireNonNull(getImageFromResource(classLoader, "img/ship.png")),0.25);
        } catch (NullPointerException exception) {
            System.out.println("GameImageCollection uploadMainImage " + exception.getMessage());
        }
    }


    private static void uploadAsteroidImages(ClassLoader classLoader) {
        asteroids = new LinkedHashSet<>();
        try {
            for (int index = 1;index<=5;index++) {
                BufferedImage[] bufferedImages = new BufferedImage[IdAndImg.MAX_NR_IMAGES_BUFFER_SIZE];
                bufferedImages[0] = getImageFromResource(classLoader, "img/asteroid" + Integer.toString(index) + ".png");
                bufferedImages[1] = scale(Objects.requireNonNull(bufferedImages[0]),0.05);
                bufferedImages[2] = scale(Objects.requireNonNull(bufferedImages[0]),0.10);
                bufferedImages[3] = scale(Objects.requireNonNull(bufferedImages[0]),0.20);
                bufferedImages[4] = scale(Objects.requireNonNull(bufferedImages[0]),0.50);

                IdAndImg idAndImg = new IdAndImg( bufferedImages, index);
                System.out.println("Asteroid upload ok "+index);
                GameAsteroids.add(idAndImg, 50, 100);
            }

        } catch (NullPointerException exception) {
            System.out.println("GameImageCollection upload Astroid1" + exception.getMessage());
        }
    }
    private static void uploadUFOsImages(ClassLoader classLoader) {
        try {
            for (int index = 1;index<=1;index++) {
                BufferedImage[] bufferedImages = new BufferedImage[IdAndImg.MIN_NR_IMAGES_BUFFER_SIZE];
                bufferedImages[0] = scale(Objects.requireNonNull(getImageFromResource(classLoader, "img/UFO" + Integer.toString(index) + ".png")), 0.25);
                IdAndImg idAndImg = new IdAndImg(bufferedImages, index);
                System.out.println("UFO upload ok "+index);
                GameUFOs.add(idAndImg, 50, 100);
            }

        } catch (NullPointerException exception) {
            System.out.println("GameImageCollection upload Astroid1" + exception.getMessage());
        }
    }

    public static IdAndImg getIdAndImgWithId(int id) {
        for (IdAndImg idAndImg : asteroids) {
            if (idAndImg.getId() == id) {
                return idAndImg;
            }
        }
        return null;
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
