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
 */
class Xtrek extends JFrame {

    /**
     * @param args the command line arguments
     */
    private static Mode currentView;
    private static Container c;
    private static Xtrek xtrek;
    
    private Xtrek() {
        c = this.getContentPane();
        this.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        this.setPreferredSize(new Dimension(800, 800));
        this.setResizable(false);

        //this.setLayout(new GridBagLayout());
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        /*GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 1;
        c.weighty = 1.0;
        c.weightx = 1.0;*/

        currentView = new MainMenu(this);
        currentView.displayMode();
        currentView.makeVisible();
        this.getContentPane().add(currentView.getPanel());
        this.pack();

        this.validate();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        xtrek = new Xtrek();
    }
    
}
