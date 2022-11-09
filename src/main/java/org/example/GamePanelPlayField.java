package org.example;

import javax.swing.*;
import java.awt.*;

class GamePanelPlayField extends JPanel {


    private int mainFigureX,mainFigureY;
    float interpolation;
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
        mainFigureX = this.width / 2 - (GameImageCollection.getMainImage().getWidth()/2);
        mainFigureY = this.height - (GameImageCollection.getMainImage().getHeight() *2);
    }

    public void setInterpolation(float interp) {
        interpolation = interp;
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

    private final static RenderingHints textRenderHints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    private final static RenderingHints imageRenderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private final static RenderingHints colorRenderHints = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    private final static RenderingHints interpolationRenderHints = new RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    private final static RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

    public void applyRenderHints(Graphics2D g2d) {
        g2d.setRenderingHints(textRenderHints);
        g2d.setRenderingHints(imageRenderHints);
        g2d.setRenderingHints(colorRenderHints);
        g2d.setRenderingHints(interpolationRenderHints);
        g2d.setRenderingHints(renderHints);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //applys effects like anti alising for images and tetx, as well as sets the renderinf value to quality etc
        applyRenderHints(g2d);


        g2d.setColor(Color.RED);


        int drawX = (int) ((ballX - lastBallX) + lastBallX - ballWidth / 2);
        int drawY = (int) ((ballY - lastBallY) + lastBallY - ballHeight / 2);

        g2d.fillOval(drawX, drawY, ballWidth, ballHeight);

        lastDrawX = drawX;
        lastDrawY = drawY;

        //g2d.setColor(Color.BLACK);
        //g2d.drawString("FPS: " + fps, 5, 10);


        try {
            g.drawImage(GameImageCollection.getMainImage(),mainFigureX,mainFigureY,null);
        } catch (Exception exception) {
            System.out.println("Error during drawImage mainImage");
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
}
