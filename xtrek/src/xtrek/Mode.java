package xtrek;

import javax.swing.*;

/**
 * Mode abstract class
 * <p>
 * Contains methods that are implemented in each controller of the device.
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
abstract class Mode implements ButtonListener {
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
        view.hideView();
        model.hide();
    }

    void onDisplay() {
        model.onDisplay();
    }

}
