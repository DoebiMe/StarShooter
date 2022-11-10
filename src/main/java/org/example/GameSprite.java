package org.example;

import java.util.Random;

public class GameSprite {
    private IdAndImg idAndImg;
    private int xPos;
    private int yPos;

    private int velocity;
    private boolean active;

    public GameSprite(IdAndImg idAndImg, int xPos, int yPos) {
        this.idAndImg = idAndImg;
        this.xPos = xPos;
        this.yPos = yPos;
        setRandomVelocity();
    }

    public  void setRandomVelocity() {
        Random rn = new Random();
        int oldVelocity = velocity;
        while (oldVelocity==velocity) {
            velocity = rn.nextInt(10) + 1;
        }
    }

    public IdAndImg getIdAndImg() {
        return idAndImg;
    }

    public void setIdAndImg(IdAndImg idAndImg) {
        this.idAndImg = idAndImg;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getVelocity() {
        return velocity;
    }
}
