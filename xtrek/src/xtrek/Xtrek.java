/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

/**
 *
 * @author sebltm
 */
public class Xtrek {

    /**
     * @param args the command line arguments
     */
    public static Mode currentView;
    
    public void Xtrek() {
        /*currentView = new Menu(); or whatever Yazan's class is*/
    }
    
    public static void main(String[] args) {    
        currentView = new MainMenu();
    }
    
}
