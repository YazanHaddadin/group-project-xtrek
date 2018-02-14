/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

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
        setLocationRelativeTo(null);
        
        //Dimensions are in pixels, need to be mm
        setSize(new Dimension(350, 650));
        setResizable(false);
        
        setTitle("Turn-by-Turn");
        setLayout(null);
        
        bOff.setBounds(0,   0, 350, 90); add(bOff);
        bEng.setBounds(0,  90, 350, 90); add(bEng);
        bFre.setBounds(0, 180, 350, 90); add(bFre);
        bGer.setBounds(0, 270, 350, 90); add(bGer);
        bIta.setBounds(0, 360, 350, 90); add(bIta);
        bJap.setBounds(0, 450, 350, 90); add(bJap);
        
        validate();
        setVisible(true);
    }
    
    class LangButton extends JButton implements SelectedListener {
        private String language;
        
        public LangButton(String display, String language) {
            super(display);
            this.language = language;
            
            addMouseListener(new MouseAdapter() {
                public void mouseCliked(MouseEvent me) {
                    LangButton.this.selected();
                }
            });
        }
        
        @Override
        public void selected() {
            TurnByTurn.language = language;
            System.out.println(language);
        }
        
        public String getDisplayLabel() {
            return language;
        }
    }
}
