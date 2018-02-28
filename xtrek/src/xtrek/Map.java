/**
 * Map class
 * <p>
 * Provides a map on the screen of the device, where the user will be able to see
 * their current location.
 *
 * @author Alex Vale
 * @version Sprint 1
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


public class Map extends Mode {

    final static String OUTPUT = "output.png";  /* Output file        */
    final static String LATITUDE = "50.7184";     /* Inputted latitude  */
    final static String LONGITUDE = "-3.5339";     /* Inputted Longitude */
    final static String ZOOM = "17";           /* 0 .. 21           */
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
        frame.setTitle("Map");

        panel.setLayout(null);

        label = new JLabel();

        label.setBounds(0, 0, Constants.screenWidth, Constants.screenHeight);
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
                    + "&" + "size" + "=" + SIZE, "POST", new HashMap<>(), "");
            connect.writeData(OUTPUT, connect.getResponse());
        }
    }
}