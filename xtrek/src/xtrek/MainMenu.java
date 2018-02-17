package xtrek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Yazan
 */
public class MainMenu extends JFrame implements Mode {
    static private Class currentClass = MainMenu.class;
    
//    final JButton onOff        = new OperatorButton ("On/Off", "onoffbutton");
//    final JButton plusMinus    = new OperatorButton("+/-","plusMinusbutton");
//    final JButton select       = new OperatorButton("Select","selectbutton");
//    final JButton menu         = new OperatorButton("Menu","menuButton");
    
    final JButton whereTo      = new OperatorButton("Where To?",WhereTo.class);
//    final JButton tripComputer = new OperatorButton("Trip Computer","tripbutton");
//    final JButton map          = new OperatorButton("Map","mapbutton");
    final JButton speech       = new OperatorButton("Speech",TurnByTurn.class);
//    final JButton satellite    = new OperatorButton("Sattelite","sattellitebutton");
//    final JButton about        = new OperatorButton("About","aboutbutton");
    
    public MainMenu(){
        displayMode();
    }
   
    @Override
    public void displayMode() {
        Container c = getContentPane();
        setLocationRelativeTo(null);
       
        setSize(274,440);
        setResizable(false);
        
        setTitle("Main Menu");
        setLayout(null);
        c.setBackground(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
//        onOff.setBounds(195,25,35,35); add(onOff);
        
        whereTo.setBounds(45, 100, 80, 80); add(whereTo);
//        tripComputer.setBounds(150, 100, 80, 80);add(tripComputer);
//        map.setBounds(45, 195, 80, 80);add(map);
        speech.setBounds(150, 195, 80, 80);add(speech);
//        satellite.setBounds(45, 290, 80, 80);add(satellite);
//        about.setBounds(150, 290, 80, 80);add(about);
        validate();
        setVisible(true);
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
            MainMenu.currentClass = currentClass;
            System.out.println(currentClass);
        }

        public Class getDisplayLabel() {
            return currentClass;
        }
    }
    public static void main(String[] args) {
        Mode myTest = new MainMenu();
    }
}
