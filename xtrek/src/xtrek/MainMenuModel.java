/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *
 * @author Yazuz
 */
public class MainMenuModel extends ModeModel{
    private String display;
    private Class currentClass;
    
    private OperatorButton currentButton;
    private int buttonIndex;
    
    public void selected() {
            //when a button is selected the Main Menu view is hidden and the selected view is made visible
//        try {
//            MainMenu.thishide();
//
//            @SuppressWarnings("unchecked")
//            Mode newMode = (Mode) currentClass.getDeclaredConstructor(JFrame.class).newInstance(frame);
//
//            newMode.makeVisible();
//            MainMenuView.frame.add(newMode.getPanel());
//            frame.revalidate();
//            frame.repaint();
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
    }
    
    void plus(ButtonEvent evt) {
        if(buttonIndex < buttons.size()-1) buttonIndex++;
        else buttonIndex = 0;

        currentButton = (OperatorButton)buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void minus(ButtonEvent evt) {
        if(buttonIndex > 0) buttonIndex--;
        else buttonIndex = buttons.size()-1;

        currentButton = (OperatorButton)buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void giveFocus(OperatorButton button) {
        button.giveFocus(buttons);
    }

    JButton addButton(String display, Class currentClass) {
        OperatorButton button = new OperatorButton(display, currentClass);
        buttons.add(button);
        return button;
    }

    @Override
    void selected(ButtonEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    class OperatorButton extends JButton{
        private final Class currentClass;
        
        OperatorButton(String display, Class currentClass) {
            super(display);
            this.currentClass = currentClass;
            setStyle();
        }
        
        private void setStyle() {
            //set the display style of the operator buttons
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 14));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void focusGained() {
            //when the focus is on the current button it changes colour
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            //when the focus is lost it reverts back to the orginal colour
            setBackground(Color.WHITE);
        }
        
        String getDisplay() {
            return display;
        }

        Class getCurrentClass() {
            return currentClass;
        }

        void giveFocus(ArrayList<JButton> buttons) {
            for (JButton randButton : buttons) ((OperatorButton)randButton).focusLost();
            this.focusGained();
        }
    }
}
