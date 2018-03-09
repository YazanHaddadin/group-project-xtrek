package xtrek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

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
 * @version Sprint 3
 */
public class ControlLayout extends JPanel{
    final JButton plus = new ControlButton("+");
    final JButton minus = new ControlButton("-");
    private final JButton onOff = new ControlButton("PWR");
    private final JButton menu = new ControlButton("Menu");
    private final JButton select = new ControlButton("Select");

    private Mode currentMode;
    private JFrame frame;
    private ButtonListener listener;
    private Xtrek xtrek;

    ControlLayout(Xtrek frame, Mode mode) {
        this.currentMode = mode;
        this.xtrek = frame;
        this.frame = frame;
        displayMode();
    }

    ControlLayout(JFrame frame, Mode mode) {
        this.currentMode = mode;
        this.frame = frame;
        displayMode();
    }

    public void displayMode() {
        frame.setTitle("Control Layout");

        //seperate panel for the control buttons
        this.setLayout(null);
        this.setPreferredSize(Constants.device);

        //add the control buttons to the new panel created
        Dimension buttonSize = new Dimension(15, 40);
        plus.setBounds(0, 80, 15, 40);
        plus.setPreferredSize(buttonSize);
        this.add(plus);

        minus.setBounds(0, 120, 15, 40);
        minus.setPreferredSize(buttonSize);
        this.add(minus);

        select.setBounds(0, 200, 15, 40);
        select.setPreferredSize(buttonSize);
        this.add(select);

        menu.setBounds(310, 80, 15, 40);
        menu.setPreferredSize(buttonSize);
        this.add(menu);

        onOff.setBounds(200, 100, 45, 45);
        onOff.setPreferredSize(new Dimension(45, 45));
        
        //Style power button
        try {
                Image img = ImageIO.read(getClass().getResource("assets/power.png"))
                        .getScaledInstance(45, 45, Image.SCALE_SMOOTH);
                onOff.setIcon(new ImageIcon(img));
            }   catch (Exception ex) {
            ex.printStackTrace();
            }  
        
        this.add(onOff);

        onOff.setVisible(true);
        menu.setVisible(true);
        plus.setVisible(true);
        minus.setVisible(true);
        select.setVisible(true);

        currentMode.displayMode();
        currentMode.show();
        this.add(currentMode.getPanel());

        listener = currentMode;

        JPanel overlayPanel = new JPanel();
        overlayPanel.setPreferredSize(Constants.device);
        overlayPanel.setBounds(0, 0, Constants.deviceWidth, Constants.deviceHeight);
        overlayPanel.setLayout(null);
        overlayPanel.setBackground(Color.BLACK);
        JLabel overlay = new JLabel();
        try {
            Image img = ImageIO.read(getClass().getResource("assets/display.png"));
            overlay.setBounds(0, 0, Constants.deviceWidth, Constants.deviceHeight);
            overlay.setIcon(new ImageIcon(img));
            overlayPanel.add(overlay);
            this.add(overlayPanel);
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.validate();
    }

    JPanel getPanel() {
        return this;
    }

    class ControlButton extends JButton {
        private String control;

        ControlButton(String control) {
            super("");
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
                            xtrek.updateFrame(Xtrek.MainMenu);
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
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFont(new Font(Constants.systemFont, Font.BOLD, 5));
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
