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
    
    private TripComputerView tcView;
    private TripComputerModel tcModel;
    
    TripComputer(JFrame frame) {
        model = new TripComputerModel();
        view = new TripComputerView(frame);

        tcModel = (TripComputerModel) model;
        tcView = (TripComputerView) view;
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
    
    // Temporary Main Method for Testing
    // REMOVE THIS BEFORE SUBMISSION!
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

        Mode currentView = new TripComputer(frame);
        ControlLayout controlPanel = new ControlLayout(frame, currentView);

        currentView.displayMode();
        currentView.makeVisible();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(controlPanel.getPanel(), con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

}
