/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author liamvinson
 */
public class SatelliteView extends ModeView {
    SatellitePanel sat = new SatellitePanel();
    
    SatelliteView(JFrame frame) {
        super(frame);
    }
    
    public void setPosition(Float value1, String direction1, Float value2, String direction2) {
        sat.setLabel1(value1 + " " + direction1);
        sat.setLabel2(value2 + " " + direction2);
    }
    
    public void setNoSignal() {
        sat.setLabel1("No Signal");
        sat.setLabel2("");
    }

    @Override
    public void displayMode() {
        frame.setTitle("Satellite");
        frame.getContentPane().add(sat);
    }
}
