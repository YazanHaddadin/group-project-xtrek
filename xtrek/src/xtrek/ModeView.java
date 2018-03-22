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
abstract class ModeView extends JPanel {
    final JFrame frame;
    final JPanel panel;

    ModeView(JFrame frame) {
        this.frame = frame;
        //Dimensions are in pixels, need to be mm
        panel = this;
        panel.setPreferredSize(Constants.SCREEN);
        panel.setBounds(50, 150, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        panel.setPreferredSize(Constants.SCREEN);
        panel.setMaximumSize(Constants.SCREEN);
        panel.setBackground(Color.BLACK);
    }

    public void makeVisible() {
        panel.setVisible(true);
    }

    void hideView() {
        panel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }

    public abstract void displayMode();
}
