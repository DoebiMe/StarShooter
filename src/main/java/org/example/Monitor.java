package org.example;

import javax.swing.*;
import java.awt.*;

public class Monitor {
    static public void showOnScreen(int screen, JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gd = ge.getScreenDevices();
        if (screen > -1 && screen < gd.length) {
            int width = gd[screen].getDefaultConfiguration().getBounds().width;
            int height = gd[screen].getDefaultConfiguration().getBounds().height;
            frame.setLocation(
                    ((width / 2) - (frame.getSize().width / 2)) + gd[screen].getDefaultConfiguration().getBounds().x,
                    ((height / 2) - (frame.getSize().height / 2)) + gd[screen].getDefaultConfiguration().getBounds().y
            );
            frame.setVisible(true);
        } else {
            System.out.println("Problem in class Monitor : no screens found");
            throw new RuntimeException("No Screens Found");
        }
    }
}
