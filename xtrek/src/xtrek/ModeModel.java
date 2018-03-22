package xtrek;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Mode Model abstract class
 * 
 * Describes the methods for each model on the device.
 * 
 * @author Sebastien Michel
 * @version Sprint 3
 */
abstract class ModeModel {
    final ArrayList<JButton> buttons = new ArrayList<>();

    abstract void plus(ButtonEvent evt);

    abstract void minus(ButtonEvent evt);

    abstract void selected(ButtonEvent evt);

    void onDisplay() {
    }

    void hide() {
    }
}
