/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

//Import necessary packages for the button interface

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;


/**
 *
 * @author caleb
 */
public class WhereTo extends Mode {
    
    // Text field where destination will go.
    final JTextField destination = new JTextField();
    
    //Create each letter button
    final JButton btnA = new KeyboardButton ("A");
    final JButton btnB = new KeyboardButton ("B");
    final JButton btnC = new KeyboardButton ("C");
    final JButton btnD = new KeyboardButton ("D");
    final JButton btnE = new KeyboardButton ("E");
    final JButton btnF = new KeyboardButton ("F");
    final JButton btnG = new KeyboardButton ("G");
    final JButton btnH = new KeyboardButton ("H");
    final JButton btnI = new KeyboardButton ("I");
    final JButton btnJ = new KeyboardButton ("J");
    final JButton btnK = new KeyboardButton ("K");
    final JButton btnL = new KeyboardButton ("L");
    final JButton btnM = new KeyboardButton ("M");
    final JButton btnN = new KeyboardButton ("N");
    final JButton btnO = new KeyboardButton ("O");
    final JButton btnP = new KeyboardButton ("P");
    final JButton btnQ = new KeyboardButton ("Q");
    final JButton btnR = new KeyboardButton ("R");
    final JButton btnS = new KeyboardButton ("S");
    final JButton btnT = new KeyboardButton ("T");
    final JButton btnU = new KeyboardButton ("U");
    final JButton btnV = new KeyboardButton ("V");
    final JButton btnW = new KeyboardButton ("W");
    final JButton btnX = new KeyboardButton ("X");
    final JButton btnY = new KeyboardButton ("Y");
    final JButton btnZ = new KeyboardButton ("Z");
    
    final JButton btnSpace = new KeyboardButton ("_");
    final JButton btnChangePage = new KeyboardButton (">");
    
