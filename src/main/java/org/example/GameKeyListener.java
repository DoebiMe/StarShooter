package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GameKeyListener implements KeyListener {
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        System.out.println("You pressed "+e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) {
    }
}
