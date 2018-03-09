/**
 * TripComputer Controller Class
 * <p>
 * Provides for communication between the view and model of the Trip Computer
 * on the XTrek. Will provide information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 2
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

public class TripComputer extends Mode {
    
    private static TripComputerView tcView;
    private static TripComputerModel tcModel;
    
    TripComputer(JFrame frame) {
        model = new TripComputerModel();
        view = new TripComputerView(frame);

        tcModel = (TripComputerModel) model;
        tcView = (TripComputerView) view;
        
        //Start the simulations of the moving time and odometer
        tcModel.increaseMovingTime();
        tcModel.increaseTripOdometer();
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
    
    // Temporary Main Method for Testing
    // REMOVE THIS BEFORE SUBMISSION!
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new TripComputer(frame);
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
        
        //Start the simulations of the moving time and odometer
        tcModel.increaseMovingTime();
        tcModel.increaseTripOdometer();
    }

}
