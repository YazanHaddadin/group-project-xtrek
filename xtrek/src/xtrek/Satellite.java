/**
 * Satellite Class
 * <p>
 * Reads out the current coordinates of the device.
 *
 * @author Liam Vinson
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Satellite extends Mode {
    private SatelliteView SView;
    private SatelliteModel SModel;

    Satellite(JFrame frame) {
        model = new SatelliteModel();
        view = new SatelliteView(frame);

        SModel = (SatelliteModel) model;
        SView = (SatelliteView) view;
    }
    
    void updatePosition() {
        //TODO check that values are not empty to determine mad bants
        Float value1 = SModel.getLatitude();
        String direction1 = SModel.getLatitudeDirection();
        Float value2 = SModel.getLongitude();
        String direction2 = SModel.getLongitudeDirection();
        
        if (value1==null) {
            SView.setNoSignal();
        } else {
            SView.setPosition(value1, direction1, value2, direction2);
        }
    }
    
    public static void main(String[] argv) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Satellite currentView = new Satellite(frame);
        currentView.displayMode();
        currentView.makeVisible();

        frame.setVisible(true);
        
        currentView.SModel.startThread();
        while(true){
            currentView.updatePosition();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Satellite.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void selected(ButtonEvent evt) {
    }

    @Override
    public void plus(ButtonEvent evt) {
    }

    @Override
    public void minus(ButtonEvent evt) {
    }
    
}