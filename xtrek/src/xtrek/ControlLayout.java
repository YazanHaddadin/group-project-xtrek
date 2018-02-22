/**
 * Control Layout Class
 *
 * Provides the Control Panel which has the control buttons to be shown on all
 * the modes, where the + and - buttons are used to navigate between the modes
 * and the select button to choose which mode that is highlighted and the menu
 * button to close the current mode and revert back to the Main Menu. The power
 * button is used to turn off and on the display.
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

public class ControlLayout {
    JPanel controlPanel = new JPanel(); //create a seperate panel other than the main one
    
    //create the control buttons to control the selection of modes
    final JButton onOff        = new ControlButton("PWR");
    final JButton plus         = new ControlButton("+");
    final JButton minus        = new ControlButton("-");
    final JButton select       = new ControlButton("Select");
    final JButton menu         = new ControlButton("M");
    
    ControlLayout(){
        controlPanel.setPreferredSize(new Dimension(570, 710));
        controlPanel.setMaximumSize(new Dimension(570, 710));
        
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        controlPanel.setLayout(null);
        
        //add the control buttons to the new panel created 
        onOff.setBounds (244,25,55,55) ; controlPanel.add(onOff);
        menu.setBounds  (313,150,20,55); controlPanel.add(menu);
        plus.setBounds  (12,150,20,30) ; controlPanel.add(plus);
        minus.setBounds (12,205,20,30) ; controlPanel.add(minus);
        select.setBounds(12,325,20,65) ; controlPanel.add(select);

        controlPanel.setBackground(Color.BLACK);
    }

    private class ControlButton extends JButton {

        ControlButton(String control){
            super(control);
            setStyle();

            addMouseListener( new MouseAdapter() {
                public void mouseClicked( MouseEvent me ) {
                //to add functionality depending on which button has been clicked
                    switch ( control ) {
                        case "PWR"      : if       (Xtrek.isOn == false){
                        //turns the screen back on making the current view visible
                            Xtrek.showCurrentView();
                            menu.setBackground(Color.WHITE);plus.setBackground(Color.WHITE);minus.setBackground(Color.WHITE);
                            select.setBackground(Color.WHITE); Xtrek.isOn = true;

                        } else if(Xtrek.isOn == true){
                        //turns the screen back off making the current view invisible but leaving the operator buttons visible
                            Xtrek.hideCurrentView();
                            menu.setBackground(Color.GRAY);plus.setBackground(Color.GRAY);minus.setBackground(Color.GRAY);
                            select.setBackground(Color.GRAY); menu.setEnabled(false);plus.setEnabled(false);
                            minus.setEnabled(false);select.setEnabled(false); Xtrek.isOn = false; //disables all buttons but the power button
                        }
                            break;
                        case "+"        :  if (Xtrek.isOn == true){System.out.println(control);} // scroll through buttons
                                           else if(Xtrek.isOn == false){plus.setEnabled(false);} // not yet implemented
                                           break;
                        case "-"        :  if (Xtrek.isOn == true){System.out.println(control);} // scroll through buttons
                                           else if(Xtrek.isOn == false){plus.setEnabled(false);} // not yet implemented
                                            break;
                        case "Select"   :  if (Xtrek.isOn == true){System.out.println(control);} // select button
                                           else if(Xtrek.isOn == false){plus.setEnabled(false);} // not yet implemented
                                           break;
                        case "M"        :  Xtrek.hideCurrentView(); Xtrek.setCurrentView(Xtrek.MainMenu); Xtrek.showCurrentView();
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    ControlButton.this.focusGained();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    ControlButton.this.focusLost();
                }
            });
            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    ControlButton.this.focusGained();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    ControlButton.this.focusLost();
                }

            });
        }

        private void setStyle() {
        //set the display style of the control buttons
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 8));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void focusGained() {
        //when the focus is on the current button it changes colour
            if (Xtrek.isOn == true){
                setBackground(Color.ORANGE);
            }
        }

        private void focusLost() {
        //when the focus is lost it reverts back to the orginal colour
            if (Xtrek.isOn == true){
                setBackground(Color.WHITE);
            }
        }
    }
    
    public void makeVisibleControl() {
        controlPanel.setVisible(true);
    }

    void hideControlPanel() {
        controlPanel.setVisible(false);
    }

    public JPanel getControlPanel() {
        return controlPanel;
    }
}
