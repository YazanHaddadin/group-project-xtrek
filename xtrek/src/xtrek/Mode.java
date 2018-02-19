/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author sebltm
 * An abstract that ensure all our different panels are compatible with the main frame
 *
 * To use : extends Mode
 * Implement displayMode() which needs to include your buttons, text labels, images, etc
 */
public abstract class Mode {
    final JFrame frame;
    static JPanel panel;

    Mode(JFrame frame) {
        this.frame = frame;

        panel = new JPanel();

        //Dimensions are in pixels, need to be mm
        panel.setSize(new Dimension(350, 650));

        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

    }

    public void makeVisible() {
        panel.setVisible(true);
    }

    void hide() {
        panel.setVisible(false);
    }

    public static JPanel getPanel() {
        return panel;
    }

    public abstract void displayMode();
}
