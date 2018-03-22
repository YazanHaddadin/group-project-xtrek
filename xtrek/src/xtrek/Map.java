package xtrek;

import javax.swing.*;
import java.awt.*;
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

    private final MapView mapView;
    private final MapModel mapModel;


    Map(JFrame frame) {
        model = new MapModel(this);
        view = new MapView(frame);

        mapModel = (MapModel) model;
        mapView = (MapView) view;
    }

    /**
     * The main method is just for testing purposes, allowing me to test my class independently of others
     * It will not be present in the final product
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setPreferredSize(new Dimension(700, 850));
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new Map(frame);
        ControlLayout controlPanel = new ControlLayout(frame, currentView);

        currentView.displayMode();
        currentView.show();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(controlPanel.getPanel(), con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
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

    private final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    static double calculateDistance(Double lat2, Double lon2, Double lat1, Double lon1) {

        Double dlon = Math.toRadians(lon2 - lon1);
        Double dlat = Math.toRadians(lat2 - lat1);
        Double a = Math.pow(Math.sin(dlat / 2), 2) + (Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2));
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return AVERAGE_RADIUS_OF_EARTH_KM * c;
    }

    @Override
    public void onGPSUpdate(Double latitude, Double longitude,
                            SatelliteModel.Direction latitudeDirection,
                            SatelliteModel.Direction longitudeDirection) {
        mapModel.onGPSUpdate(latitude, longitude, latitudeDirection, longitudeDirection);
    }
}