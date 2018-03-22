package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * MapView class
 * <p>
 * The View for the Map MVC
 *
 * @author Alex Vale
 * @version Sprint 3
 */
public class MapView extends ModeView {
    private static JLabel label = new JLabel();

    MapView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
    
    @Override
    public void makeVisible() {
        panel.setVisible(true);
    }

    @Override
    public void hideView() {
        panel.setVisible(false);
    }

    void setIcon(ImageIcon image) {
        label.setIcon(image);
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("XTrek");

        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 0;
        panel.add(label, con);
        panel.validate();
        panel.setVisible(true);
    }    
}
