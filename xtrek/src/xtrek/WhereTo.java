/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

//Import necessary packages for the button interface
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.WindowConstants;


/**
 *
 * @author caleb
 */
public class WhereTo extends JFrame implements Mode {
    final TextField destination = new TextField(); //Textfield where the destination will go
    
    //Create each letter button
    final JButton btnA = new LetterButton ("A");
    final JButton btnB = new LetterButton ("B");
    final JButton btnC = new LetterButton ("C");
    final JButton btnD = new LetterButton ("D");
    final JButton btnE = new LetterButton ("E");
    final JButton btnF = new LetterButton ("F");
    final JButton btnG = new LetterButton ("G");
    final JButton btnH = new LetterButton ("H");
    final JButton btnI = new LetterButton ("I");
    final JButton btnJ = new LetterButton ("J");
    final JButton btnK = new LetterButton ("K");
    final JButton btnL = new LetterButton ("L");
    final JButton btnM = new LetterButton ("M");
    final JButton btnN = new LetterButton ("N");
    final JButton btnO = new LetterButton ("O");
    final JButton btnP = new LetterButton ("P");
    final JButton btnQ = new LetterButton ("Q");
    final JButton btnR = new LetterButton ("R");
    final JButton btnS = new LetterButton ("S");
    final JButton btnT = new LetterButton ("T");
    final JButton btnU = new LetterButton ("U");
    final JButton btnV = new LetterButton ("V");
    final JButton btnW = new LetterButton ("W");
    final JButton btnX = new LetterButton ("X");
    final JButton btnY = new LetterButton ("Y");
    final JButton btnZ = new LetterButton ("Z");
    
    private class LetterButton extends JButton {
    LetterButton( String s ) {
      setIcon( new ImageIcon( "letter" + s + ".png" ) );
      setBorder( null );
      addMouseListener( new MouseAdapter() {
          public void mouseClicked( MouseEvent me ) {
            //Code for each letter being clicked will go here
          }
      });
    }
  }
    
    @Override
    public void displayMode() {
        Container c = getContentPane();
        setLocationRelativeTo(null);
        
        setSize(new Dimension(350, 650));
        setResizable(false);
        
        setTitle("Where To?");
        setLayout(null);
        c.setBackground(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        validate();
        setVisible(true);
    }
    
    public WhereTo() {
        displayMode();  
    }
}
