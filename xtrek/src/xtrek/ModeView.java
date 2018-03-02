/**
 * ModeView class
 * <p>
 * An abstract that ensure all our different panels are compatible with the main frame (same size and all)
 *
 * @author sebltm
 * @version Sprint 1
 * <p>
 * To use : extends ModeView
 * Implement displayMode() which needs to include your buttons, text labels, images, etc
 */
package xtrek;

import javax.swing.*;
import java.awt.*;


public abstract class ModeView {
    final JFrame frame;
    JPanel panel = new JPanel();

    ModeView(JFrame frame) {
        this.frame = frame;
        //Dimensions are in pixels, need to be mm
        panel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        panel.setMaximumSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        panel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
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
