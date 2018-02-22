/**
 * Mode class
 * 
 * An abstract that ensure all our different panels are compatible with the main frame (same size and all)
 * 
 * @author sebltm
 * @version Sprint 1
 *
 * To use : extends Mode
 * Implement displayMode() which needs to include your buttons, text labels, images, etc
 */
package xtrek;

import javax.swing.*;
import java.awt.*;


public abstract class Mode {
    final JFrame frame;
    JPanel panel = new JPanel();

    Mode(JFrame frame) {

        this.frame = frame;
        //Dimensions are in pixels, need to be mm
        panel.setPreferredSize(new Dimension(570, 710));
        panel.setMaximumSize(new Dimension(570, 710));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        panel.setLayout(null);

        panel.setBackground(Color.BLACK);
    }

    public void makeVisible() {
        panel.setVisible(true);
    }

    void hide() {
        panel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }

    public abstract void displayMode();
}
