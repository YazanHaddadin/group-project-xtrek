package xtrek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Map Model class
 * <p>
 * The Model for the Map MVC
 *
 * @author Alex Vale
 * @version Sprint 3
 */
class MapModel extends ModeModel {
    private static Float latitude = 50.7184f;     /* Inputted latitude - default Exeter - will be changed to current location  */
    private static Float longitude = -3.5339f;     /* Inputted Longitude - default Exeter - will be changed to current location */
    private static String ZOOM = "10";           /* 0 .. 21           */
    private static String SIZE = Constants.SCREEN_WIDTH + "x" + Constants.SCREEN_HEIGHT;
    private Map controller;

    MapModel(Map controller) {
        this.controller = controller;
    }

    //Downloads a new map every 5 sec and sets the new image
    /*void updateMap() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }

        timer = new Timer();
        timer.schedule(mapUpdater, 0, 5000); //5000 = 5 second delay on update
    }*/

    void onGPSUpdate(Float latitude, Float longitude, String latitudeDirection, String longitudeDirection) {
        if (MapModel.latitude < (latitude - 0.005) || MapModel.latitude > (latitude + 0.005)) {
            MapModel.latitude = latitude;

            if (latitudeDirection.equals("S")) {
                MapModel.latitude = -latitude;
            }
        }

        if (MapModel.longitude < (longitude - 0.005) || MapModel.longitude > (longitude + 0.005)) {
            MapModel.longitude = longitude;

            if (longitudeDirection.equals("W")) {
                MapModel.longitude = -longitude;
            }
        }

        System.out.println(MapModel.latitude + ", " + MapModel.longitude);
        downloadNewMap();
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
        downloadNewMap();
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
        downloadNewMap();
    }

    @Override
    void selected(ButtonEvent evt) {
        //do nothing
    }

    @Override
    void hide() {
        System.out.println("Stopped timer");
    }

    private void downloadNewMap() {

        System.out.println("Downloaded new image");
        HttpConnection connect = new HttpConnection("https://maps.googleapis.com/maps/api/staticmap"
                + "?center=" + latitude + "," + longitude
                + "&zoom=" + ZOOM
                + "&size=" + SIZE
                + "&markers=" + latitude + "," + longitude
                + "&key=" + Constants.GOOGLE_MAP_API, "POST", new HashMap<>(), "");

        ByteArrayInputStream image = new ByteArrayInputStream(connect.getResponse());

        try {

            BufferedImage myPicture = ImageIO.read(image);
            controller.setIcon(new ImageIcon(myPicture));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

