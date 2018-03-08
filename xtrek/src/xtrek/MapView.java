/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 *
 * @author Alex Vale
 * @version Sprint 2
 */
public class MapView extends ModeView {;
    private java.util.Timer timer;
    private static JLabel label = new JLabel();

    public MapView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
    
    @Override
    public void makeVisible() {
        panel.setVisible(true);
        //MapModel.updateMap();
    }

    @Override
    public void onHide() {
        panel.setVisible(false);
        timer.cancel();
    }
    void setIcon(ImageIcon image) {
        label.setIcon(image);
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Map");

        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 0;
        panel.add(label, con);
        
        ImageIcon pic = new ImageIcon("redDot.jpg");
        panel.add(new JLabel(pic));

        panel.validate();
        panel.setVisible(true);

        timer = new java.util.Timer();
    }

    
    
}
