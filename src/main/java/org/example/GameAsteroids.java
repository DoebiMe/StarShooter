package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameAsteroids {
    private static final ArrayList<GameSprite> gameSpriteArrayList = new ArrayList<>();


    public static void add(IdAndImg idAndImg, double xPos, double yPos) {
        gameSpriteArrayList.add(new GameSprite(idAndImg, xPos, yPos));
    }

    public static void doMovementOnAllAsteroids(JPanel gamePlayField) {
        Random rn = new Random();

        for (GameSprite gameSprite : gameSpriteArrayList) {
            gameSprite.setyPos(gameSprite.getyPos() + gameSprite.getVelocity());
            if (gameSprite.getyPos() > gamePlayField.getHeight()+ 100) {
                double randomVerticalPosToStop = rn.nextDouble(1000) + gameSprite.getVelocity();
                if (randomVerticalPosToStop > 800) {
                    gameSprite.setyPos(- Math.max(rn.nextDouble(800),gameSprite.getIdAndImg().getBufferedImage(gameSprite.getIndexInBufferedImages()).getHeight() )); // hieronder rekeneing houden met de scaling
                    int randomHorizontalPosToStart = rn.nextInt(gamePlayField.getWidth() - gameSprite.getIdAndImg().getBufferedImage(gameSprite.getIndexInBufferedImages()).getWidth()/2) + 1;
                    gameSprite.setxPos(randomHorizontalPosToStart);
                    gameSprite.setRandomVelocity();
                }
            }
        }
    }

    public static void drawAllAsteroids(Graphics g) {
        for (GameSprite gameSprite : gameSpriteArrayList) { // hier moeten we een scaling toepassen in de index buffered image
            g.drawImage(gameSprite.getIdAndImg().getBufferedImage(gameSprite.getIndexInBufferedImages()), (int)gameSprite.getxPos(), (int)gameSprite.getyPos(), null);
        }
    }

}
