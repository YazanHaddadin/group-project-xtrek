package xtrek;

import javax.swing.*;

/**
 * Mode abstract class
 * 
 * @author Sebastien Michel
 * @version Sprint 3
 */
public abstract class Mode implements ButtonListener {
    ModeView view;
    ModeModel model;

    void displayMode() {
        onDisplay();
        view.displayMode();
    }

    void show() {
        onDisplay();
        view.makeVisible();
    }

    JPanel getPanel() {
        return view.getPanel();
    }

    void hide() {
        view.hide();
        model.hide();
    }

    void onDisplay() {
        model.onDisplay();
    }

}
