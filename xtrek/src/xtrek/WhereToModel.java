package xtrek;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * WhereTo Model Class
 * <p>
 * Manages the behaviour of the keyboard on the XTrek DEVICE, where the user
 * will input their destination.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
class WhereToModel extends ModeModel {
    private KeyboardButton currentButton;
    private int buttonIndex = 0;

    private int startButtonIndex = 0;
    private int endButtonIndex = 27;

    private WhereTo controller;

    //Add each button to the array of buttons, and set the initial button as A.
    JButton addButton(String letter, WhereTo.buttonType type) {
        KeyboardButton button = new KeyboardButton(letter, type);
        buttons.add(button);
        currentButton = (KeyboardButton) buttons.get(0);
        return button;
    }

    //Action based on which keyboard button has been pressed.
    void selected(ButtonEvent evt) {
        //Space button has been pressed
        if (currentButton.getButtonType() == WhereTo.buttonType.SPACE) {
            WhereTo.addToDestination(" ");
        }

        //Next page button pressed
        else if (currentButton.getButtonType() == WhereTo.buttonType.NEXT_PAGE) {
            WhereTo.showNumberButtons();
            startButtonIndex = 28;
            buttonIndex = 28;
            currentButton = (KeyboardButton) buttons.get(buttonIndex);
            currentButton.giveFocus(buttons);
            endButtonIndex = 39;
        }

        //Delete button pressed
        else if (currentButton.getButtonType() == WhereTo.buttonType.DEL) {
            WhereTo.deleteFromDestination();
        }

        //Previous page button has been pressed
        else if (currentButton.getButtonType() == WhereTo.buttonType.BACK_PAGE) {
            WhereTo.showLetterButtons();
            startButtonIndex = 0;
            buttonIndex = 0;
            currentButton = (KeyboardButton) buttons.get(buttonIndex);
            currentButton.giveFocus(buttons);
            endButtonIndex = 27;
        }

        //An ordinary letter or number button has been pressed
        else if (currentButton.getButtonType() == WhereTo.buttonType.LETTER_NUMBER) {
            WhereTo.addToDestination(currentButton.getDisplayLabel());
        }
    }

    void plus(ButtonEvent evt) {
        //Advance selected keyboard button if the + button is pressed
        if (buttonIndex < endButtonIndex) buttonIndex++;
        else buttonIndex = startButtonIndex;

        currentButton = (KeyboardButton) buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void minus(ButtonEvent evt) {
        //Go back 1 keyboard button if the - button is pressed
        if (buttonIndex > startButtonIndex) buttonIndex--;
        else buttonIndex = endButtonIndex;

        currentButton = (KeyboardButton) buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void giveFocus(KeyboardButton button) {
        button.giveFocus(buttons);
    }

    @Override
    void hide() {
        //Call the changed destination listener when exiting where to mode.
        WhereTo.callListener();
        buttons = new ArrayList<>();

        //Reset button indexes for the letters keyboard.
        startButtonIndex = 0;
        endButtonIndex = 27;
    }

    class KeyboardButton extends JButton {
        private final String letter;
        private final WhereTo.buttonType TYPE;

        KeyboardButton(String letter, WhereTo.buttonType type) {
            super(letter);
            this.letter = letter;
            this.TYPE = type;
            applyKeyboardButtonStyling();
        }

        String getDisplayLabel() {
            return letter;
        }

        WhereTo.buttonType getButtonType() {
            return TYPE;
        }

        //Set the background, font and border for each button.
        private void applyKeyboardButtonStyling() {
            setOpaque(true);
            setBackground(Color.WHITE);
            setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, 22));
            setFocusable(false);
            setFocusPainted(false);
            setBorder(new LineBorder(Color.BLACK));
            setVisible(true);
        }

        private void focusGained() {
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }

        void giveFocus(ArrayList<JButton> buttons) {
            for (JButton randButton : buttons) ((KeyboardButton) randButton).focusLost();
            focusGained();
        }
    }


}
