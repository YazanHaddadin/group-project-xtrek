package xtrek;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Satellite Class
 * <p>
 * Reads out the current coordinates of the DEVICE.
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
public class Satellite extends Mode {
    private SatelliteView SView;
    private SatelliteModel SModel;
    private Thread thread = new Thread(new Updater());

    Satellite(JFrame frame) {
        model = new SatelliteModel();
        view = new SatelliteView(frame);

        SModel = (SatelliteModel) model;
        SView = (SatelliteView) view;
        
        thread.start();
        SModel.startThread();
    }

    void setListener(OnGPSUpdateListener listener) {
        SModel.setListener(listener);
    }
    
    public void updatePosition() {
        Double value1 = SModel.getLatitude();
        String direction1 = SModel.getLatitudeDirection();
        Double value2 = SModel.getLongitude();
        String direction2 = SModel.getLongitudeDirection();

        if (value1 == null) {
            SView.setNoSignal();
        } else {
            SView.setPosition(value1, direction1, value2, direction2);
        }
    }
    
    public class Updater implements Runnable {
        @Override
        public void run() {
            while(true){
                updatePosition();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Satellite.class.getName()).log(Level.SEVERE, null, ex);
                }
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
