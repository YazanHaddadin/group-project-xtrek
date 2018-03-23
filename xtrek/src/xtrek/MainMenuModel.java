package xtrek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main Menu View Class
 * <p>
 * Provides the functionality of the buttons in the Main Menu class.
 *
 * @author Yazan Haddadin
 * @version Sprint 3
 */
class MainMenuModel extends ModeModel {
    private final Xtrek xtrek;
    ModeView view;
    private String display;
    private OperatorButton currentButton;
    private int buttonIndex;

    MainMenuModel(Xtrek xtrek) {
        this.xtrek = xtrek;
    }

    @Override
    void selected(ButtonEvent evt) {
        //when a button is selected the Main Menu view is hidden and the selected view is made visible
        if (currentButton.currentClass == WhereTo.class) {
            xtrek.updateFrame(Xtrek.WhereTo);
        } else if (currentButton.currentClass == TripComputer.class) {
            xtrek.updateFrame(Xtrek.tripComputer);
        } else if (currentButton.currentClass == Map.class) {
            xtrek.updateFrame(Xtrek.MapMode);
        } else if (currentButton.currentClass == TurnByTurn.class) {
            xtrek.updateFrame(Xtrek.turnByTurn);
        } else if (currentButton.currentClass == Satellite.class) {
            xtrek.updateFrame(Xtrek.satellite);
        } else if (currentButton.currentClass == About.class) {
            xtrek.updateFrame(Xtrek.AboutMode);
        } else {
            xtrek.updateFrame(Xtrek.MainMenu);
        }
    }

    void plus(ButtonEvent evt) {
        if (buttonIndex < buttons.size() - 1) buttonIndex++;
        else buttonIndex = 0;

        currentButton = (OperatorButton) buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void minus(ButtonEvent evt) {
        if (buttonIndex > 0) buttonIndex--;
        else buttonIndex = buttons.size() - 1;

        currentButton = (OperatorButton) buttons.get(buttonIndex);
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

    void initClass() {
        buttonIndex = 0;
        currentButton = (OperatorButton) buttons.get(0);
    }

    void emptyArray() {
        buttons = new ArrayList<>();
    }

    class OperatorButton extends JButton {
        private final Class currentClass;

        OperatorButton(String display, Class currentClass) {
            try {
                Image img = ImageIO.read(getClass().getResource("assets/" + display + ".png"))
                        .getScaledInstance(88, 70, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(img));
            } catch (IOException | IllegalArgumentException ex) {
                ex.printStackTrace();
                this.setText(display);
                this.setContentAreaFilled(true);
                this.setBackground(Color.WHITE);
            }
            this.currentClass = currentClass;
            setStyle();
        }

        private void setStyle() {
            //set the display style of the operator buttons
            setOpaque(true);
            setBackground(Color.WHITE);
            setBorderPainted(true);

        }

        private void focusGained() {
            //when the focus is on the current button it changes colour
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            //when the focus is lost it reverts back to the orginal colour
            setBackground(Color.WHITE);
        }

        void giveFocus(ArrayList<JButton> buttons) {
            for (JButton randButton : buttons) ((OperatorButton) randButton).focusLost();
            this.focusGained();
        }
    }
}
