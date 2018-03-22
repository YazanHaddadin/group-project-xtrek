package xtrek;

import javax.swing.*;

/**
 * TripComputer Controller Class
 * <p>
 * Provides for communication between the view and model of the Trip Computer
 * on the XTrek. Will provide information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class TripComputer extends Mode {
    
    private static TripComputerView tcView;
    private static TripComputerModel tcModel;

    TripComputer(JFrame frame) {
        model = new TripComputerModel();
        view = new TripComputerView(frame);

        tcModel = (TripComputerModel) model;
        tcView = (TripComputerView) view;

        SatelliteModel satModel = new SatelliteModel();

        TripComputerModel.lastLatitude = satModel.getLatitude();
        TripComputerModel.lastLongitude = satModel.getLongitude();
    }
    
    @Override
    void displayMode() {
        tcView.setController(this);
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
    
    public static void updateMovingTime(String mtText) {
        tcView.movingTimeReading.setText(mtText);
    }
    
    public static void updateTripOdometer(String odoText) {
        tcView.odometerReading.setText(odoText);
    }
}
