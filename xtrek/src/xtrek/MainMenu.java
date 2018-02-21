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
    final JButton satellite    = new OperatorButton("Sattelite",WhereTo.class);
    final JButton about        = new OperatorButton("About",About.class);
    
    public MainMenu(JFrame frame){
        super(frame);
        panel.setLayout(null);
        displayMode();
    }
   
    @Override
    public void displayMode() {
        
        frame.setTitle("Main Menu");
        panel.setBackground(Color.BLACK);
        
        JPanel display = new JPanel();
        display.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GRAY));
        //display.setBounds(32,10,281,600);
        display.setOpaque(false);
        panel.add(display);
        
        whereTo.setBounds     (45, 150, 120, 120) ; panel.add(whereTo);
        tripComputer.setBounds(180, 150, 120, 120); panel.add(tripComputer);
        map.setBounds         (45, 300, 120, 120) ; panel.add(map);
        speech.setBounds      (180, 300, 120, 120); panel.add(speech);
        satellite.setBounds   (45, 450, 120, 120) ; panel.add(satellite);
        about.setBounds       (180, 450, 120, 120); panel.add(about);
        
        if (Xtrek.isOn==false){
            whereTo.setVisible(false);tripComputer.setVisible(false);map.setVisible(false);
            speech.setVisible(false);satellite.setVisible(false);about.setVisible(false);
        }
        panel.validate();
        panel.setVisible(true);
        
//      GridBagConstraints c = new GridBagConstraints();
//
//      c.fill = GridBagConstraints.BOTH;
//      c.insets = new Insets(5, 5, 5, 5);
//      c.weightx = 1.0;
//      c.weighty = 1.0;
//        
//      c.gridx = 0;
//      c.gridy = 0;
//      panel.add(whereTo,c);
//      
//      c.gridx++;
//      panel.add(tripComputer,c);
//      
//      c.gridy++;
//      c.gridx--;
//      panel.add(map,c);
//        
//      c.gridx++;
//      panel.add(speech,c);
//      
//      c.gridy++;
//      c.gridx--;
//      panel.add(satellite,c);
//        
//      c.gridx++;
//      panel.add(about,c);
//        
//      if (Xtrek.isOn==false){
//          whereTo.setVisible(false);tripComputer.setVisible(false);map.setVisible(false);
//          speech.setVisible(false);satellite.setVisible(false);about.setVisible(false);
//      }
//      panel.validate();
//      panel.setVisible(true);
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
