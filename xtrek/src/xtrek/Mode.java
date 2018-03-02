package xtrek;

import javax.swing.*;

public abstract class Mode {
    private JFrame frame;
    ModeView view;
    ModeModel model;

    Mode(JFrame frame) {
        this.model = new TurnByTurnModel();
        this.view = new TurnByTurnView(frame);
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

    void hide() {
        view.hide();
    }
}
