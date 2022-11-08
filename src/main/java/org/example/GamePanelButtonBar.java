package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GamePanelButtonBar extends JPanel {

    final private JButton startButton;
    final private JButton quitButton;
    final private JButton pauseButton;


    public GamePanelButtonBar(ActionListener actionListener) {
        setLayout(new GridLayout(1, 2));
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");
        pauseButton = new JButton("Pause");
        pauseButton.setEnabled(false);

        startButton.addActionListener(actionListener);
        quitButton.addActionListener(actionListener);
        pauseButton.addActionListener(actionListener);

        add(startButton);
        add(pauseButton);
        add(quitButton);

        startButton.addKeyListener(new GameKeyListener());
        quitButton.addKeyListener(new GameKeyListener());
        pauseButton.addKeyListener(new GameKeyListener());

    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }

    public JButton getPauseButton() {
        return pauseButton;
    }
}
