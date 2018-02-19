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
    
    private Xtrek() {
        c = this.getContentPane();
        this.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        this.setPreferredSize(new Dimension(600, 720));
        this.setResizable(false);

        this.setLayout(null);
        c.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        currentView = new MainMenu(this);
        currentView.displayMode();
        currentView.makeVisible();
        this.getContentPane().add(currentView.getPanel());
        this.pack();

        this.validate();
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        Xtrek xtrek = new Xtrek();
    }
    
}
