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
    final JButton btnNextPage = new KeyboardButton (">");
    
    final JButton btn1 = new KeyboardButton ("1");
    final JButton btn2 = new KeyboardButton ("2");
    final JButton btn3 = new KeyboardButton ("3");
    final JButton btn4 = new KeyboardButton ("4");
    final JButton btn5 = new KeyboardButton ("5");
    final JButton btn6 = new KeyboardButton ("6");
    final JButton btn7 = new KeyboardButton ("7");
    final JButton btn8 = new KeyboardButton ("8");
    final JButton btn9 = new KeyboardButton ("9");
    final JButton btn0 = new KeyboardButton ("0");
    final JButton btnBackPage = new KeyboardButton ("<");
    final JButton btnDel = new KeyboardButton ("DEL");
    
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
        
        btnNextPage.setBackground(Color.WHITE);
        btnNextPage.setBounds(245, 520, 70, 70); panel.add(btnNextPage);
        btnNextPage.setFont(new Font("Arial", Font.BOLD, 36));
        btnNextPage.setBorder(new LineBorder(Color.BLACK));
        
        
        
        
        
        //Numeric keyboard
        btn1.setBackground(Color.WHITE);
        btn1.setBounds(35, 100, 93, 98); panel.add(btn1);
        btn1.setFont(new Font("Arial", Font.BOLD, 36));
        btn1.setBorder(new LineBorder(Color.BLACK));
        btn1.setVisible(false);
        
        btn2.setBackground(Color.WHITE);
        btn2.setBounds(128, 100, 93, 98); panel.add(btn2);
        btn2.setFont(new Font("Arial", Font.BOLD, 36));
        btn2.setBorder(new LineBorder(Color.BLACK));
        btn2.setVisible(false);
        
        btn3.setBackground(Color.WHITE);
        btn3.setBounds(221, 100, 93, 98); panel.add(btn3);
        btn3.setFont(new Font("Arial", Font.BOLD, 36));
        btn3.setBorder(new LineBorder(Color.BLACK));
        btn3.setVisible(false);
        
        btn4.setBackground(Color.WHITE);
        btn4.setBounds(35, 198, 93, 98); panel.add(btn4);
        btn4.setFont(new Font("Arial", Font.BOLD, 36));
        btn4.setBorder(new LineBorder(Color.BLACK));
        btn4.setVisible(false);
        
        btn5.setBackground(Color.WHITE);
        btn5.setBounds(128, 198, 93, 98); panel.add(btn5);
        btn5.setFont(new Font("Arial", Font.BOLD, 36));
        btn5.setBorder(new LineBorder(Color.BLACK));
        btn5.setVisible(false);
        
        btn6.setBackground(Color.WHITE);
        btn6.setBounds(221, 198, 93, 98); panel.add(btn6);
        btn6.setFont(new Font("Arial", Font.BOLD, 36));
        btn6.setBorder(new LineBorder(Color.BLACK));
        btn6.setVisible(false);
        
        btn7.setBackground(Color.WHITE);
        btn7.setBounds(35, 296, 93, 98); panel.add(btn7);
        btn7.setFont(new Font("Arial", Font.BOLD, 36));
        btn7.setBorder(new LineBorder(Color.BLACK));
        btn7.setVisible(false);
        
        btn8.setBackground(Color.WHITE);
        btn8.setBounds(128, 296, 93, 98); panel.add(btn8);
        btn8.setFont(new Font("Arial", Font.BOLD, 36));
        btn8.setBorder(new LineBorder(Color.BLACK));
        btn8.setVisible(false);
        
        btn9.setBackground(Color.WHITE);
        btn9.setBounds(221, 296, 93, 98); panel.add(btn9);
        btn9.setFont(new Font("Arial", Font.BOLD, 36));
        btn9.setBorder(new LineBorder(Color.BLACK));
        btn9.setVisible(false);
        
        btn0.setBackground(Color.WHITE);
        btn0.setBounds(35, 394, 93, 98); panel.add(btn0);
        btn0.setFont(new Font("Arial", Font.BOLD, 36));
        btn0.setBorder(new LineBorder(Color.BLACK));
        btn0.setVisible(false);
        
        btnBackPage.setBackground(Color.WHITE);
        btnBackPage.setBounds(35, 492, 93, 98); panel.add(btnBackPage);
        btnBackPage.setFont(new Font("Arial", Font.BOLD, 36));
        btnBackPage.setBorder(new LineBorder(Color.BLACK));
        btnBackPage.setVisible(false);
        
        btnDel.setBackground(Color.WHITE);
        btnDel.setBounds(128, 394, 186, 196); panel.add(btnDel);
        btnDel.setFont(new Font("Arial", Font.BOLD, 36));
        btnDel.setBorder(new LineBorder(Color.BLACK));
        btnDel.setVisible(false);
        
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
            
            //Next page button pressed
            else if(">".equals(letter)) {
                //Hide all buttons for the main letter keyboard
                btnA.setVisible(false);
                btnB.setVisible(false);
                btnC.setVisible(false);
                btnD.setVisible(false);
                btnE.setVisible(false);
                btnF.setVisible(false);
                btnG.setVisible(false);
                btnH.setVisible(false);
                btnI.setVisible(false);
                btnJ.setVisible(false);
                btnK.setVisible(false);
                btnL.setVisible(false);
                btnM.setVisible(false);
                btnN.setVisible(false);
                btnO.setVisible(false);
                btnP.setVisible(false);
                btnQ.setVisible(false);
                btnR.setVisible(false);
                btnS.setVisible(false);
                btnT.setVisible(false);
                btnU.setVisible(false);
                btnV.setVisible(false);
                btnW.setVisible(false);
                btnX.setVisible(false);
                btnY.setVisible(false);
                btnZ.setVisible(false);
                btnSpace.setVisible(false);
                btnNextPage.setVisible(false);
                
                //Show all numeric keyboard buttons
                btn1.setVisible(true);
                btn2.setVisible(true);
                btn3.setVisible(true);
                btn4.setVisible(true);
                btn5.setVisible(true);
                btn6.setVisible(true);
                btn7.setVisible(true);
                btn8.setVisible(true);
                btn9.setVisible(true);
                btn0.setVisible(true);
                btnDel.setVisible(true);
                btnBackPage.setVisible(true); 
            }
            
            //Delete button pressed
            else if ("DEL".equals(letter)) {
                //Delete a letter from the text field
                destination.setText(destination.getText().substring(0, destination.getText ().length() - 1));
            }
            
            //Previous page button has been pressed
            else if ("<".equals(letter)) {
                //Show all buttons for the main letter keyboard
                btnA.setVisible(true);
                btnB.setVisible(true);
                btnC.setVisible(true);
                btnD.setVisible(true);
                btnE.setVisible(true);
                btnF.setVisible(true);
                btnG.setVisible(true);
                btnH.setVisible(true);
                btnI.setVisible(true);
                btnJ.setVisible(true);
                btnK.setVisible(true);
                btnL.setVisible(true);
                btnM.setVisible(true);
                btnN.setVisible(true);
                btnO.setVisible(true);
                btnP.setVisible(true);
                btnQ.setVisible(true);
                btnR.setVisible(true);
                btnS.setVisible(true);
                btnT.setVisible(true);
                btnU.setVisible(true);
                btnV.setVisible(true);
                btnW.setVisible(true);
                btnX.setVisible(true);
                btnY.setVisible(true);
                btnZ.setVisible(true);
                btnSpace.setVisible(true);
                btnNextPage.setVisible(true);
                
                //Hide all numeric keyboard buttons
                btn1.setVisible(false);
                btn2.setVisible(false);
                btn3.setVisible(false);
                btn4.setVisible(false);
                btn5.setVisible(false);
                btn6.setVisible(false);
                btn7.setVisible(false);
                btn8.setVisible(false);
                btn9.setVisible(false);
                btn0.setVisible(false);
                btnDel.setVisible(false);
                btnBackPage.setVisible(false);
            }
            
            //An ordinary letter or number button has been pressed
            else {
                destination.setText(destination.getText() + letter);
            }
            
        }
         
         
             
        }

    
    

}
    


