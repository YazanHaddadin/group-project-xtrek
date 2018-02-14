/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.util.ArrayList;

/**
 *
 * @author sebltm
 */
public class SelectButton {
    private ArrayList<SelectedListener> listeners = new ArrayList<SelectedListener>();
    
    public void setSelectedListener(SelectedListener listener) {
        listeners.add(listener);
    }
    
    public void selectedPressed() {
        /*Get which button has been pressed and call listener.selected()*/
    }
}