    public WhereTo(JFrame frame) {
        super(frame);
        displayMode();
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Where To?");
        
        destination.setBounds(35, 10, 280, 80);
        destination.setBackground(Color.WHITE);
        destination.setFont(new Font("Arial", Font.BOLD, 36));
        destination.setHorizontalAlignment(JTextField.CENTER);
        
        btnA.setBackground(Color.WHITE);
        btnA.setBounds(35, 100, 70, 70); panel.add(btnA);
        btnA.setFont(new Font("Arial", Font.BOLD, 36));
        btnA.setBorder(new LineBorder(Color.BLACK));
        
        btnB.setBackground(Color.WHITE);
        btnB.setBounds(105, 100, 70, 70); panel.add(btnB);
        btnB.setFont(new Font("Arial", Font.BOLD, 36));
        btnB.setBorder(new LineBorder(Color.BLACK));
        
        btnC.setBackground(Color.WHITE);
        btnC.setBounds(175, 100, 70, 70); panel.add(btnC);
        btnC.setFont(new Font("Arial", Font.BOLD, 36));
        btnC.setBorder(new LineBorder(Color.BLACK));
        
        btnD.setBackground(Color.WHITE);
        btnD.setBounds(245, 100, 70, 70); panel.add(btnD);
        btnD.setFont(new Font("Arial", Font.BOLD, 36));
        btnD.setBorder(new LineBorder(Color.BLACK));

        btnE.setBackground(Color.WHITE);
        btnE.setBounds(35, 170, 70, 70); panel.add(btnE);
        btnE.setFont(new Font("Arial", Font.BOLD, 36));
        btnE.setBorder(new LineBorder(Color.BLACK));
        
        btnF.setBackground(Color.WHITE);
        btnF.setBounds(105, 170, 70, 70); panel.add(btnF);
        btnF.setFont(new Font("Arial", Font.BOLD, 36));
        btnF.setBorder(new LineBorder(Color.BLACK));
        
        btnG.setBackground(Color.WHITE);
        btnG.setBounds(175, 170, 70, 70); panel.add(btnG);
        btnG.setFont(new Font("Arial", Font.BOLD, 36));
        btnG.setBorder(new LineBorder(Color.BLACK));
        
        btnH.setBackground(Color.WHITE);
        btnH.setBounds(245, 170, 70, 70); panel.add(btnH);
        btnH.setFont(new Font("Arial", Font.BOLD, 36));
        btnH.setBorder(new LineBorder(Color.BLACK));

        btnI.setBackground(Color.WHITE);
        btnI.setBounds(35, 240, 70, 70); panel.add(btnI);
        btnI.setFont(new Font("Arial", Font.BOLD, 36));
        btnI.setBorder(new LineBorder(Color.BLACK));
        
        btnJ.setBackground(Color.WHITE);
        btnJ.setBounds(105, 240, 70, 70); panel.add(btnJ);
        btnJ.setFont(new Font("Arial", Font.BOLD, 36));
        btnJ.setBorder(new LineBorder(Color.BLACK));
        
        btnK.setBackground(Color.WHITE);
        btnK.setBounds(175, 240, 70, 70); panel.add(btnK);
        btnK.setFont(new Font("Arial", Font.BOLD, 36));
        btnK.setBorder(new LineBorder(Color.BLACK));
        
        btnL.setBackground(Color.WHITE);
        btnL.setBounds(245, 240, 70, 70); panel.add(btnL);
        btnL.setFont(new Font("Arial", Font.BOLD, 36));
        btnL.setBorder(new LineBorder(Color.BLACK));

        btnM.setBackground(Color.WHITE);
        btnM.setBounds(35, 310, 70, 70); panel.add(btnM);
        btnM.setFont(new Font("Arial", Font.BOLD, 36));
        btnM.setBorder(new LineBorder(Color.BLACK));
        
        btnN.setBackground(Color.WHITE);
        btnN.setBounds(105, 310, 70, 70); panel.add(btnN);
        btnN.setFont(new Font("Arial", Font.BOLD, 36));
        btnN.setBorder(new LineBorder(Color.BLACK));
        
        btnO.setBackground(Color.WHITE);
        btnO.setBounds(175, 310, 70, 70); panel.add(btnO);
        btnO.setFont(new Font("Arial", Font.BOLD, 36));
        btnO.setBorder(new LineBorder(Color.BLACK));
        
        btnP.setBackground(Color.WHITE);
        btnP.setBounds(245, 310, 70, 70); panel.add(btnP);
        btnP.setFont(new Font("Arial", Font.BOLD, 36));
        btnP.setBorder(new LineBorder(Color.BLACK));

        btnQ.setBackground(Color.WHITE);
        btnQ.setBounds(35, 380, 70, 70); panel.add(btnQ);
        btnQ.setFont(new Font("Arial", Font.BOLD, 36));
        btnQ.setBorder(new LineBorder(Color.BLACK));
        
        btnR.setBackground(Color.WHITE);
        btnR.setBounds(105, 380, 70, 70); panel.add(btnR);
        btnR.setFont(new Font("Arial", Font.BOLD, 36));
        btnR.setBorder(new LineBorder(Color.BLACK));
        
        btnS.setBackground(Color.WHITE);
        btnS.setBounds(175, 380, 70, 70); panel.add(btnS);
        btnS.setFont(new Font("Arial", Font.BOLD, 36));
        btnS.setBorder(new LineBorder(Color.BLACK));
        
        btnT.setBackground(Color.WHITE);
        btnT.setBounds(245, 380, 70, 70); panel.add(btnT);
        btnT.setFont(new Font("Arial", Font.BOLD, 36));
        btnT.setBorder(new LineBorder(Color.BLACK));
 
        btnU.setBackground(Color.WHITE);
        btnU.setBounds(35, 450, 70, 70); panel.add(btnU);
        btnU.setFont(new Font("Arial", Font.BOLD, 36));
        btnU.setBorder(new LineBorder(Color.BLACK));
        
        btnV.setBackground(Color.WHITE);
        btnV.setBounds(105, 450, 70, 70); panel.add(btnV);
        btnV.setFont(new Font("Arial", Font.BOLD, 36));
        btnV.setBorder(new LineBorder(Color.BLACK));
        
        btnW.setBackground(Color.WHITE);
        btnW.setBounds(175, 450, 70, 70); panel.add(btnW);
        btnW.setFont(new Font("Arial", Font.BOLD, 36));
        btnW.setBorder(new LineBorder(Color.BLACK));
        
        btnX.setBackground(Color.WHITE);
        btnX.setBounds(245, 450, 70, 70); panel.add(btnX);
        btnX.setFont(new Font("Arial", Font.BOLD, 36));
        btnX.setBorder(new LineBorder(Color.BLACK));
        
        btnY.setBackground(Color.WHITE);
        btnY.setBounds(35, 520, 70, 70); panel.add(btnY);
        btnY.setFont(new Font("Arial", Font.BOLD, 36));
        btnY.setBorder(new LineBorder(Color.BLACK));
        
        btnZ.setBackground(Color.WHITE);
        btnZ.setBounds(105, 520, 70, 70); panel.add(btnZ);
        btnZ.setFont(new Font("Arial", Font.BOLD, 36));
        btnZ.setBorder(new LineBorder(Color.BLACK));
        
        btnSpace.setBackground(Color.WHITE);
        btnSpace.setBounds(175, 520, 70, 70); panel.add(btnSpace);
        btnSpace.setFont(new Font("Arial", Font.BOLD, 36));
        btnSpace.setBorder(new LineBorder(Color.BLACK));
        
        btnChangePage.setBackground(Color.WHITE);
        btnChangePage.setBounds(245, 520, 70, 70); panel.add(btnChangePage);
        btnChangePage.setFont(new Font("Arial", Font.BOLD, 36));
        btnChangePage.setBorder(new LineBorder(Color.BLACK));
        
        panel.validate();
        panel.setVisible(true);
        frame.add(destination);
    }
    
    class KeyboardButton extends JButton {
        private String letter;
                
        public KeyboardButton(String letter) {
            super(letter);
            this.letter = letter; 
            
            this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                KeyboardButton.this.selected();
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                KeyboardButton.this.focusGained();
            }
               
            @Override
            public void mouseExited(MouseEvent e) {
                KeyboardButton.this.focusLost();
            }
        });
            
            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    KeyboardButton.this.focusGained();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    KeyboardButton.this.focusLost();
                }
            });
        }
        
        public String getDisplayLabel() {
           return this.letter;
        }
         
        private void focusGained() {
           setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }
        
        private void selected() {
            if("_".equals(letter)) {
                destination.setText(destination.getText() + " ");
            }
            else if(">".equals(letter)) {
                // Code for going to the next page...
                System.out.println("Go to next page");
            }
            else {
                destination.setText(destination.getText() + letter);
            }
            
        }
         
         
             
        }

    
    

}
    


