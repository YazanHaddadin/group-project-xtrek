package xtrek;

import javax.swing.*;
import java.util.EventObject;

public class ButtonEvent extends EventObject {
    JButton button;

    public ButtonEvent(Object source) {
        super(source);
        button = (ControlLayout.ControlButton)source;
    }
}
