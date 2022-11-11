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
        try {
            for (int index = 1;index<=5;index++) {
                BufferedImage[] bufferedImagesForNewAsteroid = new BufferedImage[IdAndImg.MAX_NR_IMAGES_BUFFER_SIZE];
                BufferedImage originalBufferedImage = getImageFromResource(classLoader, "img/asteroid" + index + "-400.png");
                for (int scaleSize=0;scaleSize < IdAndImg.MAX_NR_IMAGES_BUFFER_SIZE; scaleSize++) {
                    bufferedImagesForNewAsteroid[scaleSize] =
                            scale(Objects.requireNonNull(originalBufferedImage), 0.10 + (scaleSize*0.05));
                }

                IdAndImg idAndImg = new IdAndImg( bufferedImagesForNewAsteroid, index);
                System.out.println("Asteroid upload ok "+index);
                GameAsteroids.add(idAndImg, 50, 100);
            }

        } catch (NullPointerException exception) {
            System.out.println("GameImageCollection upload Asteroid1" + exception.getMessage());
        }
    }
    private static void uploadUFOsImages(ClassLoader classLoader) {
        try {
            for (int index = 1;index<=1;index++) {
                BufferedImage[] bufferedImages = new BufferedImage[IdAndImg.MIN_NR_IMAGES_BUFFER_SIZE];
                bufferedImages[0] = scale(Objects.requireNonNull(getImageFromResource(classLoader, "img/UFO" + index + ".png")), 0.25);
                IdAndImg idAndImg = new IdAndImg(bufferedImages, index);
                System.out.println("UFO upload ok "+index);
                GameUFOs.add(idAndImg, 50, 100);
            }

        } catch (NullPointerException exception) {
            System.out.println("GameImageCollection upload UFO" + exception.getMessage());
        }
    }

    private static BufferedImage getImageFromResource(ClassLoader classLoader,String resourceName) {
        try {
            return ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream(resourceName)));
        } catch (IOException ioException) {
            return null;
        }
    }

    private static BufferedImage scale(BufferedImage originalBufferedImage, double scaleFactor) {
        BufferedImage resultScaledBufferImage = new BufferedImage(
                (int) (originalBufferedImage.getWidth() * scaleFactor),
                (int) (originalBufferedImage.getHeight() * scaleFactor), BufferedImage.TYPE_INT_ARGB);
        AffineTransform scaleInstance = AffineTransform.getScaleInstance(scaleFactor, scaleFactor);
        AffineTransformOp scaleOp
                = new AffineTransformOp(scaleInstance, AffineTransformOp.TYPE_BILINEAR);

        Graphics2D g2 = (Graphics2D) resultScaledBufferImage.getGraphics();
        // Here, you may draw anything you want into the new image, but we're
        // drawing a scaled version of the original image.
        g2.drawImage(originalBufferedImage, scaleOp, 0, 0);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g2.setColor(Color.RED);
        g2.drawString(Double.toString(scaleFactor),10,15);
        g2.dispose();
        return resultScaledBufferImage;
    }
    public static BufferedImage getMainImage() {
        return mainImage;
    }
}
