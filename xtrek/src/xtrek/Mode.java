package xtrek;

import javax.swing.*;

public abstract class Mode {
    private ModeView view;
    private ModeModel controller;

    Mode(ModeView view, ModeModel controller) {
        this.view = view;
        this.controller = controller;
    }

    void displayMode() {
        view.displayMode();
    }

    void makeVisible() {
        view.makeVisible();
    }

    JPanel getPanel() {
        return view.getPanel();
    }
}
