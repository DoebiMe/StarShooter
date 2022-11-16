package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRocketBombs {

    public static final long MIN_TIME_IN_MS_BETWEEN_ROCKET_BOMB_LAUNCHES = 300;
    public static final int MAX_ROCKET_BOMBS = 50;
    private static final ArrayList<GameSprite> gameSpriteArrayList = new ArrayList<>();


    private static long lastTimeRocketBombLaunch=0;

    public static void init(IdAndImg idAndImg) {
        for (int index = 0;index<MAX_ROCKET_BOMBS;index++) {
            GameSprite gameSprite = new GameSprite(idAndImg,0,0);
            gameSprite.setActive(false);
            gameSpriteArrayList.add(gameSprite);
        }
        lastTimeRocketBombLaunch = System.currentTimeMillis();
    }

    public static GameSprite activateRocketBombReturn( double xPos, double yPos) {
        if (lastTimeRocketBombLaunch < System.currentTimeMillis() - MIN_TIME_IN_MS_BETWEEN_ROCKET_BOMB_LAUNCHES) {
            for (GameSprite gameSprite : gameSpriteArrayList) {
                if (!gameSprite.isActive()) {
                    lastTimeRocketBombLaunch = System.currentTimeMillis();
                    gameSprite.setxPos(xPos);
                    gameSprite.setyPos(yPos);
                    gameSprite.setVelocity(12);
                    gameSprite.setActive(true);
                    return gameSprite;
                }
            }
        } else {
            System.out.println("To few time between shooting");
            System.out.println(System.currentTimeMillis());
        }
        return null;
    }

    public static void doMovementOnAllRocketBombs(JPanel gamePlayField) {
        for (GameSprite gameSprite : gameSpriteArrayList) {
            if (gameSprite.isActive()) {
                gameSprite.setyPos(gameSprite.getyPos() - gameSprite.getVelocity());
                if (gameSprite.getyPos() < gamePlayField.getY() -gameSprite.getIdAndImg().getHeightBufferedImage(0)) {
                    gameSprite.setActive(false);
                }
            }
        }
    }

    public static void drawAllRocketBombs(Graphics g) {
        for (GameSprite gameSprite : gameSpriteArrayList) { // hier moeten we een scaling toepassen in de index buffered image
            g.drawImage(gameSprite.getIdAndImg().getBufferedImage(gameSprite.getIndexInBufferedImages()), (int) gameSprite.getxPos(), (int) gameSprite.getyPos(), null);
        }
    }

    public static double getHalveWidthRocketBomb0() {
        return gameSpriteArrayList.get(0).getHalveWidth(0);
    }

}

