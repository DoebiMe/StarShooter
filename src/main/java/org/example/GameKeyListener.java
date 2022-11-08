package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GameKeyListener implements KeyListener {
    public void keyTyped(KeyEvent e) {
        System.out.println("You pressed enters");
    }

    public void keyPressed(KeyEvent e) {
        //if(e.getKeyCode() == KeyEvent.VK_ENTER){
        System.out.println("You pressed enter");
        //}
    }

    public void keyReleased(KeyEvent e) {
    }
}
