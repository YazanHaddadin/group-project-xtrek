/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 *
 * @author sebltm
 */
public class Xtrek extends JFrame {

    /**
     * @param args the command line arguments
     */
    public void Xtrek() {
        setTitle("XTrek");
        setLayout(null); //GridBagLayout ?
    }
    
    public static void main(String[] args) {
        JFrame xtrek = new Xtrek();
        xtrek.setLocationRelativeTo(null);
        
        //Dimensions are in pixels, need to be mm
        xtrek.setSize(new Dimension(103, 54));
        
        xtrek.setResizable(false);
        xtrek.validate();
        xtrek.setVisible(true);
    }
    
}
