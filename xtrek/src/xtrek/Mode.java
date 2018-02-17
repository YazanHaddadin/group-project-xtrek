/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarEntry;

/**
 *
 * @author sebltm
 */
public abstract class Mode {
    JFrame frame;
    static JPanel panel;

    public Mode(JFrame frame) {
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

    public void hide() {
        panel.setVisible(false);
    }

    public static JPanel getPanel() {
        return panel;
    }

    public abstract void displayMode();
}
