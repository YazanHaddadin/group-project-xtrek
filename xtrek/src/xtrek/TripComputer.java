package xtrek;

import javax.swing.*;

/**
 * TripComputer Controller Class
 * <p>
 * Provides for communication between the view and model of the Trip Computer
 * on the XTrek, showing information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class TripComputer extends Mode implements OnChangeDestinationListener, OnGPSUpdateListener {
    
    private static TripComputerView tcView;
    private static TripComputerModel tcModel;

    TripComputer(JFrame frame) {
        model = new TripComputerModel();
        view = new TripComputerView(frame);

        tcModel = (TripComputerModel) model;
        tcView = (TripComputerView) view;
        
        tcModel.increaseMovingTime();
    }
    
    @Override
    void displayMode() {
        tcView.displayMode();
    }
    
    @Override
    public void selected(ButtonEvent evt) {
        tcModel.selected(evt);
    }

    @Override
    public void plus(ButtonEvent evt) {
        tcModel.plus(evt);
    }

    @Override
    public void minus(ButtonEvent evt) {
        tcModel.minus(evt);
    }

    static void updateMovingTime(String mtText) {
        tcView.movingTimeReading.setText(mtText);
    }

    static void updateTripOdometer(String odoText) {
        tcView.odometerReading.setText(odoText + " KM");
    }

    static void updateSpeed(String speedText) {
        tcView.speedReading.setText(speedText + " KM/H");
    }

    @Override
    public void onGPSUpdate(Double latitude, Double longitude, SatelliteModel.Direction latitudeDirection, SatelliteModel.Direction longitudeDirection) {
        tcModel.onGPSUpdate(latitude, longitude, latitudeDirection, longitudeDirection);
    }

    @Override
    public void onChangeDestination(String destination) {
        tcModel.onChangeDestination(destination);
    }
}
