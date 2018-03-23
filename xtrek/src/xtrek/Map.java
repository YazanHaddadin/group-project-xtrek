package xtrek;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Map Controller class
 * <p>
 * Provides communication between the map view and model.
 * Shows a map on the SCREEN of the DEVICE, where the user will be able to see
 * their current location.
 *
 * @author Alex Vale
 * @version Sprint 3
 */
public class Map extends Mode implements OnGPSUpdateListener {

    //radius of earth in km
    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    final MapView mapView;
    private final MapModel mapModel;

    Map(JFrame frame) {
        model = new MapModel(this);
        view = new MapView(frame);

        mapModel = (MapModel) model;
        mapView = (MapView) view;
    }

    static double calculateDistance(Double lat2, Double lon2, Double lat1, Double lon1) {
        //calcuates the distance travelled by taking in the latitude and longitude of one point,
        //and the latitude and longitude of the current point and comparing them.
        Double dlon = Math.toRadians(lon2 - lon1);
        Double dlat = Math.toRadians(lat2 - lat1);
        //Math to make the distance match kilometres
        Double a = Math.pow(Math.sin(dlat / 2), 2) + (Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2));
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return AVERAGE_RADIUS_OF_EARTH_KM * c;
    }

    @Override
    void displayMode() {
        mapView.displayMode();
    }

    @Override
    public void selected(ButtonEvent evt) {
        mapModel.selected(evt);
    }

    @Override
    public void plus(ButtonEvent evt) {
        mapModel.plus(evt);
    }

    @Override
    public void minus(ButtonEvent evt) {
        mapModel.minus(evt);
    }

    @Override
    void hide() {
        mapModel.hide();
    }

    @Override
    void onDisplay() {
        mapModel.onDisplay();
    }

    void setIcon(BufferedImage image) {
        mapView.setIcon(image);
    }

    @Override

    public void onGPSUpdate(Double latitude, Double longitude,
                            SatelliteModel.Direction latitudeDirection,
                            SatelliteModel.Direction longitudeDirection) {
        //makes changes once the GPS updates
        mapModel.onGPSUpdate(latitude, longitude, latitudeDirection, longitudeDirection);
    }
}