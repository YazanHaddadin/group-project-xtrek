/**
 * Main Menu View Class
 * <p>
 * Provides the functionality of the buttons in the Main Menu class.
 *
 * @author Yazan Haddadin
 * @version Sprint 1
 */
package xtrek;

import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class MainMenuModel extends ModeModel{
    private String display;
    private Class currentClass;
    ModeView view;
    
    private OperatorButton currentButton;
    private int buttonIndex;

    @Override
    void selected(ButtonEvent evt) {
        //when a button is selected the Main Menu view is hidden and the selected view is made visible
        if(currentButton.currentClass == WhereTo.class) {
            Xtrek.updateFrame(Xtrek.WhereTo);
        } else if(currentButton.currentClass == TripComputer.class) {
            Xtrek.updateFrame(Xtrek.tripComputer);
        } else if(currentButton.currentClass == Map.class) {
            Xtrek.updateFrame(Xtrek.MapMode);
        } else if(currentButton.currentClass == TurnByTurn.class) {
            Xtrek.updateFrame(Xtrek.TurnByTurn);
        } else if(currentButton.currentClass == Satellite.class) {
            Xtrek.updateFrame(Xtrek.satellite);
        } else if(currentButton.currentClass == About.class) {
            Xtrek.updateFrame(Xtrek.AboutMode);
        }
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
    
    class OperatorButton extends JButton{
        private final Class currentClass;
        
        OperatorButton(String display, Class currentClass) {
            try {
                Image img = ImageIO.read(getClass().getResource("assets/" + display + ".png"))
                        .getScaledInstance(88, 70, Image.SCALE_SMOOTH);
                setIcon(new ImageIcon(img));
            }   catch (Exception ex) {
                System.out.println(ex);
            }  
            this.currentClass = currentClass;
            setStyle();
        }
        
        private void setStyle() {
            //set the display style of the operator buttons
            setOpaque(true);
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font(Constants.systemFont, Font.BOLD, 14));
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
