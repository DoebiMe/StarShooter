package org.example;

import javax.swing.*;
import java.awt.*;

class GamePanelPlayField extends JPanel {


    private int mainFigureX,mainFigureY;
    final private int initialMainFigureX,initialMainFigureY;
    final private int mainFigureMinX,mainFigureMaxX;
    final private int mainFigureMinY,mainFigureMaxY;
    private int mainFigureHorizontalVelocity;
    private int mainFigureVerticalVelocity;
    //float interpolation;
    float ballX, ballY, lastBallX, lastBallY;
    int ballWidth, ballHeight;
    float ballXVel, ballYVel;
    float ballSpeed;
    int lastDrawX, lastDrawY;

    int width, height;

    public GamePanelPlayField(int width, int height) {
        super(true);
        ballX = lastBallX = 100;
        ballY = lastBallY = 100;
        ballWidth = 25;
        ballHeight = 25;
        ballSpeed = 25;
        ballXVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        ballYVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        this.width = width;
        this.height = height;
        initialMainFigureX = this.width / 2 - (GameImageCollection.getMainImage().getWidth()/2) ;
        initialMainFigureY = this.height - (GameImageCollection.getMainImage().getHeight() *2);
        positionMainFigureToInitialPosition();
        mainFigureMinX = 10 - (GameImageCollection.getMainImage().getWidth()/2) ;
        mainFigureMaxX = this.width - 10 - (GameImageCollection.getMainImage().getWidth()/2) ;
        mainFigureMaxY = this.height - 50;
        mainFigureMinY = this.height - 3 * GameImageCollection.getMainImage().getHeight();
        setMainFigureHorizontalVelocity(5);
        setMainFigureVerticalVelocity(5);

    }

    public void positionMainFigureToInitialPosition(){
        mainFigureX = initialMainFigureX;
        mainFigureY = initialMainFigureY;
    }

    public void repositionMainFigureInsideAllowedZone(){

        mainFigureX = Math.max(mainFigureMinX,mainFigureX);
        mainFigureX = Math.min(mainFigureMaxX,mainFigureX);
        mainFigureY = Math.max(mainFigureMinY,mainFigureY);
        mainFigureY = Math.min(mainFigureMaxY,mainFigureY);

    }
    public void update() {

        lastBallX = ballX;
        lastBallY = ballY;

        ballX += ballXVel;
        ballY += ballYVel;

        if (ballX + ballWidth / 2 >= getWidth()) {
            ballXVel *= -1;
            ballX = getWidth() - ballWidth / 2;
            ballYVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        } else if (ballX - ballWidth / 2 <= 0) {
            ballXVel *= -1;
            ballX = ballWidth / 2;
        }

        if (ballY + ballHeight / 2 >= getHeight()) {
            ballYVel *= -1;
            ballY = getHeight() - ballHeight / 2;
            ballXVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        } else if (ballY - ballHeight / 2 <= 0) {
            ballYVel *= -1;
            ballY = ballHeight / 2;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        int drawX = (int) ((ballX - lastBallX) + lastBallX - ballWidth / 2);
        int drawY = (int) ((ballY - lastBallY) + lastBallY - ballHeight / 2);

        lastDrawX = drawX;
        lastDrawY = drawY;



        try {
            GameAsteroids.doMovementOnAllAsteroids(this);
            GameAsteroids.drawAllAsteroids(g);
        } catch (Exception exception) {
            System.out.println("Error during drawAllAsteroids " + exception.getMessage());
        }

        try {
            GameUFOs.doMovementOnAllUFOs((int)ballX,(int)ballY);
            GameUFOs.drawAllUFOs(g);
        } catch (Exception exception) {
            System.out.println("Error during drawAllUFO");
        }

        try {
            g.drawImage(GameImageCollection.getMainImage(),mainFigureX,mainFigureY,null);
        } catch (Exception exception) {
            System.out.println("Error during drawImage mainImage");
        }

        try {
            GameRocketBombs.doMovementOnAllRocketBombs(this);
            GameRocketBombs.drawAllRocketBombs(g);
        } catch (Exception exception) {
            System.out.println("Error during drawRocketBombs");
        }

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public int getMainFigureX() {
        return mainFigureX;
    }

    public void setMainFigureX(int mainFigureX) {
        this.mainFigureX = mainFigureX;
    }

    public int getMainFigureY() {
        return mainFigureY;
    }

    public void setMainFigureY(int mainFigureY) {
        this.mainFigureY = mainFigureY;
    }

    public int getMainFigureHorizontalVelocity() {
        return mainFigureHorizontalVelocity;
    }

    public void setMainFigureHorizontalVelocity(int mainFigureHorizontalVelocity) {
        this.mainFigureHorizontalVelocity = mainFigureHorizontalVelocity;
    }
    public int getMainFigureVerticalVelocity() {
        return mainFigureVerticalVelocity;
    }

    public void setMainFigureVerticalVelocity(int mainFigureVerticalVelocity) {
        this.mainFigureVerticalVelocity = mainFigureVerticalVelocity;
    }
}
