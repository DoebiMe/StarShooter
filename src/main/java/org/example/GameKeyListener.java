package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashSet;

class GameKeyListener implements KeyListener {

    final private static LinkedHashSet<KeyEnum> currentUsedKeys = new LinkedHashSet<>();
    public void keyTyped(KeyEvent e) {

    }
    public void keyPressed(KeyEvent keyEvent) {
        KeyEnum keyEnum = KeyEnum.getKeyEnumForKeyEvent(keyEvent);
        if (!keyEnum.equals(KeyEnum.KEY_NONE)) {
            currentUsedKeys.add(keyEnum);
        }
    }
    public void keyReleased(KeyEvent keyEvent) {
        KeyEnum keyEnum = KeyEnum.getKeyEnumForKeyEvent(keyEvent);
        if (!keyEnum.equals(KeyEnum.KEY_NONE)) {
            currentUsedKeys.remove(keyEnum);
        }
    }


    public static LinkedHashSet<KeyEnum> getCurrentPressedKeys() {
        return currentUsedKeys;
    }
}

