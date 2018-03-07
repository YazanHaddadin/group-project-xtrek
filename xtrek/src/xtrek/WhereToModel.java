/**
 * WhereTo Model Class
 * <p>
 * Manages the behaviour of the keyboard on the XTrek device, where the user
 * will input their destination.
 *
 * @author Caleb Blackmore
 * @version Sprint 2
 */
package xtrek;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class WhereToModel extends ModeModel {
    private KeyboardButton currentButton;
    private String currentLetter;
    private int buttonIndex = 0;
    
    private WhereTo controller;
    
    JButton addButton(String letter) {
            KeyboardButton button = new KeyboardButton(letter);
            buttons.add(button);
            return button;
        } 
    
    void selected(ButtonEvent evt) {
        //Space button has been pressed
            if ("_".equals(currentLetter)) {
                controller.addToDestination(" ");
            }

            //Next page button pressed
            else if (">".equals(currentLetter)) {
                controller.hideLetterButtons();
                controller.showNumberButtons();
            }

            //Delete button pressed
            else if ("DEL".equals(currentLetter)) {
                controller.deleteFromDestination();
            }

            //Previous page button has been pressed
            else if ("<".equals(currentLetter)) {
                controller.hideNumberButtons();
                controller.showLetterButtons();
            }

            //An ordinary letter or number button has been pressed
            else {
                controller.addToDestination(currentLetter);
            }
    }
    
    public class KeyboardButton extends JButton {
        private final String letter;

        public KeyboardButton(String letter) {
            super(letter);
            this.letter = letter;
            applyKeyboardButtonStyling();
        }
    
        
        public String getDisplayLabel() {
            return letter;
        }

        //Set the background, font and border for each button.
        private void applyKeyboardButtonStyling() {
            setOpaque(true);
            setBackground(Color.WHITE);
            setFont(new Font(Constants.systemFont, Font.BOLD, 36));
            setBorder(new LineBorder(Color.BLACK));
        }

        private void focusGained() {
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }
        
        void giveFocus(ArrayList<JButton> buttons) {
            for (JButton randButton : buttons) ((KeyboardButton)randButton).focusLost();
            this.focusGained();
        }
    }
        
        void plus(ButtonEvent evt) {
            if(buttonIndex < buttons.size()-1) buttonIndex++;
            else buttonIndex = 0;

            currentButton = (KeyboardButton) buttons.get(buttonIndex);
            currentButton.giveFocus(buttons);
        }

        void minus(ButtonEvent evt) {
            if(buttonIndex > 0) buttonIndex--;
            else buttonIndex = buttons.size()-1;

            currentButton = (KeyboardButton) buttons.get(buttonIndex);
            currentButton.giveFocus(buttons);
        }
        
        void giveFocus(KeyboardButton button) {
            button.giveFocus(buttons);
        }
        
           
}
