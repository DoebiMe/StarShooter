package org.example;


import java.awt.*;
import java.util.ArrayList;

public class GameUFOs {

 private static final ArrayList<GameSprite> gameSpriteArrayList = new ArrayList<>();


    public static void add(IdAndImg idAndImg, double xPos, double yPos) {
        gameSpriteArrayList.add(new GameSprite(idAndImg, xPos, yPos));
    }

    public static void doMovementOnAllUFOs(double xPos,double yPos) {
        for (GameSprite gameSprite : gameSpriteArrayList) {
            gameSprite.setxPos(xPos);
            gameSprite.setyPos(yPos);
            }
        }


    public static void drawAllUFOs(Graphics g) {
        for (GameSprite gameSprite : gameSpriteArrayList) {
            g.drawImage(gameSprite.getIdAndImg().getBufferedImage(0), (int)gameSprite.getxPos(), (int) gameSprite.getyPos(), null);
        }

    }

}

