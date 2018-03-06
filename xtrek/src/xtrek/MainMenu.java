/**
 * Main Menu Class
 * <p>
 * Provides the User Interface of the Main Menu for the Xtrek, where the user is
 * able to choose between which mode to go to using the Control Buttons provided
 * in the ControlLayout class.
 *
 * @author Yazan Haddadin
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
