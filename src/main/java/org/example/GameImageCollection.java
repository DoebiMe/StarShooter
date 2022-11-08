package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameImageCollection {
    static private BufferedImage mainImage = null;
    static void Init(ClassLoader classLoader){
        try {
            //InputStream path = this.getClass().getClassLoader().getResourceAsStream("img/ship.png");
            //mainImage= ImageIO.read(path);
            mainImage = ImageIO.read(Objects.requireNonNull(classLoader.getResourceAsStream("img/ship.png")));
        } catch (NullPointerException | IOException exception) {
            System.out.println("GameImageCollection " + exception.getMessage());
        }
    }

    public static BufferedImage getMainImage() {
        return mainImage;
    }
}
