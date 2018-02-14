/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author sebltm
 */
public class TurnByTurn extends JFrame implements Mode  {
    static private String language = "en-GB";
    final JButton bOff = new LangButton("Off"     , null);
    final JButton bEng = new LangButton("English" , "en-GB");
    final JButton bFre = new LangButton("French"  , "fr-FR");
    final JButton bGer = new LangButton("German"  , "de-DE");
    final JButton bIta = new LangButton("Italian" , "it-IT");
    final JButton bJap = new LangButton("Japanese", "ja-JP");
    
    public TurnByTurn() {
        displayMode();
    }
    
    @Override
    public void displayMode() {
        Container c = getContentPane();
        setLocationRelativeTo(null);
        
        //Dimensions are in pixels, need to be mm
        setSize(new Dimension(350, 650));
        setResizable(false);
        
        setTitle("Turn-by-Turn");
        setLayout(null);
        c.setBackground(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        bOff.setBounds(20,  10, 310, 90); add(bOff);
        bEng.setBounds(20, 110, 310, 90); add(bEng);
        bFre.setBounds(20, 210, 310, 90); add(bFre);
        bGer.setBounds(20, 310, 310, 90); add(bGer);
        bIta.setBounds(20, 410, 310, 90); add(bIta);
        bJap.setBounds(20, 510, 310, 90); add(bJap);
        
        validate();
        setVisible(true);
    }
    
    class LangButton extends JButton implements SelectedListener {
        private String language;
        private SelectButton select = new SelectButton();
        
        public LangButton(String display, String language) {
            super(display);
            this.language = language;
            setStyle();
            
            /*THE MOUSE LISTENER WILL GET REMOVED WHEN THE BUTTONS ARE PROPERLY
            IMPLEMENTED. THIS IS ONLY FOR TESTING PURPOSE ATM
            */
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LangButton.this.selected();
                }
                
                @Override
                public void mouseEntered(MouseEvent e) {
                    LangButton.this.focusGained();
                }
                
                @Override
                public void mouseExited(MouseEvent e) {
                    LangButton.this.focusLost();
                }
            });
            
            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    LangButton.this.focusGained();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    LangButton.this.focusLost();
                }
            });
            
            select.setSelectedListener(this);
        }
        
        private void focusGained() {
            setBackground(Color.ORANGE);
        }
        
        private void focusLost() {
            setBackground(Color.WHITE);
        }
        
        private void setStyle() {
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 36));
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        
        @Override
        public void selected() {
            TurnByTurn.language = language;
            System.out.println(language); //TEST PURPOSES
        }
        
        public String getDisplayLabel() {
            return language;
        }
    }
    
    public static void main(String[] args) {
        Mode testView = new TurnByTurn();
    }
}
