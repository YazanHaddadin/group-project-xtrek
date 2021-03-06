package xtrek;

import javax.swing.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    private final SatelliteView SView;
    private final SatelliteModel SModel;
    private final Updater updater = new Updater();
    private Thread thread = new Thread(updater);

    Satellite(JFrame frame) {
        model = new SatelliteModel();
        view = new SatelliteView(frame);

        SModel = (SatelliteModel) model;
        SView = (SatelliteView) view;
    }

    void setListener(OnGPSUpdateListener listener) {
        SModel.setListener(listener);
    }

    private void updatePosition() {
        Double value1 = SModel.getLatitude();
        String direction1 = SModel.getLatitudeDirection();
        Double value2 = SModel.getLongitude();
        String direction2 = SModel.getLongitudeDirection();

        if (value1 == null) {
            SView.setNoSignal();
        } else {
            DecimalFormat df = new DecimalFormat("###.#####");
            df.setRoundingMode(RoundingMode.CEILING);
            String lat = df.format(value1);
            String lon = df.format(value2);
            SView.setPosition(lat, direction1, lon, direction2);
        }
    }

    void turnOff() {

        updater.setIsTerminating(true);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SModel.turnOff();
    }

    void turnOn() {
        SModel.turnOn();

        updater.setIsTerminating(false);
        thread = new Thread(updater);
        thread.start();
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

    class Updater implements Runnable {

        boolean stop = false;

        void setIsTerminating(boolean stop) {
            this.stop = stop;
        }

        @Override
        public void run() {
            while (!stop) {
                updatePosition();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Satellite.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
