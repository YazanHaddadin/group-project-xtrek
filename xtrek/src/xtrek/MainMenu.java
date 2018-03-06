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
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setPreferredSize(new Dimension(700, 850));
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new MainMenu(frame);
        ControlLayout controlPanel = new ControlLayout(frame, currentView);

        currentView.displayMode();
        currentView.makeVisible();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(controlPanel.getPanel(), con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }
}
