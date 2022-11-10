package org.example;

import java.awt.image.BufferedImage;

public class IdAndImg {
    private BufferedImage bufferedImage;
    private int id;

    public IdAndImg(BufferedImage bufferedImage, int id) {
        this.bufferedImage = bufferedImage;
        this.id = id;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

