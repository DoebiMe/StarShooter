package org.example;

import java.awt.event.KeyEvent;

public enum KeyEnum {
    KEY_UP(38),
    KEY_DOWN(40),
    KEY_LEFT(37),
    KEY_RIGHT(39),
    KEY_SPACE(32),
    KEY_ENTER(10),
    KEY_ESC(27),
    KEY_NONE(0);
    private final int value;

    KeyEnum(int value) {
        this.value = value;
    }

    public static KeyEnum getKeyEnumForKeyEvent(KeyEvent keyEvent) {
        for (KeyEnum keyEnum : KeyEnum.values()) {
            if (keyEnum.value == keyEvent.getKeyCode()) {
                return keyEnum;
            }
        }
        return KEY_NONE;
    }

    public int getValue() {
        return value;
    }
}
