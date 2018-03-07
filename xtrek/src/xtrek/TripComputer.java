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

}
