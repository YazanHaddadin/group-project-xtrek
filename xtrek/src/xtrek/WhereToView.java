/**
 * WhereTo View Class
 * <p>
 * Provides the view for the keyboard on the XTrek device, where the user will
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
    public static JTextField destination = new JTextField();

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
        
        //Add destination to its own JPanel
        destination.setBackground(Color.WHITE);
        destination.setFont(new Font(Constants.systemFont, Font.BOLD, 22));
        destination.setBorder(null);
        destination.setOpaque(true);
        destination.setEditable(false);
        
        JPanel destinationPanel = new JPanel();
        
        destinationPanel.setLayout(new GridBagLayout());
        GridBagConstraints d = new GridBagConstraints();
        
        d.fill = GridBagConstraints.BOTH;
        d.insets = new Insets(1, 1, 1, 1);
        d.weightx = 1.0;
        d.weighty = 1.0;
        d.gridwidth = 1;
        d.gridx = 0;
        d.gridy = 0;

        destinationPanel.add(destination, d);
        
        destinationPanel.validate();
        destinationPanel.setVisible(true);
        
        
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        panel.add(destinationPanel, c);

        //Add letter buttons to the grid
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
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


        //Add numerical keyboard buttons to the grid
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
        
        //Initial keyboard set up
        controller.showLetterButtons();
        controller.hideNumberButtons();

        panel.validate();
        panel.setVisible(true);
        
        //Place cursor on the default button - A in this case
        frame.getRootPane().setDefaultButton(btnA);
        btnA.requestFocus();
        controller.giveFocus(btnA);
    }
}
