package xtrek;

import javax.swing.*;

public abstract class Mode {
    ModeView view;
    ModeModel model;

    void displayMode() {
        view.displayMode();
    }

    void makeVisible() {
        view.makeVisible();
    }

    JPanel getPanel() {
        return view.getPanel();
    }

    void hide() {
        view.hide();
    }
}
