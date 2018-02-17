package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Yazan
 */
public class MainMenu extends Mode {
    
//    final JButton onOff        = new OperatorButton ("On/Off", "onoffbutton");
//    final JButton plusMinus    = new OperatorButton("+/-","plusMinusbutton");
//    final JButton select       = new OperatorButton("Select","selectbutton");
//    final JButton menu         = new OperatorButton("Menu","menuButton");
    
    final JButton whereTo      = new OperatorButton("Where To?", WhereTo.class);
//    final JButton tripComputer = new OperatorButton("Trip Computer","tripbutton");
//    final JButton map          = new OperatorButton("Map","mapbutton");
    final JButton speech       = new OperatorButton("Speech", TurnByTurn.class);
//    final JButton satellite    = new OperatorButton("Sattelite","sattellitebutton");
//    final JButton about        = new OperatorButton("About","aboutbutton");
    
    public MainMenu(JFrame frame){
        super(frame);
    }
   
    @Override
    public void displayMode() {
        frame.setTitle("Main Menu");
        panel.setBackground(Color.BLACK);
        
//        onOff.setBounds(195,25,35,35); add(onOff);
        
        whereTo.setBounds(45, 100, 80, 80); panel.add(whereTo);
//        tripComputer.setBounds(150, 100, 80, 80);add(tripComputer);
//        map.setBounds(45, 195, 80, 80);add(map);
        speech.setBounds(150, 195, 80, 80); panel.add(speech);
//        satellite.setBounds(45, 290, 80, 80);add(satellite);
//        about.setBounds(150, 290, 80, 80);add(about);
        panel.validate();
        panel.setVisible(false);
    }
    
    private class OperatorButton extends JButton {
        private final Class currentClass;
        private final SelectButton select = new SelectButton();
        
        OperatorButton(String display, Class currentClass){
            super(display);
            //setIcon( new ImageIcon( s + ".png" ) );
            this.currentClass = currentClass;
            setDestinationStyle();
            
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

        private void setDestinationStyle() {
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

        public void selected() {
            try {
                MainMenu.this.hide();

                frame.remove(getPanel());
                Mode newMode = (Mode)currentClass.getDeclaredConstructor(JFrame.class).newInstance(frame);
                newMode.makeVisible();
                frame.add(getPanel());
                frame.revalidate();
                frame.repaint();

            } catch (java.lang.NoSuchMethodException e) {
                e.printStackTrace();
            } catch (java.lang.IllegalAccessException e) {
                e.printStackTrace();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (java.lang.reflect.InvocationTargetException e) {
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
