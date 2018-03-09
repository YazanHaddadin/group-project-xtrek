/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;

/**
 *
 * @author liamvinson
 */
public class AboutView extends ModeView {
    AboutPanel about = new AboutPanel();

    public AboutView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
    }

    @Override
    public void displayMode() {
        frame.setTitle("About");
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1,1,1,1);
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(about, c);
    }
    
}
