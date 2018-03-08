package xtrek;

import javax.swing.*;

/**
 * @author Sebastien Michel
 * @version Sprint 2
 */
public abstract class Mode implements ButtonListener {
    ModeView view;
    ModeModel model;

    void displayMode() {
        onDisplay();
        view.displayMode();
    }

    void makeVisible() {
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
