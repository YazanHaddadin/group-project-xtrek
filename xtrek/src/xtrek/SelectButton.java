/**
 * SelectButton class
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SelectButton extends JButton {
    private ArrayList<SelectedListener> listeners = new ArrayList<>();

    SelectButton() {
        super("Select");

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(Xtrek.isOn) {
                    fireEvent();
                } else {
                    //What
                }
            }
        });
    }

    public synchronized void addSelectedListener(SelectedListener listener) {
        listeners.add(listener);
    }

    public synchronized void emptyListeners() {
        listeners.clear();
    }

    private void fireEvent() {
        SelectedEvent event = new SelectedEvent(this);
        for (Object listener : listeners) {
            ((SelectedListener) listener).selected(event);
        }
    }


}
