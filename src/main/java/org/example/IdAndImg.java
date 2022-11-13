package org.example;

import java.awt.image.BufferedImage;

public class IdAndImg {

    public static final int MAX_NR_IMAGES_BUFFER_SIZE = 5;
    public static final int MIN_NR_IMAGES_BUFFER_SIZE = 1;
    final private int id;
    private BufferedImage[] bufferedImages;

    public IdAndImg(BufferedImage[] bufferedImages, int id) {
        this.id = id;
        try {
            if (bufferedImages.length > MAX_NR_IMAGES_BUFFER_SIZE) {
                throw new Exception("To mush images");
            }
            this.bufferedImages = bufferedImages;
        } catch (Exception exception) {
            System.out.println("To mush images in IdAndImg " + exception);
            System.exit(0);
        }
    }

    public BufferedImage getBufferedImage(int index) {

        if (index < bufferedImages.length) {
            return bufferedImages[index];
        }
        return bufferedImages[0];
    }


    public int getBufferedImageSizeZeroBased() {
        return bufferedImages.length;
    }


    public double getWidthBufferedImage(int index) {
        return bufferedImages[index].getWidth();
    }

    public double getHeightBufferedImage(int index) {
        return bufferedImages[index].getHeight();
    }

    public double getMiddleHorizontalPointBufferedImage(int index) {
        return bufferedImages[index].getWidth() / 2;
    }

}

