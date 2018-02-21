/*
 * Control Layout Class
 *
 * Provides the Cotrol Panel which has the control buttons to be shown on all
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
    JPanel controlPanel = new JPanel();

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
                    switch ( control ) {
                        case "PWR"      : if       (Xtrek.isOn == false){

                            Xtrek.showCurrentView();
//                                  whereTo.setVisible(true); tripComputer.setVisible(true); map.setVisible(true);
//                                  speech.setVisible(true);satellite.setVisible(true);about.setVisible(true);
                            menu.setBackground(Color.WHITE);plus.setBackground(Color.WHITE);minus.setBackground(Color.WHITE);
                            select.setBackground(Color.WHITE); Xtrek.isOn = true;

                        } else if(Xtrek.isOn == true){

                            Xtrek.hideCurrentView();
//                                  whereTo.setVisible(false); tripComputer.setVisible(false); map.setVisible(false);
//                                  speech.setVisible(false);satellite.setVisible(false);about.setVisible(false);
                            menu.setBackground(Color.GRAY);plus.setBackground(Color.GRAY);minus.setBackground(Color.GRAY);
                            select.setBackground(Color.GRAY); menu.setEnabled(false);plus.setEnabled(false);
                            minus.setEnabled(false);select.setEnabled(false); Xtrek.isOn = false;
                        }
                            break;
                        case "+"        :  if (Xtrek.isOn == true){System.out.println(control);} // scroll through buttons
                                           else if(Xtrek.isOn == false){plus.setEnabled(false);}
                                           break;
                        case "-"        :  if (Xtrek.isOn == true){System.out.println(control);} // scroll through buttons
                                           else if(Xtrek.isOn == false){plus.setEnabled(false);}
                                            break;
                        case "Select"   :  if (Xtrek.isOn == true){System.out.println(control);} // select button
                                           else if(Xtrek.isOn == false){plus.setEnabled(false);}
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
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 8));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void focusGained() {
            if (Xtrek.isOn == true){
                setBackground(Color.ORANGE);
            }
        }

        private void focusLost() {
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
