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
    private static Float prevLat;
    private static Float prevLong;
    private static String zoom = "10";           /* 0 .. 21           */
    private static String SIZE = Constants.SCREEN_WIDTH + "x" + Constants.SCREEN_HEIGHT;
    private Map controller;

    MapModel(Map controller) {
        this.controller = controller;
    }

    void onGPSUpdate(Float latitude, Float longitude,
                     SatelliteModel.Direction latitudeDirection,
                     SatelliteModel.Direction longitudeDirection) {
        boolean updateMaps = false;

        System.out.println(Map.calculateDistance(MapModel.latitude, MapModel.longitude, latitude, longitude));

        if (Map.calculateDistance(MapModel.latitude, MapModel.longitude, latitude, longitude) > Constants.GPS_TOLERANCE) {
            updateMaps = true;
        }

        if (Math.abs(Math.abs(MapModel.latitude) - latitude) > 0.005) {
            MapModel.latitude = latitude;

            if (latitudeDirection == SatelliteModel.Direction.SOUTH) {
                MapModel.latitude = -latitude;
            }
        }

        if (Math.abs(Math.abs(MapModel.longitude) - longitude) > 0.005) {
            MapModel.longitude = longitude;

            if (longitudeDirection == SatelliteModel.Direction.WEST) {
                MapModel.longitude = -longitude;
            }
        }

        if (updateMaps) {
            System.out.println(MapModel.latitude + ", " + MapModel.longitude);
            //downloadNewMap();
        }
    }

    @Override
    void plus(ButtonEvent evt) {
        //change zoom if + button is pressed
        int zoomInt = Integer.parseInt(zoom);
        if (zoomInt < 21) {
            zoomInt++;
        } else {
            zoomInt = 21;
        }

        zoom = Integer.toString(zoomInt);
        downloadNewMap();
    }

    @Override
    void minus(ButtonEvent evt) {
        //change zoom if - button is pressed
        int zoomInt = Integer.parseInt(zoom);
        if (zoomInt > 0) {
            zoomInt--;
        } else {
            zoomInt = 0;
        }

        zoom = Integer.toString(zoomInt);
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
                + "&zoom=" + zoom
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

