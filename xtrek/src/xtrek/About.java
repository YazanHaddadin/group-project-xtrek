/**
 * About Class
 *
 * Provides information about the device
 *
 * @author Liam Vinson
 * @version Sprint 1
 */
package xtrek;

import java.awt.Dimension;
import javax.swing.*;

public class About extends Mode  {

    public About(JFrame frame) {
        super(frame);
    }
    
    @Override
    public void displayMode() {

        frame.setTitle("About");
        
        panel = new AboutPanel();
        
        panel.validate();
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new AboutPanel();
        frame.setSize(new Dimension(570, 710));
        frame.setResizable(false);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(panel);
        
        frame.setVisible(true);
    }
}
