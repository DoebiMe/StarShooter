package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRocketBombs {

    public static final int MAX_ROCKET_BOMBS = 50;
    private static final ArrayList<GameSprite> gameSpriteArrayList = new ArrayList<>();

    public static void init(IdAndImg idAndImg) {
        for (int index = 0;index<MAX_ROCKET_BOMBS;index++) {
            GameSprite gameSprite = new GameSprite(idAndImg,0,0);
            gameSprite.setActive(false);
            gameSpriteArrayList.add(gameSprite);
        }
    }

    public static GameSprite activateRocketBombReturn( double xPos, double yPos) {
        for (GameSprite gameSprite:gameSpriteArrayList) {
            if (!gameSprite.isActive()) {
                gameSprite.setxPos(xPos);
                gameSprite.setyPos(yPos);
                gameSprite.setVelocity(7);
                gameSprite.setActive(true);
                return gameSprite;
            }
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

}

