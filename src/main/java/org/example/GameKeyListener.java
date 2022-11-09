package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedHashSet;

class GameKeyListener implements KeyListener {

    private static LinkedHashSet<KeyEnum> currentUsedKeys = new LinkedHashSet<>();
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

    private void bulkPrintCurrentUsedKeys() {
        for (KeyEnum keyEnum : currentUsedKeys) {
            System.out.print(keyEnum + " ");
        }
        System.out.println();
    }

    public static LinkedHashSet<KeyEnum> getCurrentPressedKeys() {
        return currentUsedKeys;
    }
}

