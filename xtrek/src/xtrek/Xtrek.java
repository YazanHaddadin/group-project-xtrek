package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * XTrek class
 * <p>
 * Provides a main method for starting the DEVICE and running the software.
 * Switches between the different modes in the software.
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
class Xtrek extends JFrame {

    static boolean isOn = true;
    static MainMenu MainMenu;
    static About AboutMode;
    static Map MapMode;
    static TurnByTurn turnByTurn;
    static WhereTo WhereTo;
    static TripComputer tripComputer;
    static Satellite satellite;
    private static ControlLayout controlPanel;
    private static Mode currentView;


    private Xtrek() {
        this.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        this.setResizable(false);

        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainMenu = new MainMenu(this);
        tripComputer = new TripComputer(this);
        AboutMode = new About(this);
        MapMode = new Map(this);
        turnByTurn = new TurnByTurn(this);
        WhereTo = new WhereTo(this);
        satellite = new Satellite(this);
        Directions directions = new Directions();
        directions.setListener(turnByTurn);
        WhereTo.setListener(directions);
        satellite.setListener(MapMode);
        satellite.setListener(directions);
        satellite.setListener(tripComputer);

        currentView = MainMenu;

        updateFrame(currentView);

        this.getContentPane().add(controlPanel.getPanel());
        Xtrek.turnOff();
        this.pack();

        this.validate();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Xtrek xtrek = new Xtrek();
    }

    private static void showCurrentView() {
        currentView.show();
    }


    static void turnOff() {
        Xtrek.hideCurrentView();
        controlPanel.disableOPButton(); //disables all buttons but the power button
        Xtrek.isOn = false;
        Xtrek.satellite.turnOff();
    }

    static void turnOn() {
        //turns the SCREEN back on making the current view visible
        Xtrek.showCurrentView();
        controlPanel.enableOPButton(); //enables all disabled buttons
        Xtrek.isOn = true;
        Xtrek.satellite.turnOn();
    }

    private static void hideCurrentView() {
        currentView.hide();
    }

    void updateFrame(Mode view) {
        currentView.hide();
        currentView = view;
        currentView.show();
        this.getContentPane().removeAll();
        controlPanel = new ControlLayout(this, currentView);
        this.getContentPane().add(controlPanel.getPanel());
        this.pack();
        this.validate();
    }

}
