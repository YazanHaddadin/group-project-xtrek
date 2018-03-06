/**
 * WhereTo View
 * <p>
 * Provides a view for the keyboard on the Xtrek device, where the user will
 * input their destination.
 *
 * @author Caleb Blackmore
 * @version Sprint 2
 */
package xtrek;

import java.awt.*;
import javax.swing.*;

public class WhereToView extends ModeView{
    private WhereTo controller;
    
    // Text field where destination will go.
    public JTextField destination = new JTextField();

    //Create each keyboard button
    public JButton btnA;
    public JButton btnB;
    public JButton btnC;
    public JButton btnD;
    public JButton btnE;
    public JButton btnF;
    public JButton btnG;
    public JButton btnH;
    public JButton btnI;
    public JButton btnJ;
    public JButton btnK;
    public JButton btnL;
    public JButton btnM;
    public JButton btnN;
    public JButton btnO;
    public JButton btnP;
    public JButton btnQ;
    public JButton btnR;
    public JButton btnS;
    public JButton btnT;
    public JButton btnU;
    public JButton btnV;
    public JButton btnW;
    public JButton btnX;
    public JButton btnY;
    public JButton btnZ;
    
    public JButton btnSpace;
    public JButton btnNextPage;
    
    public JButton btn1;
    public JButton btn2;
    public JButton btn3;
    public JButton btn4;
    public JButton btn5;
    public JButton btn6;
    public JButton btn7;
    public JButton btn8;
    public JButton btn9;
    public JButton btn0;
    
    public JButton btnBackPage;
    public JButton btnDel;
    
    
    WhereToView (JFrame frame) {
        super(frame);
        frame.pack();
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
    
    void setController(WhereTo controller) {
        this.controller = controller;
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Where To?");
        
        btnA = controller.addButton("A");
        btnB = controller.addButton("B");
        btnC = controller.addButton("C");
        btnD = controller.addButton("D");
        btnE = controller.addButton("E");
        btnF = controller.addButton("F");
        btnG = controller.addButton("G");
        btnH = controller.addButton("H");
        btnI = controller.addButton("I");
        btnJ = controller.addButton("J");
        btnK = controller.addButton("K");
        btnL = controller.addButton("L");
        btnM = controller.addButton("M");
        btnN = controller.addButton("N");
        btnO = controller.addButton("O");
        btnP = controller.addButton("P");
        btnQ = controller.addButton("Q");
        btnR = controller.addButton("R");
        btnS = controller.addButton("S");
        btnT = controller.addButton("T");
        btnU = controller.addButton("U");
        btnV = controller.addButton("V");
        btnW = controller.addButton("W");
        btnX = controller.addButton("X");
        btnY = controller.addButton("Y");
        btnZ = controller.addButton("Z");
        
        btnSpace = controller.addButton("_");
        btnNextPage = controller.addButton(">");
        
        btn1 = controller.addButton("1");
        btn2 = controller.addButton("2");
        btn3 = controller.addButton("3");
        btn4 = controller.addButton("4");
        btn5 = controller.addButton("5");
        btn6 = controller.addButton("6");
        btn7 = controller.addButton("7");
        btn8 = controller.addButton("8");
        btn9 = controller.addButton("9");
        btn0 = controller.addButton("0");
        
        btnDel = controller.addButton("DEL");
        btnBackPage = controller.addButton("<");
        
        
        
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        panel.add(destination, c);

        c.gridwidth = 1;
        c.gridy++;
        panel.add(btnA, c);

        c.gridx++;
        panel.add(btnB, c);

        c.gridx++;
        panel.add(btnC, c);

        c.gridx++;
        panel.add(btnD, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnE, c);

        c.gridx++;
        panel.add(btnF, c);

        c.gridx++;
        panel.add(btnG, c);

        c.gridx++;
        panel.add(btnH, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnI, c);

        c.gridx++;
        panel.add(btnJ, c);

        c.gridx++;
        panel.add(btnK, c);

        c.gridx++;
        panel.add(btnL, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnM, c);

        c.gridx++;
        panel.add(btnN, c);

        c.gridx++;
        panel.add(btnO, c);

        c.gridx++;
        panel.add(btnP, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnQ, c);

        c.gridx++;
        panel.add(btnR, c);

        c.gridx++;
        panel.add(btnS, c);

        c.gridx++;
        panel.add(btnT, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnU, c);

        c.gridx++;
        panel.add(btnV, c);

        c.gridx++;
        panel.add(btnW, c);

        c.gridx++;
        panel.add(btnX, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnY, c);

        c.gridx++;
        panel.add(btnZ, c);

        c.gridx++;
        panel.add(btnSpace, c);

        c.gridx++;
        panel.add(btnNextPage, c);


        //Numerical keyboard buttons...
        c.gridx = 0;
        c.gridy = 1;
        panel.add(btn1, c);

        c.gridx++;
        panel.add(btn2, c);

        c.gridx++;
        panel.add(btn3, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btn4, c);

        c.gridx++;
        panel.add(btn5, c);

        c.gridx++;
        panel.add(btn6, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btn7, c);

        c.gridx++;
        panel.add(btn8, c);

        c.gridx++;
        panel.add(btn9, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btn0, c);

        c.gridx = 0;
        c.gridy++;
        panel.add(btnBackPage, c);

        c.gridx++;
        c.gridy--;
        c.gridheight = 2;
        c.gridwidth = 2;
        panel.add(btnDel, c);


        //Styling and positioning for destination text field
        //destination.setBounds(10, 10, 540, 80);
        destination.setBackground(Color.WHITE);
        destination.setFont(new Font("Arial", Font.BOLD, 36));
        destination.setBorder(null);
        destination.setEditable(false);

        //Set co-ordinates for the buttons on the alphabetical keyboard
        /*btnA.setBounds(10, 100, 135, 80); panel.add(btnA);
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
        btnNextPage.setBounds(415, 580, 135, 80); panel.add(btnNextPage); */


        //Set co-ordinates for numeric keyboard buttons, and also make them not visible initially, until the numerical keyboard is opened
        //btn1.setBounds(10, 100, 180, 112); panel.add(btn1);
        btn1.setVisible(false);

        //btn2.setBounds(190, 100, 180, 112); panel.add(btn2);
        btn2.setVisible(false);

        //btn3.setBounds(370, 100, 180, 112); panel.add(btn3);
        btn3.setVisible(false);

        //btn4.setBounds(10, 212, 180, 112); panel.add(btn4);
        btn4.setVisible(false);

        //btn5.setBounds(190, 212, 180, 112); panel.add(btn5);
        btn5.setVisible(false);

        //btn6.setBounds(370, 212, 180, 112); panel.add(btn6);
        btn6.setVisible(false);

        //btn7.setBounds(10, 324, 180, 112); panel.add(btn7);
        btn7.setVisible(false);

        //btn8.setBounds(190, 324, 180, 112); panel.add(btn8);
        btn8.setVisible(false);

        //btn9.setBounds(370, 324, 180, 112); panel.add(btn9);
        btn9.setVisible(false);

        //btn0.setBounds(10, 436, 180, 112); panel.add(btn0);
        btn0.setVisible(false);

        //btnBackPage.setBounds(10, 548, 180, 112); panel.add(btnBackPage);
        btnBackPage.setVisible(false);

        //btnDel.setBounds(190, 436, 360, 224); panel.add(btnDel);
        btnDel.setVisible(false);

        panel.validate();
        panel.setVisible(true);
        panel.add(destination);
    }
}
