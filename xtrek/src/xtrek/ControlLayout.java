/**
 * Control Layout Class
 * <p>
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

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ControlLayout {
    final JPanel controlPanel = new JPanel();
    final JButton plus = new ControlButton("+");
    final JButton minus = new ControlButton("-");
    final JButton onOff = new ControlButton("PWR");
    final JButton menu = new ControlButton("Menu");
    final JButton select = new ControlButton("Select");

    Mode currentMode;
    private JFrame frame;
    private ButtonListener listener;

    ControlLayout(JFrame frame, Mode mode) {
        this.currentMode = mode;
        this.frame = frame;
        displayMode();
    }

    public void displayMode() {
        frame.setTitle("Control Layout");

        //seperate panel for the control buttons
        controlPanel.setPreferredSize(new Dimension(Constants.screenWidth + 25, Constants.screenHeight + 25));
        controlPanel.setMaximumSize(new Dimension(Constants.screenWidth + 25, Constants.screenHeight + 25));

        controlPanel.setLayout(new GridBagLayout());
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));

        controlPanel.setBackground(Color.BLACK);

        GridBagConstraints con = new GridBagConstraints();
        JLabel overlay = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("assets/display.png"));
            overlay.setIcon(new ImageIcon(img));
            con.gridx = 4;
            con.gridy = 0;
            con.gridwidth = 8;
            con.gridheight = 17;
            con.fill = GridBagConstraints.BOTH;
            controlPanel.add(overlay, con);
        } catch(IOException e) {
            System.out.println(e);
        }

        //add the control buttons to the new panel created
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 1;
        con.gridheight = 2;
        con.fill = GridBagConstraints.BOTH;
        controlPanel.add(plus, con);

        con.gridy = 1;
        controlPanel.add(minus, con);

        con.gridy = 4;
        con.gridheight = 2;
        controlPanel.add(select, con);

        con.gridy = 0;
        con.gridx = 6;
        con.gridheight = 1;
        controlPanel.add(onOff, con);

        con.gridx = 6;
        con.gridy = 3;
        controlPanel.add(menu, con);

        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 5;
        con.gridheight = 15;
        con.weighty = 1.0;
        con.weightx = 1.0;
        con.fill = GridBagConstraints.BOTH;
        controlPanel.add(currentMode.getPanel(), con);

        currentMode.displayMode();
        currentMode.makeVisible();

        listener = currentMode;

        onOff.setVisible(true);
        menu.setVisible(true);
        plus.setVisible(true);
        minus.setVisible(true);
        select.setVisible(true);

        controlPanel.validate();
        controlPanel.setVisible(true);
    }

    JPanel getPanel() {
        return controlPanel;
    }

    void updateFrame(Mode mode) {
        controlPanel.remove(currentMode.getPanel());

        this.currentMode = mode;

        GridBagConstraints con = new GridBagConstraints();

        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 5;
        con.gridheight = 15;
        con.weighty = 1.0;
        con.weightx = 1.0;
        con.fill = GridBagConstraints.BOTH;
        controlPanel.add(currentMode.getPanel(), con);

        currentMode.displayMode();
        currentMode.makeVisible();

        listener = currentMode;

        controlPanel.validate();
        frame.revalidate();
        frame.repaint();
    }

    class ControlButton extends JButton {
        private String control;

        ControlButton(String control) {
            super(control);
            this.control = control;
            setStyle();

            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    //to add functionality depending on which button has been clicked
                    switch (control) {
                        case "PWR":
                            if (!Xtrek.isOn) {
                                //turns the screen back on making the current view visible
                                Xtrek.showCurrentView();
                                setOPEnabColor();
                                enableOPButton();
                                Xtrek.isOn = true;

                            } else if (Xtrek.isOn) {
                                //turns the screen back off making the current view invisible but leaving the operator buttons visible
                                Xtrek.hideCurrentView();
                                setOPDisColor();
                                disableOPButton();
                                Xtrek.isOn = false; //disables all buttons but the power button
                            }
                            break;
                        case "+":
                            if (Xtrek.isOn) {
                                fireEvent();
                            }
                            break;
                        case "-":
                            if (Xtrek.isOn) {
                                fireEvent();
                            }
                            break;
                        case "Select":
                            if (Xtrek.isOn) {
                                fireEvent();
                            }
                            break;
                        case "Menu":
//                            Xtrek.hideCurrentView();
//                            Xtrek.setCurrentView(Xtrek.MainMenu);
//                            Xtrek.showCurrentView();
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

            setOpaque(true);
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font(Constants.systemFont, Font.BOLD, 25));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        private void focusGained() {
            //when the focus is on the current button it changes colour
            if (Xtrek.isOn) {
                setBackground(Color.ORANGE);
            }
        }

        private void focusLost() {
            //when the focus is lost it reverts back to the orginal colour
            if (Xtrek.isOn) {
                setBackground(Color.WHITE);
            }
        }

        private void fireEvent() {
            ButtonEvent event = new ButtonEvent(this);
            switch (control) {
                case "+":
                    listener.plus(event);
                    break;
                case "-":
                    listener.minus(event);
                    break;
                case "Select":
                    listener.selected(event);
                    break;
            }

        }
        
        private void setOPDisColor(){
            menu.setBackground(Color.GRAY);
            plus.setBackground(Color.GRAY);
            minus.setBackground(Color.GRAY);
            select.setBackground(Color.GRAY);
        }
        
        private void setOPEnabColor(){
            menu.setBackground(Color.WHITE);
            plus.setBackground(Color.WHITE);
            minus.setBackground(Color.WHITE);
            select.setBackground(Color.WHITE);
        }
        
        private void disableOPButton(){
            menu.setEnabled(false);
            plus.setEnabled(false);
            minus.setEnabled(false);
            select.setEnabled(false);
        }
        
        private void enableOPButton(){
            menu.setEnabled(true);
            plus.setEnabled(true);
            minus.setEnabled(true);
            select.setEnabled(true);
        }
    }
}
