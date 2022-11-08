package org.example;

import javax.swing.*;

public class GamePanelStatusBar extends JPanel {
    private final JLabel labelLives;
    private final JLabel fieldLives;
    private int lives = 0;
    public GamePanelStatusBar() {
        labelLives = new JLabel("Lives ");
        fieldLives = new JLabel(Integer.toString(lives));
        add(labelLives);
        add(fieldLives);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
        fieldLives.setText(Integer.toString(lives));
    }
}
