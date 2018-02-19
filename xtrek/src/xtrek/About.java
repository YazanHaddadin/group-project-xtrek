/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author liamvinson
 */
public class About  {

    public static void main(String[] args) {
        JFrame frame = new JFrame("FrameDemo");
        JPanel panel = new AboutPanel();
        frame.setSize(new Dimension(220, 300));
        frame.setResizable(false);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(panel);
        
        frame.setVisible(true);
    }
}
