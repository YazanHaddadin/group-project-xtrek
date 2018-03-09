/*
 * Map Model class
 * <p>
 * The Model for the Map MVC
 * @author Alex Vale
 * @version Sprint 2
 */
package xtrek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class MapModel extends ModeModel {
    private static String LATITUDE = "50.7184";     /* Inputted latitude - default Exeter - will be changed to current location  */
    private static String LONGITUDE = "-3.5339";     /* Inputted Longitude - default Exeter - will be changed to current location */
    private static String ZOOM = "17";           /* 0 .. 21           */
    private static String SIZE = Constants.screenWidth + "x" + Constants.screenHeight;
    private Timer timer = new Timer();
    private Map controller;

    MapModel(Map controller) {
        this.controller = controller;
    }

    //Downloads a new map every 5 sec and sets the new image
    public void updateMap() {
        timer.cancel();
        timer = new Timer();
        timer.schedule(new UpdateMap(), 0, 5000); //5000 = 5 second delay on update
    }

    private void stopUpdate() {
        timer.cancel();
    }

    @Override
    void plus(ButtonEvent evt) {
        //change zoom if + button is pressed
        int zoomInt = Integer.parseInt(ZOOM);
        if (zoomInt < 21) {
            zoomInt++;
        } else {
            zoomInt = 21;
        }

        ZOOM = Integer.toString(zoomInt);
        updateMap();
    }

    @Override
    void minus(ButtonEvent evt) {
        //change zoom if - button is pressed
        int zoomInt = Integer.parseInt(ZOOM);
        if (zoomInt > 0) {
            zoomInt--;
        } else {
            zoomInt = 0;
        }

        ZOOM = Integer.toString(zoomInt);
        updateMap();
    }

    @Override
    void selected(ButtonEvent evt) {
        //do nothing
    }

    @Override
    void onDisplay() {
        updateMap();
    }

    @Override
    void hide() {
        stopUpdate();
    }

    //Downloads a static image of the map
    class UpdateMap extends TimerTask {
        @Override
        public void run() {
            System.out.println("Downloaded new image");
            HttpConnection connect = new HttpConnection("https://maps.googleapis.com/maps/api/staticmap"
                    + "?" + "center" + "=" + LATITUDE + "," + LONGITUDE
                    + "&" + "zoom" + "=" + ZOOM
                    + "&" + "size" + "=" + SIZE
                    + "&" + "markers" + "=" + LATITUDE + "," + LONGITUDE
                    + "&" + "key" + "=" + Constants.GoogleMapAPI, "POST", new HashMap<>(), "");

            ByteArrayInputStream image = new ByteArrayInputStream(connect.getResponse());

            try {

                BufferedImage myPicture = ImageIO.read(image);
                controller.setIcon(new ImageIcon(myPicture));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
