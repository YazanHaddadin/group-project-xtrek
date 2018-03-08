/**
 * About Class
 * <p>
 * Provides information about the device
 *
 * @author Liam Vinson
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

public class About extends Mode {

    public About(JFrame frame) {
        //super(frame);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new AboutPanel();
        frame.setSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(panel);

        frame.setVisible(true);
    }

    @Override
    public void displayMode() {

        //frame.setTitle("About");

        //panel = new AboutPanel();

        //panel.validate();
        //panel.setVisible(true);
    }

    @Override
    public void selected(ButtonEvent evt) {

    }

    @Override
    public void plus(ButtonEvent evt) {

    }

    @Override
    public void minus(ButtonEvent evt) {

    }
}
