package xtrek;

import javax.swing.*;

/**
 * Main Menu Class
 * <p>
 * Using the Main Menu View and Main Menu Model the display of the Xtrek is shown
 * and functionality is implemented.
 *
 * @author Yazan Haddadin
 * @version Sprint 3
 */
public class MainMenu extends Mode {
    private final MainMenuView MMView;
    private final MainMenuModel MMModel;

    MainMenu(Xtrek frame) {
        model = new MainMenuModel(frame);
        view = new MainMenuView(frame);

        MMModel = (MainMenuModel) model;
        MMView = (MainMenuView) view;
    }

    @Override
    void displayMode() {
        MMView.setController(this);
        MMView.displayMode();
        MMModel.initClass();
    }

    JButton addButton(String display, Class currentClass) {
        return MMModel.addButton(display, currentClass);
    }

    void giveFocus(JButton button) {
        MMModel.giveFocus((MainMenuModel.OperatorButton) button);
    }

    @Override
    public void selected(ButtonEvent evt) {
        MMModel.selected(evt);
    }

    @Override
    public void plus(ButtonEvent evt) {
        MMModel.plus(evt);
    }

    @Override
    public void minus(ButtonEvent evt) {
        MMModel.minus(evt);
    }
}
