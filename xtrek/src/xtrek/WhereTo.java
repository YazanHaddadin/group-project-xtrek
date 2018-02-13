/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

//Import necessary packages for the button interface
import javax.swing.JFrame;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;


/**
 *
 * @author caleb
 */
public class WhereTo extends JFrame {
    final TextField destination = new TextField(); //Textfield where the destination will go
    
    //Create each letter button
    final LetterButton btnA = new LetterButton ("A");
    final LetterButton btnB = new LetterButton ("B");
    final LetterButton btnC = new LetterButton ("C");
    final LetterButton btnD = new LetterButton ("D");
    final LetterButton btnE = new LetterButton ("E");
    final LetterButton btnF = new LetterButton ("F");
    final LetterButton btnG = new LetterButton ("G");
    final LetterButton btnH = new LetterButton ("H");
    final LetterButton btnI = new LetterButton ("I");
    final LetterButton btnJ = new LetterButton ("J");
    final LetterButton btnK = new LetterButton ("K");
    final LetterButton btnL = new LetterButton ("L");
    final LetterButton btnM = new LetterButton ("M");
    final LetterButton btnN = new LetterButton ("N");
    final LetterButton btnO = new LetterButton ("O");
    final LetterButton btnP = new LetterButton ("P");
    final LetterButton btnQ = new LetterButton ("Q");
    final LetterButton btnR = new LetterButton ("R");
    final LetterButton btnS = new LetterButton ("S");
    final LetterButton btnT = new LetterButton ("T");
    final LetterButton btnU = new LetterButton ("U");
    final LetterButton btnV = new LetterButton ("V");
    final LetterButton btnW = new LetterButton ("W");
    final LetterButton btnX = new LetterButton ("X");
    final LetterButton btnY = new LetterButton ("Y");
    final LetterButton btnZ = new LetterButton ("Z");
    
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
    
    public WhereTo() {
        setTitle("Where To?");     
        
    }
}
