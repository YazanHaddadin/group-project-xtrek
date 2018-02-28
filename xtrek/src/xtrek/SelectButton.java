/**
 * SelectButton class
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import java.util.ArrayList;

class SelectButton {
    private final ArrayList<SelectedListener> listeners = new ArrayList<>();

    public void setSelectedListener(SelectedListener listener) {
        listeners.add(listener);
    }

    public void selectedPressed() {
        /*Get which button has been pressed and call listener.selected()*/
    }
}
