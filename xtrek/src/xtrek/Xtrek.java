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

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;

class Xtrek extends JFrame {

    static boolean isOn = true;
    static MainMenu MainMenu;
    static About AboutMode;
    static Map MapMode;
    static TurnByTurn TurnByTurn;
    static WhereTo WhereTo;
    static ControlLayout ControlPanel;
    /**
     * @param args the command line arguments
     */
    private static Mode currentView;
    private static Container c;
    private static Xtrek xtrek;

    private Xtrek() {
        c = this.getContentPane();
        this.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        this.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        this.setResizable(false);

        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainMenu = new MainMenu(this);
        MainMenu.hide();
        /*AboutMode = new About(this);
        AboutMode.hide();
        MapMode = new Map(this);
        MapMode.hide();*/
        TurnByTurn = new TurnByTurn(this);
        TurnByTurn.hide();
        /*WhereTo = new WhereTo(this);
        WhereTo.hide();*/
        currentView = MainMenu;
        ControlPanel = new ControlLayout(this, currentView);

        this.getContentPane().add(ControlPanel.getPanel());
        this.pack();


        this.validate();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        xtrek = new Xtrek();
    }

    public static void hideCurrentView() {
        currentView.hide();
    }

    public static void showCurrentView() {
        currentView.makeVisible();
    }

    public static void setCurrentView(Mode view) {
        currentView = view;
    }

}
