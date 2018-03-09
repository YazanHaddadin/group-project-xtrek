/**
 * Map Controller class
 * <p>
 * Provides a map on the screen of the device, where the user will be able to see
 * their current location.
 *
 * @author Alex Vale
 * @version Sprint 2
 */
package xtrek;

import javax.swing.*;
import java.awt.*;


public class Map extends Mode {

    private MapView mapView;
    private MapModel mapModel;


    public Map(JFrame frame) {
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

        ((Map) currentView).updateMap();
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

    private void updateMap() {
        mapModel.updateMap();
    }

    void setIcon(ImageIcon image) {
        mapView.setIcon(image);
    }
}