/**
 * Main Menu Class
 * <p>
 * Using the Main Menu View and Main Menu Model the display of the Xtrek is shown
 * and functionality is implemented.
 *
 * @author Yazan Haddadin
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends Mode{
    private MainMenuView MMView;
    private MainMenuModel MMModel;

    MainMenu(JFrame frame) {
        model = new MainMenuModel();
        view = new MainMenuView(frame);

        MMModel = (MainMenuModel) model;
        MMView = (MainMenuView) view;
    }
    
    @Override
    void displayMode() {
        MMView.setController(this);
        MMView.displayMode();
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
