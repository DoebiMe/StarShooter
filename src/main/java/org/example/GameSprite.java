package org.example;

import java.util.Random;

public class GameSprite {
    final private IdAndImg idAndImg;
    private double xPos;
    private double yPos;

    private double velocity;
    private boolean active;

    private int indexInBufferedImages;

    public GameSprite(IdAndImg idAndImg, double xPos, double yPos) {
        this.idAndImg = idAndImg;
        this.xPos = xPos;
        this.yPos = yPos;
        setRandomVelocity();
        setRandomIndexInBufferedImages();
    }

    public void setRandomIndexInBufferedImages() {
        Random random = new Random();
        indexInBufferedImages = random.nextInt(idAndImg.getBufferedImageSizeZeroBased()   ) ;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public  void setRandomVelocity() {
        Random rn = new Random();
        double oldVelocity = velocity;
        while (oldVelocity==velocity) {
            velocity = rn.nextDouble(10) + 1;
        }
    }

    public IdAndImg getIdAndImg() {
        return idAndImg;
    }



    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getVelocity() {
        return velocity;
    }

    public int getIndexInBufferedImages() {
        return indexInBufferedImages;
    }
}
