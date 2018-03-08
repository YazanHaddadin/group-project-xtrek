/**
 * XTrek class
 * <p>
 * Provides a main method for starting the device and running the software.
 * Switches between the different modes in the software.
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

class Xtrek extends JFrame {

    static boolean isOn = true;
    static MainMenu MainMenu;
    static About AboutMode;
    static Map MapMode;
    static TurnByTurn TurnByTurn;
    static WhereTo WhereTo;
    static TripComputer tripComputer;
    static Satellite satellite;
    static ControlLayout controlPanel;
    /**
     * @param args the command line arguments
     */
    private static Xtrek xtrek;
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
        TurnByTurn = new TurnByTurn(this);
        WhereTo = new WhereTo(this);
        satellite = new Satellite(this);

        setCurrentView(MainMenu);
        controlPanel = new ControlLayout(this, MainMenu);

        this.getContentPane().add(controlPanel.getPanel());
        this.pack();

        this.validate();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        xtrek = new Xtrek();
    }

    public static void showCurrentView() {
        currentView.makeVisible();
    }

    public static void hideCurrentView(){
        currentView.onHide();
    }

    public static void setCurrentView(Mode view) {
        currentView = view;
    }

    public static void updateFrame(Mode view) {
        currentView = view;
        currentView.onDisplay();
        controlPanel.updateFrame(view);
    }

    
}
