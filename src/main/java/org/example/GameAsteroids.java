package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameAsteroids {
    private static final ArrayList<GameSprite> gameSpriteArrayList = new ArrayList<>();


    public static void add(IdAndImg idAndImg, int xPos, int yPos) {
        gameSpriteArrayList.add(new GameSprite(idAndImg, xPos, yPos));
    }

    public static void doMovementOnAllAstroids(JPanel gamePlayField) {
        Random rn = new Random();

        for (GameSprite gameSprite : gameSpriteArrayList) {
            gameSprite.setyPos(gameSprite.getyPos() + gameSprite.getVelocity());
            if (gameSprite.getyPos() > gamePlayField.getHeight()+ 100) {
                int randomVerticalPosToStop = rn.nextInt(1000) + gameSprite.getVelocity();
                if (randomVerticalPosToStop > 800) {
                    gameSprite.setyPos(- Math.max(rn.nextInt(800),gameSprite.getIdAndImg().getBufferedImage().getHeight() ));
                    int randomHorizontalPosToStart = rn.nextInt(gamePlayField.getWidth() - gameSprite.getIdAndImg().getBufferedImage().getWidth()/2) + 1;
                    gameSprite.setxPos(randomHorizontalPosToStart);
                    gameSprite.setRandomVelocity();
                }
            }
        }
    }

    public static void drawAllAsteroids(Graphics g) {
        for (GameSprite gameSprite : gameSpriteArrayList) {
            g.drawImage(gameSprite.getIdAndImg().getBufferedImage(), gameSprite.getxPos(), gameSprite.getyPos(), null);
        }
    }

}
