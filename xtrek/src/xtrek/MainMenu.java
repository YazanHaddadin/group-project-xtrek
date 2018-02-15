package xtrek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Yazan
 */
public class MainMenu extends JFrame implements Mode {
    static private String currentOP = "?";
    
    final JButton onOff        = new OperatorButton ("onoffbutton");
    final JButton plusMinus    = new OperatorButton("plusMinusbutton");
    final JButton select       = new OperatorButton("selectbutton");
    final JButton menu         = new OperatorButton("menuButton");
    
    final JButton whereTo      = new DestinationButton("wheretobutton");
    final JButton tripComputer = new DestinationButton("wheretobutton");
    final JButton map          = new DestinationButton("wheretobutton");
    final JButton speech       = new DestinationButton("wheretobutton");
    final JButton satellite    = new DestinationButton("wheretobutton");
    final JButton about        = new DestinationButton("wheretobutton");
    
    public MainMenu(){
        displayMode();
    }
   
    public void displayMode() {
        Container c = getContentPane();
        setLocationRelativeTo(null);
       
        setSize(274,440);
        setResizable(false);
        
        setTitle("Main Menu");
        setLayout(null);
        c.setBackground(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        onOff.setBounds(175,45,35,35); add(onOff);
        
        whereTo.setBounds(35, 100, 70, 70); add(whereTo);
        tripComputer.setBounds(105, 100, 70, 70);add(tripComputer);
        validate();
        setVisible(true);
    }
    
    private class OperatorButton extends JButton{
        String currentOP;
        private SelectButton select = new SelectButton();
        
        OperatorButton(String s){
           super(s);
           setOperatorStyle();
        }
        
        private void setOperatorStyle() {
            setBackground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 36));
        }
    }
    
    private class DestinationButton extends JButton {
        String currentDest;
        private SelectButton select = new SelectButton();
        
        DestinationButton(String s){
            super(s);
            setDestinationStyle();
        }

        private void setDestinationStyle() {
            setBackground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 36));
        }
    }
        
    public static void main(String[] args) {
        Mode myTest = new MainMenu();
    }
}
