package org.example;

import javax.swing.*;
import java.awt.*;

class GamePanelPlayField extends JPanel {


    final private double initialMainFigureX, initialMainFigureY;
    //float interpolation;
    float ballX, ballY, lastBallX, lastBallY;
    double ballWidth, ballHeight;
    float ballXVel, ballYVel;
    float ballSpeed;
    double lastDrawX, lastDrawY;
    double width, height;
    private double mainFigureX, mainFigureY;
    private double mainFigureMinX, mainFigureMaxX;
    private double mainFigureMinY, mainFigureMaxY;
    private double mainFigureHorizontalVelocity;
    private double mainFigureVerticalVelocity;

    public GamePanelPlayField(double width, double height) {
        super(true);
        ballX = lastBallX = 100;
        ballY = lastBallY = 100;
        ballWidth = 25;
        ballHeight = 25;
        ballSpeed = 25;
        ballXVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        ballYVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        this.width = (int) width;
        this.height = (int) height;
        initialMainFigureX = this.width / 2 - (GameImageCollection.getMainImage().getWidth() / 2);
        initialMainFigureY = this.height - (GameImageCollection.getMainImage().getHeight() * 2);
        positionMainFigureToInitialPosition();

        setMainFigureHorizontalVelocity(5);
        setMainFigureVerticalVelocity(5);

    }

    public void positionMainFigureToInitialPosition() {
        mainFigureX = initialMainFigureX;
        mainFigureY = initialMainFigureY;
    }

    public void repositionMainFigureInsideAllowedZone() {
        recalculateMainFigurePlayFieldSize();

        mainFigureX = Math.max(mainFigureMinX, mainFigureX);
        mainFigureX = Math.min(mainFigureMaxX, mainFigureX);
        mainFigureY = Math.max(mainFigureMinY, mainFigureY);
        mainFigureY = Math.min(mainFigureMaxY, mainFigureY);

    }

    private void recalculateMainFigurePlayFieldSize() {

        mainFigureMinX = 10 - (GameImageCollection.getMainImage().getWidth() / 2);
        mainFigureMaxX = this.getParent().getWidth() - 10 - (GameImageCollection.getMainImage().getWidth() / 2);
        mainFigureMaxY = this.getParent().getHeight() - 50;
        mainFigureMinY = this.getParent().getHeight() - 3 * GameImageCollection.getMainImage().getHeight();
    }

    public void update() {

        lastBallX = ballX;
        lastBallY = ballY;

        ballX += ballXVel;
        ballY += ballYVel;

        if (ballX + ballWidth / 2 >= getWidth()) {
            ballXVel *= -1;
            ballX = (float) (getWidth() - ballWidth / 2);
            ballYVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        } else if (ballX - ballWidth / 2 <= 0) {
            ballXVel *= -1;
            ballX = (float) (ballWidth / 2);
        }

        if (ballY + ballHeight / 2 >= getHeight()) {
            ballYVel *= -1;
            ballY = (float) (getHeight() - ballHeight / 2);
            ballXVel = (float) Math.random() * ballSpeed * 2 - ballSpeed;
        } else if (ballY - ballHeight / 2 <= 0) {
            ballYVel *= -1;
            ballY = (float) (ballHeight / 2);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.RED);

        double drawX =  (ballX - lastBallX) + lastBallX - ballWidth / 2;
        double drawY =  (ballY - lastBallY) + lastBallY - ballHeight / 2;

        lastDrawX = drawX;
        lastDrawY = drawY;


        try {
            GameAsteroids.doMovementOnAllAsteroids(this);
            GameAsteroids.drawAllAsteroids(g);
        } catch (Exception exception) {
            System.out.println("Error during drawAllAsteroids " + exception.getMessage());
        }

        try {
            GameUFOs.doMovementOnAllUFOs((int) ballX, (int) ballY);
            GameUFOs.drawAllUFOs(g);
        } catch (Exception exception) {
            System.out.println("Error during drawAllUFO");
        }

        try {
            g.drawImage(GameImageCollection.getMainImage(), (int) mainFigureX, (int) mainFigureY, null);
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
        return new Dimension( (int)width, (int) height);
    }

    public double getMainFigureX() {
        return mainFigureX;
    }

    public void setMainFigureX(double mainFigureX) {
        this.mainFigureX = mainFigureX;
    }

    public double getMainFigureY() {
        return mainFigureY;
    }

    public void setMainFigureY(double mainFigureY) {
        this.mainFigureY = mainFigureY;
    }

    public double getMainFigureHorizontalVelocity() {
        return mainFigureHorizontalVelocity;
    }

    public void setMainFigureHorizontalVelocity(int mainFigureHorizontalVelocity) {
        this.mainFigureHorizontalVelocity = mainFigureHorizontalVelocity;
    }

    public double getMainFigureVerticalVelocity() {
        return mainFigureVerticalVelocity;
    }

    public void setMainFigureVerticalVelocity(int mainFigureVerticalVelocity) {
        this.mainFigureVerticalVelocity = mainFigureVerticalVelocity;
    }
}
