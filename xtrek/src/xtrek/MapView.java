package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    private static BufferedImage image;
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

    void setIcon(BufferedImage image) {
        MapView.image = image;
        label.setIcon(image);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.rotate( MapModel.getBearing(), image.getWidth()/2, image.getHeight()/2 );
        g2d.drawImage( image, 0, 0, this);
        
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
