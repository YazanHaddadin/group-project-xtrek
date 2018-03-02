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

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ControlLayout {
    final JPanel controlPanel = new JPanel();
    final JButton plus = new ControlButton("+");
    final JButton minus = new ControlButton("-");
    final JButton onOff = new ControlButton("PWR");
    final JButton menu = new ControlButton("Menu");
    final JButton select = new ControlButton("Select");
    //create the control buttons to control the selection of modes
    Mode currentMode;
    private JFrame frame;
    private ArrayList<ButtonListener> listeners = new ArrayList<>();

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
        listeners.add(currentMode);

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
                                menu.setBackground(Color.WHITE);
                                plus.setBackground(Color.WHITE);
                                minus.setBackground(Color.WHITE);
                                select.setBackground(Color.WHITE);
                                Xtrek.isOn = true;

                            } else if (Xtrek.isOn) {
                                //turns the screen back off making the current view invisible but leaving the operator buttons visible
                                Xtrek.hideCurrentView();
                                menu.setBackground(Color.GRAY);
                                plus.setBackground(Color.GRAY);
                                minus.setBackground(Color.GRAY);
                                select.setBackground(Color.GRAY);
                                menu.setEnabled(false);
                                plus.setEnabled(false);
                                minus.setEnabled(false);
                                select.setEnabled(false);
                                Xtrek.isOn = false; //disables all buttons but the power button
                            }
                            break;
                        case "+":
                            if (Xtrek.isOn) {
                                fireEvent();
                            } else {
                                //TODO implement plus if xtrek is off
                            }
                            break;
                        case "-":
                            if (Xtrek.isOn) {
                                fireEvent();
                            } else {
                                //TODO implement minus if Xtrek is off
                            }
                            break;
                        case "Select":
                            if (Xtrek.isOn) {
                                fireEvent();
                            } else {
                                //TODO implement Select if Xtrek is off
                            }
                            break;
                        case "Menu":
                            Xtrek.hideCurrentView();
                            //Xtrek.setCurrentView(Xtrek.MainMenu);
                            //Xtrek.showCurrentView();
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
            setFont(new Font("Arial", Font.BOLD, 25));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        public synchronized void addSelectedListener(ButtonListener listener) {
            listeners.add(listener);
        }

        public synchronized void emptyListeners() {
            listeners.clear();
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
                    for (Object listener : listeners) {
                        ((ButtonListener) listener).plus(event);
                    }
                    break;
                case "-":
                    for (Object listener : listeners) {
                        ((ButtonListener) listener).minus(event);
                    }
                    break;
                case "Select":
                    for (Object listener : listeners) {
                        ((ButtonListener) listener).selected(event);
                    }
            }

        }
    }
}
