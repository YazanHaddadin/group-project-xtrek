/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Yazuz
 */
public class ControlLayout {
    JPanel controlPanel = new JPanel();
    
    ControlLayout(JButton b){
        controlPanel.setPreferredSize(new Dimension(570, 710));
        controlPanel.setMaximumSize(new Dimension(570, 710));
        
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        controlPanel.setLayout(null);

        controlPanel.setBackground(Color.BLACK);
        controlPanel.add(b);
    }
    
    public void makeVisibleControl() {
        controlPanel.setVisible(true);
    }

    void hideControlPanel() {
        controlPanel.setVisible(false);
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }
}
