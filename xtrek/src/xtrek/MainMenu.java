package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;

/**
 *
 * @author Yazan
 */
public class MainMenu extends Mode {
    private static Class now;
    
    final JButton onOff        = new ControlButton ("PWR");
    final JButton plus         = new ControlButton("+");
    final JButton minus        = new ControlButton("-");
    final JButton select       = new ControlButton("Select");
    final JButton menu         = new ControlButton("M");
    
    final JButton whereTo      = new OperatorButton("Where To?", WhereTo.class);
    final JButton tripComputer = new OperatorButton("Trip Computer",WhereTo.class);
    final JButton map          = new OperatorButton("Map",WhereTo.class);
    final JButton speech       = new OperatorButton("Speech", TurnByTurn.class);
    final JButton satellite    = new OperatorButton("Sattelite",WhereTo.class);
    final JButton about        = new OperatorButton("About",WhereTo.class);
    
    public MainMenu(JFrame frame){
        super(frame);
    }
   
    @Override
    public void displayMode() {
        frame.setTitle("Main Menu");
        panel.setBackground(Color.BLACK);
        
        onOff.setBounds(244,25,55,55); panel.add(onOff);
        menu.setBounds(310,150,30,65); panel.add(menu);
        plus.setBounds(12,150,20,30); panel.add(plus);
        minus.setBounds(12,205,20,30); panel.add(minus);
        select.setBounds(12,325,20,65); panel.add(select);
        
        whereTo.setBounds(45, 150, 120, 120); panel.add(whereTo);
        tripComputer.setBounds(180, 150, 120, 120);panel.add(tripComputer);
        map.setBounds(45, 300, 120, 120);panel.add(map);
        speech.setBounds(180, 300, 120, 120); panel.add(speech);
        satellite.setBounds(45, 450, 120, 120);panel.add(satellite);
        about.setBounds(180, 450, 120, 120);panel.add(about);
        panel.validate();
        panel.setVisible(false);
    }
    
    private class ControlButton extends JButton {
        
        ControlButton(String control){
            super(control);
            setStyle();
            
          addMouseListener( new MouseAdapter() {
          public void mouseClicked( MouseEvent me ) {
            switch ( control ) {
            case "PWR"      :  MainMenu.this.hide(); frame.remove(getPanel()); break;
            case "+"        :   break;
            case "-"        :   break;
            case "Select"   :   break;
            case "M"     :  MainMenu.this.makeVisible(); frame.add(getPanel()); frame.revalidate(); frame.repaint(); break;
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
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }
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
                now = currentClass;
                MainMenu.this.hide();

                frame.remove(getPanel());

                @SuppressWarnings("unchecked")
                Mode newMode = (Mode)currentClass.getDeclaredConstructor(JFrame.class).newInstance(frame);

                newMode.makeVisible();
                frame.add(getPanel());
                frame.revalidate();
                frame.repaint();

            } catch (NoSuchMethodException | java.lang.InstantiationException | IllegalAccessException | java.lang.reflect.InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setSize(new Dimension(350, 650));
        frame.setResizable(true);

        frame.setLayout(null);
        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new MainMenu(frame);
        currentView.displayMode();
        frame.getContentPane().add(getPanel());
        frame.pack();

        frame.validate();
        frame.setVisible(true);
    }
}
