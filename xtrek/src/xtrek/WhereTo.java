/**
 * WhereTo Class
 *
 * Provides a keyboard for the user to input their destination for the XTrek
 * device. 
 *
 * @author Caleb Blackmore
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class WhereTo extends Mode {
    
    // Text field where destination will go.
    final JTextField destination = new JTextField();
    
    //Create each keyboard button
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
        panel.setLayout(null);
        displayMode();
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Where To?");
        
        //Styling and positioning for destination text field
        destination.setBounds(10, 10, 540, 80);
        destination.setBackground(Color.WHITE);
        destination.setFont(new Font("Arial", Font.BOLD, 36));
        destination.setBorder(null);
        destination.setEditable(false);
        
        //Set co-ordinates for the buttons on the alphabetical keyboard
        btnA.setBounds(10, 100, 135, 80); panel.add(btnA);
        btnB.setBounds(145, 100, 135, 80); panel.add(btnB);
        btnC.setBounds(280, 100, 135, 80); panel.add(btnC);
        btnD.setBounds(415, 100, 135, 80); panel.add(btnD);
        btnE.setBounds(10, 180, 135, 80); panel.add(btnE);
        btnF.setBounds(145, 180, 135, 80); panel.add(btnF);
        btnG.setBounds(280, 180, 135, 80); panel.add(btnG);
        btnH.setBounds(415, 180, 135, 80); panel.add(btnH);
        btnI.setBounds(10, 260, 135, 80); panel.add(btnI);
        btnJ.setBounds(145, 260, 135, 80); panel.add(btnJ);
        btnK.setBounds(280, 260, 135, 80); panel.add(btnK);
        btnL.setBounds(415, 260, 135, 80); panel.add(btnL);
        btnM.setBounds(10, 340, 135, 80); panel.add(btnM);
        btnN.setBounds(145, 340, 135, 80); panel.add(btnN);
        btnO.setBounds(280, 340, 135, 80); panel.add(btnO);
        btnP.setBounds(415, 340, 135, 80); panel.add(btnP);
        btnQ.setBounds(10, 420, 135, 80); panel.add(btnQ);
        btnR.setBounds(145, 420, 135, 80); panel.add(btnR);
        btnS.setBounds(280, 420, 135, 80); panel.add(btnS);
        btnT.setBounds(415, 420, 135, 80); panel.add(btnT);
        btnU.setBounds(10, 500, 135, 80); panel.add(btnU);
        btnV.setBounds(145, 500, 135, 80); panel.add(btnV);
        btnW.setBounds(280, 500, 135, 80); panel.add(btnW);
        btnX.setBounds(415, 500, 135, 80); panel.add(btnX);
        btnY.setBounds(10, 580, 135, 80); panel.add(btnY);
        btnZ.setBounds(145, 580, 135, 80); panel.add(btnZ);
        btnSpace.setBounds(280, 580, 135, 80); panel.add(btnSpace);
        btnNextPage.setBounds(415, 580, 135, 80); panel.add(btnNextPage);
        

        
        //Set co-ordinates for numeric keyboard buttons, and also make them not visible initially, until the numerical keyboard is opened
        btn1.setBounds(10, 100, 180, 112); panel.add(btn1);
        btn1.setVisible(false);
        
        btn2.setBounds(190, 100, 180, 112); panel.add(btn2);
        btn2.setVisible(false);
        
        btn3.setBounds(370, 100, 180, 112); panel.add(btn3);
        btn3.setVisible(false);

        btn4.setBounds(10, 212, 180, 112); panel.add(btn4);
        btn4.setVisible(false);
        
        btn5.setBounds(190, 212, 180, 112); panel.add(btn5);
        btn5.setVisible(false);
        
        btn6.setBounds(370, 212, 180, 112); panel.add(btn6);
        btn6.setVisible(false);
        
        btn7.setBounds(10, 324, 180, 112); panel.add(btn7);
        btn7.setVisible(false);
        
        btn8.setBounds(190, 324, 180, 112); panel.add(btn8);
        btn8.setVisible(false);
        
        btn9.setBounds(370, 324, 180, 112); panel.add(btn9);
        btn9.setVisible(false);
        
        btn0.setBounds(10, 436, 180, 112); panel.add(btn0);
        btn0.setVisible(false);
        
        btnBackPage.setBounds(10, 548, 180, 112); panel.add(btnBackPage);
        btnBackPage.setVisible(false);
        
        btnDel.setBounds(190, 436, 360, 224); panel.add(btnDel);
        btnDel.setVisible(false);
        
        panel.validate();
        panel.setVisible(true);
        panel.add(destination);
    }
    
    class KeyboardButton extends JButton {
        private String letter;
                
        public KeyboardButton(String letter) {
            super(letter);
            this.letter = letter; 
            applyKeyboardButtonStyling();
            
            // Mouse listener is for testing only, won't be in the finished code.
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
        
        //Set the background, font and border for each button.
        private void applyKeyboardButtonStyling() {
            setBackground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 36));
            setBorder(new LineBorder(Color.BLACK));
        }
         
        private void focusGained() {
           setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }
        
        private void selected() {
            //Space button has been pressed
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
                if(destination.getText().length() > 0) {
                    destination.setText(destination.getText().substring(0, destination.getText ().length() - 1));
                }
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