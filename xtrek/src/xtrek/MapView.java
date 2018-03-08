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
 * @author Alex Vale
 * @version Sprint 2
 */
public class MapView extends ModeView{
    private Map controller;
    private java.util.Timer timer;
    private static JLabel label;
    
    public MapView(JFrame frame) {
        super(frame);
        panel.setLayout(null);
        displayMode();
    }
    
    void setController(Map controller) {
        this.controller = controller;
    }
    
    @Override
    public void makeVisible() {
        panel.setVisible(true);
        //MapModel.updateMap();
    }

    @Override
    public void hide() {
        panel.setVisible(false);
        timer.cancel();
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Map");

        panel.setLayout(null);

        label = new JLabel();

        label.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
        panel.add(label);
        
        ImageIcon pic = new ImageIcon("redDot.jpg");
        panel.add(new JLabel(pic));

        panel.validate();
        panel.setVisible(true);

        timer = new java.util.Timer();
    }
    
}
