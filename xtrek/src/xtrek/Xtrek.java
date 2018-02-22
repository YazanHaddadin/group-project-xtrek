/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author sebltm
 */
class Xtrek extends JFrame {

    /**
     * @param args the command line arguments
     */
    private static Mode currentView;
    private static Container c;
    private static Xtrek xtrek;
    static boolean isOn = true;
    static MainMenu MainMenu;
    static About AboutMode;
    static Map MapMode;
    static TurnByTurn TurnByTurn;
    static WhereTo WhereTo;
    
    private Xtrek() {
        c = this.getContentPane();
        this.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        this.setPreferredSize(new Dimension(570, 710));
        this.setResizable(false);

        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        MainMenu   = new MainMenu(this);   MainMenu.hide();
        AboutMode  = new About(this);      AboutMode.hide();
        MapMode    = new Map(this);        MapMode.hide();
        TurnByTurn = new TurnByTurn(this); TurnByTurn.hide();
        WhereTo    = new WhereTo(this);    WhereTo.hide();

        currentView = MainMenu;
        currentView.displayMode();
        currentView.makeVisible();
        this.getContentPane().add(currentView.getPanel());
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
