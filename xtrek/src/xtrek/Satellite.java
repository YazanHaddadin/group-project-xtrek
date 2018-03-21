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

    Satellite(JFrame frame) {
        model = new SatelliteModel();
        view = new SatelliteView(frame);

        SModel = (SatelliteModel) model;
        SView = (SatelliteView) view;

        SModel.startThread();
    }
    
    public static void main(String[] argv) {

        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Satellite currentView = new Satellite(frame);
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

    void loopUpdate() {
        //do nothing
    }

    private void updatePosition() {
        //TODO check that values are not empty to determine mad bants
        Float value1 = SModel.getLatitude();
        String direction1 = SModel.getLatitudeDirection();
        Float value2 = SModel.getLongitude();
        String direction2 = SModel.getLongitudeDirection();

        if (value1 == null) {
            SView.setNoSignal();
        } else {
            SView.setPosition(value1, direction1, value2, direction2);
        }
    }

    void setListener(OnGPSUpdateListener listener) {
        SModel.setListener(listener);
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
