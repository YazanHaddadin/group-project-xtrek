package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * ModeView class
 * <p>
 * An abstract that ensure all our different panels are compatible with the main frame (same size and all)
 *
 * @author sebltm
 * @version Sprint 3
 * <p>
 * To use : extends ModeView
 * Implement displayMode() which needs to include your buttons, text labels, images, etc
 */
public abstract class ModeView {
    final JFrame frame;
    JPanel panel;

    ModeView(JFrame frame) {
        this.frame = frame;
        //Dimensions are in pixels, need to be mm
        panel = new JPanel();
        panel.setPreferredSize(Constants.screen);
        panel.setBounds(50, 150, Constants.screenWidth, Constants.screenHeight);
        panel.setPreferredSize(Constants.screen);
        panel.setMaximumSize(Constants.screen);
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
