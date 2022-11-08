package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameImageCollection {
    static private BufferedImage mainImage = null;
    static void Init(ClassLoader classLoader){
        try {
            mainImage = getImageFromResource(classLoader,"img/ship.png");
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

    public static BufferedImage getMainImage() {
        return mainImage;
    }
}
