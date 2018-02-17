/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

//Import necessary packages for the button interface

import javax.swing.*;
import java.awt.*;


/**
 *
 * @author caleb
 */
public class WhereTo extends Mode {
    
    // Text field where dest will go.
    final TextField destination = new TextField();
    
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
    
    // These will need to be a different type of button eventually...
    final JButton btnSpace = new LetterButton ("Space");
    final JButton btnChangePage = new LetterButton ("Change Page");
    
    public WhereTo(JFrame frame) {
        super(frame);

        displayMode();
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Trip Computer");
        
        btnA.setBackground(Color.WHITE);
        btnA.setBounds(35, 100, 70, 70); panel.add(btnA);
        btnA.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnB.setBackground(Color.WHITE);
        btnB.setBounds(105, 100, 70, 70); panel.add(btnB);
        btnB.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnC.setBackground(Color.WHITE);
        btnC.setBounds(175, 100, 70, 70); panel.add(btnC);
        btnC.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnD.setBackground(Color.WHITE);
        btnD.setBounds(245, 100, 70, 70); panel.add(btnD);
        btnD.setFont(new Font("Arial", Font.BOLD, 36));

        btnE.setBackground(Color.WHITE);
        btnE.setBounds(35, 170, 70, 70); panel.add(btnE);
        btnE.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnF.setBackground(Color.WHITE);
        btnF.setBounds(105, 170, 70, 70); panel.add(btnF);
        btnF.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnG.setBackground(Color.WHITE);
        btnG.setBounds(175, 170, 70, 70); panel.add(btnG);
        btnG.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnH.setBackground(Color.WHITE);
        btnH.setBounds(245, 170, 70, 70); panel.add(btnH);
        btnH.setFont(new Font("Arial", Font.BOLD, 36));

        btnI.setBackground(Color.WHITE);
        btnI.setBounds(35, 240, 70, 70); panel.add(btnI);
        btnI.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnJ.setBackground(Color.WHITE);
        btnJ.setBounds(105, 240, 70, 70); panel.add(btnJ);
        btnJ.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnK.setBackground(Color.WHITE);
        btnK.setBounds(175, 240, 70, 70); panel.add(btnK);
        btnK.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnL.setBackground(Color.WHITE);
        btnL.setBounds(245, 240, 70, 70); panel.add(btnL);
        btnL.setFont(new Font("Arial", Font.BOLD, 36));

        btnM.setBackground(Color.WHITE);
        btnM.setBounds(35, 310, 70, 70); panel.add(btnM);
        btnM.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnN.setBackground(Color.WHITE);
        btnN.setBounds(105, 310, 70, 70); panel.add(btnN);
        btnN.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnO.setBackground(Color.WHITE);
        btnO.setBounds(175, 310, 70, 70); panel.add(btnO);
        btnO.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnP.setBackground(Color.WHITE);
        btnP.setBounds(245, 310, 70, 70); panel.add(btnP);
        btnP.setFont(new Font("Arial", Font.BOLD, 36));

        btnQ.setBackground(Color.WHITE);
        btnQ.setBounds(35, 380, 70, 70); panel.add(btnQ);
        btnQ.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnR.setBackground(Color.WHITE);
        btnR.setBounds(105, 380, 70, 70); panel.add(btnR);
        btnR.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnS.setBackground(Color.WHITE);
        btnS.setBounds(175, 380, 70, 70); panel.add(btnS);
        btnS.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnT.setBackground(Color.WHITE);
        btnT.setBounds(245, 380, 70, 70); panel.add(btnT);
        btnT.setFont(new Font("Arial", Font.BOLD, 36));
 
        btnU.setBackground(Color.WHITE);
        btnU.setBounds(35, 450, 70, 70); panel.add(btnU);
        btnU.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnV.setBackground(Color.WHITE);
        btnV.setBounds(105, 450, 70, 70); panel.add(btnV);
        btnV.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnW.setBackground(Color.WHITE);
        btnW.setBounds(175, 450, 70, 70); panel.add(btnW);
        btnW.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnX.setBackground(Color.WHITE);
        btnX.setBounds(245, 450, 70, 70); panel.add(btnX);
        btnX.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnY.setBackground(Color.WHITE);
        btnY.setBounds(35, 520, 70, 70); panel.add(btnY);
        btnY.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnZ.setBackground(Color.WHITE);
        btnZ.setBounds(105, 520, 70, 70); panel.add(btnZ);
        btnZ.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnSpace.setBackground(Color.WHITE);
        btnSpace.setBounds(175, 520, 70, 70); panel.add(btnSpace);
        btnSpace.setFont(new Font("Arial", Font.BOLD, 36));
        
        btnChangePage.setBackground(Color.WHITE);
        btnChangePage.setBounds(245, 520, 70, 70); panel.add(btnChangePage);
        btnChangePage.setFont(new Font("Arial", Font.BOLD, 36));
        
        panel.validate();
        panel.setVisible(true);
    }
    
    class LetterButton extends JButton {
        private String letter;
        
        public LetterButton(String letter) {
            super(letter);
            this.letter = letter;
            //setLetterButtonStyle();      
        }
        
         public String getDisplayLabel() {
            return this.letter;
        }
    }
    
    
    private void setLetterButtonStyle() {
        btnA.setBackground(Color.WHITE);
        //btnA.setBorderPainted(false);
        btnA.setFont(new Font("Arial", Font.BOLD, 36));
        //btnA.setHorizontalAlignment(SwingConstants.LEFT);
        }
    
}
