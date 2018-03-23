package xtrek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * MapView class
 * <p>
 * The View for the Map MVC
 *
 * @author Alex Vale
 * @version Sprint 3
 */
public class MapView extends ModeView {
    private static final JLabel label = new JLabel();
    private static BufferedImage image;
    private BufferedImage noInternetImage;

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
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        //method to rotate the map given a bearing
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0, 0, Constants.MAP_DIM, Constants.MAP_DIM);

        if (image == null) {
            try {
                image = ImageIO.read(getClass().getResource("assets/map.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        g2d.translate((Constants.SCREEN_WIDTH - Constants.MAP_DIM) / 2,
                (Constants.SCREEN_HEIGHT - Constants.MAP_DIM) / 2);
        g2d.rotate(MapModel.getBearing(), Constants.MAP_DIM / 2, Constants.MAP_DIM / 2);
        g2d.drawImage(image, 0, 0,
                this);
        label.paint(g2d);
    }

    @Override
    public void displayMode() {
        frame.setTitle("Map");

        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridy = 0;

        try {
            noInternetImage = ImageIO.read(getClass().getResource("assets/no_connection.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setIcon(noInternetImage);
        makeVisible();

        panel.add(label, con);
        panel.validate();
        panel.setVisible(true);
    }
}