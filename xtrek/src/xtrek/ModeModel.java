package xtrek;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Sebastien Michel
 * @version Sprint 2
 */
abstract class ModeModel {
    ArrayList<JButton> buttons = new ArrayList<>();

    abstract void plus(ButtonEvent evt);

    abstract void minus(ButtonEvent evt);

    abstract void selected(ButtonEvent evt);

    void onDisplay() {
    }

    void hide() {
    }
}
