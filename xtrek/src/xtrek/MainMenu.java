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
    static private String currentOP = "?";
    
    final JButton onOff        = new OperatorButton ("On/Off", "onoffbutton");
    final JButton plusMinus    = new OperatorButton("+/-","plusMinusbutton");
    final JButton select       = new OperatorButton("Select","selectbutton");
    final JButton menu         = new OperatorButton("Menu","menuButton");
    
    final JButton whereTo      = new DestinationButton("Where To?","wheretobutton");
    final JButton tripComputer = new DestinationButton("Trip Computer","wheretobutton");
    final JButton map          = new DestinationButton("Map","wheretobutton");
    final JButton speech       = new DestinationButton("Speech","wheretobutton");
    final JButton satellite    = new DestinationButton("Sattelite","wheretobutton");
    final JButton about        = new DestinationButton("About","wheretobutton");
    
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
        
        onOff.setBounds(195,25,35,35); add(onOff);
        
        whereTo.setBounds(45, 100, 80, 80); add(whereTo);
        tripComputer.setBounds(150, 100, 80, 80);add(tripComputer);
        map.setBounds(45, 195, 80, 80);add(map);
        speech.setBounds(150, 195, 80, 80);add(speech);
        satellite.setBounds(45, 290, 80, 80);add(satellite);
        about.setBounds(150, 290, 80, 80);add(about);
        validate();
        setVisible(true);
    }
    
    private class OperatorButton extends JButton{
        String currentD;
        private SelectButton select = new SelectButton();
        
        OperatorButton(String display, String s){
           super(display);
           //setIcon( new ImageIcon( s + ".png" ) );
           currentD = s;
           setOperatorStyle();
        }
        
        private void setOperatorStyle() {
            setBackground(Color.WHITE);
            setBorder(null);
            setFont(new Font("Arial", Font.BOLD, 12));
        }
    }
    
    private class DestinationButton extends JButton {
        String currentD;
        private SelectButton select = new SelectButton();
        
        DestinationButton(String display, String s){
            super(display);
            //setIcon( new ImageIcon( s + ".png" ) );
            currentD = s;
            setDestinationStyle();
        }

        private void setDestinationStyle() {
            setBackground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 14));
            setHorizontalAlignment(SwingConstants.CENTER);
        }
    }
        
    public static void main(String[] args) {
        Mode myTest = new MainMenu();
    }
}
