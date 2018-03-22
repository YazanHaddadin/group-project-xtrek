package xtrek;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
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
    private static Double latitude;    /* Inputted latitude - default Exeter - will be changed to current location  */
    private static Double longitude;     /* Inputted Longitude - default Exeter - will be changed to current location */
    private static Double bearing = 0.0;
    private static String zoom = "10";           /* 0 .. 21           */
    private final Map controller;

    MapModel(Map controller) {
        this.controller = controller;
    }

    void onGPSUpdate(Double latitude, Double longitude,
                     SatelliteModel.Direction latitudeDirection,
                     SatelliteModel.Direction longitudeDirection) {

        if (latitudeDirection == SatelliteModel.Direction.SOUTH) {
            latitude = -latitude;
        }

        if (longitudeDirection == SatelliteModel.Direction.WEST) {
            longitude = -longitude;
        }

        if (MapModel.latitude == null) {
            MapModel.latitude = latitude;
        }

        if (MapModel.longitude == null) {
            MapModel.longitude = longitude;
        }

        if (Map.calculateDistance(MapModel.latitude, MapModel.longitude, latitude, longitude) < Constants.GPS_TOLERANCE) {
            MapModel.bearing = getBearing(MapModel.latitude, MapModel.longitude, latitude, longitude);
            System.out.println(MapModel.latitude + ", " + MapModel.longitude);
            MapModel.latitude = latitude;
            MapModel.longitude = longitude;
            downloadNewMap();
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
        controller.mapView.hideView();
    }
    private void downloadNewMap() {
        try {
            HttpConnection connect = new HttpConnection("https://maps.googleapis.com/maps/api/staticmap"
                    + "?center=" + latitude + "," + longitude
                    + "&zoom=" + zoom
                    + "&size=" + Constants.SIZE_MAP
                    + "&markers=icon:" + URLEncoder.encode("https://sebastienmichel.me/red_dot_transparent.png", "UTF-8")
                    + "|" + latitude + "," + longitude
                    + "&key=" + Constants.GOOGLE_MAP_API, "POST", new HashMap<>(), "");

            ByteArrayInputStream image = new ByteArrayInputStream(connect.getResponse());
            BufferedImage myPicture = ImageIO.read(image);
            controller.setIcon(myPicture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Double getBearing(Double lat1, Double lon1, Double lat2, Double lon2) {
        if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) {
            return 0.0;
        } else {
            Double y = Math.sin(lon2 - lon1) * Math.cos(lat2);
            Double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1);
            return Math.atan2(y, x);
        }
    }
    static Double getBearing(){
        return bearing;
    }
}

