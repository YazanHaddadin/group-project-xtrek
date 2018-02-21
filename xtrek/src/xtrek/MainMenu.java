/*
 * Main Menu Class
 *
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
    private static ControlLayout controlView;
    
    final JButton whereTo      = new OperatorButton("Where To?", WhereTo.class);
    final JButton tripComputer = new OperatorButton("Trip Computer",WhereTo.class);
    final JButton map          = new OperatorButton("Map",Map.class);
    final JButton speech       = new OperatorButton("Speech", TurnByTurn.class);
    final JButton satellite    = new OperatorButton("Satellite",WhereTo.class);
    final JButton about        = new OperatorButton("About",About.class);
    
    public MainMenu(JFrame frame){
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
   
    @Override
    public void displayMode() {
        
        frame.setTitle("Main Menu");
        
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(whereTo,c);

        c.gridy = 1;
        panel.add(map,c);

        c.gridy = 2;
        panel.add(satellite,c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(tripComputer,c);

        c.gridy = 1;
        panel.add(speech,c);

        c.gridy = 2;
        panel.add(about,c);

        map.setVisible(Xtrek.isOn);
        about.setVisible(Xtrek.isOn);
        speech.setVisible(Xtrek.isOn);
        whereTo.setVisible(Xtrek.isOn);
        satellite.setVisible(Xtrek.isOn);
        tripComputer.setVisible(Xtrek.isOn);

        panel.validate();
        panel.setVisible(true);
    }
    
    private class OperatorButton extends JButton {
        private final Class currentClass;
        
        OperatorButton(String display, Class currentClass){
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
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 14));
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    
        private void focusGained() {
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }

        public void selected() {
            try {
                MainMenu.this.hide();

                frame.remove(getPanel());

                @SuppressWarnings("unchecked")
                Mode newMode = (Mode)currentClass.getDeclaredConstructor(JFrame.class).newInstance(frame);

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
