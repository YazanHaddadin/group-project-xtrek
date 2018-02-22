/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author Alex
 */
public class Map extends Mode {

    final static String OUTPUT = "output.png";  /* Output file        */
    final static String LATITUDE = "50.7184";     /* Inputted latitude  */
    final static String LONGITUDE = "-3.5339";     /* Inputted Longitude */
    final static String ZOOM = "5";           /* 0 .. 21           */
    final static String SIZE = "612x612";     /* Size              */
    private static JLabel label;
    private BufferedImage mapImage;
    private Timer timer;

    public Map(JFrame frame) {
        super(frame);
        displayMode();
    }

    @Override
    public void displayMode() {
        frame.setTitle("Main Menu");

        panel.setLayout(null);

        label = new JLabel();

        label.setBounds(0, 0, 570, 710);
        panel.add(label);

        panel.validate();
        panel.setVisible(true);

        timer = new Timer();
    }

    //Downloads a new map every 5 sec and sets the new image
    public void updateMap() {
        timer.schedule(new UpdateMap(), 0, 5000);
        try {
            BufferedImage myPicture = ImageIO.read(new File(OUTPUT));
            label.setIcon(new ImageIcon(myPicture));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void makeVisible() {
        panel.setVisible(true);
        updateMap();
    }

    @Override
    public void hide() {
        panel.setVisible(false);
        timer.cancel();
    }

    //Downloads a static image of the map
    class UpdateMap extends TimerTask {
        @Override
        public void run() {

            System.out.println("Downloaded new image");
            HttpConnection connect = new HttpConnection("https://maps.googleapis.com/maps/api/staticmap"
                    + "?" + "center" + "=" + LATITUDE + "," + LONGITUDE
                    + "&" + "zoom" + "=" + ZOOM
                    + "&" + "size" + "=" + SIZE, new HashMap<>(), "");
            connect.writeData("map.png", connect.getResponse());
        }
    }
}