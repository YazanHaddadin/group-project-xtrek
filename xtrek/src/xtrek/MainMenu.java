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

public class MainMenu extends Mode {
    //creating the operator buttons to switch modes
    final JButton whereTo = new OperatorButton("Where To?", WhereTo.class);
    final JButton tripComputer = new OperatorButton("Trip Computer", TripComputer.class);
    final JButton map = new OperatorButton("Map", Map.class);
    final JButton speech = new OperatorButton("Speech", TurnByTurn.class);
    final JButton satallite = new OperatorButton("Satallite", Satallite.class);
    final JButton about = new OperatorButton("About", About.class);

    MainMenu(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }

    @Override
    public void displayMode() {
        //this is the actual display of the Main Menu mode
        frame.setTitle("Main Menu");

        //using GridBagConstraints to adapt to different screen sizes
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(whereTo, c);

        c.gridy = 1;
        panel.add(map, c);

        c.gridy = 2;
        panel.add(satallite, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(tripComputer, c);

        c.gridy = 1;
        panel.add(speech, c);

        c.gridy = 2;
        panel.add(about, c);

        map.setVisible(Xtrek.isOn);
        about.setVisible(Xtrek.isOn);
        speech.setVisible(Xtrek.isOn);
        whereTo.setVisible(Xtrek.isOn);
        satallite.setVisible(Xtrek.isOn);
        tripComputer.setVisible(Xtrek.isOn);

        panel.validate();
        panel.setVisible(true);
    }

    private class OperatorButton extends JButton {
        //the class that creates the operator button and sets its display style
        private final Class currentClass;

        OperatorButton(String display, Class currentClass) {
            super(display);
            //setIcon( new ImageIcon( s + ".png" ) );
            this.currentClass = currentClass;
            setStyle();

            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    OperatorButton.this.selected();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    OperatorButton.this.focusGained();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    OperatorButton.this.focusLost();
                }
            });

            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    OperatorButton.this.focusGained();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    OperatorButton.this.focusLost();
                }
            });
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

        public void selected() {
            //when a button is selected the Main Menu view is hidden and the selected view is made visible
            try {
                MainMenu.this.hide();

                frame.remove(getPanel());

                @SuppressWarnings("unchecked")
                Mode newMode = (Mode) currentClass.getDeclaredConstructor(JFrame.class).newInstance(frame);

                newMode.makeVisible();
                frame.add(newMode.panel);
                frame.revalidate();
                frame.repaint();

            } catch (NoSuchMethodException | java.lang.InstantiationException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
