package xtrek;

import java.awt.*;
import javax.swing.*;

/**
 * WhereTo View Class
 * <p>
 * Provides the view for the keyboard on the XTrek device, where the user will
 * input their destination.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
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
        
        btnA = controller.addButton("A", WhereTo.buttonType.LETTER_NUMBER);
        btnB = controller.addButton("B", WhereTo.buttonType.LETTER_NUMBER);
        btnC = controller.addButton("C", WhereTo.buttonType.LETTER_NUMBER);
        btnD = controller.addButton("D", WhereTo.buttonType.LETTER_NUMBER);
        btnE = controller.addButton("E", WhereTo.buttonType.LETTER_NUMBER);
        btnF = controller.addButton("F", WhereTo.buttonType.LETTER_NUMBER);
        btnG = controller.addButton("G", WhereTo.buttonType.LETTER_NUMBER);
        btnH = controller.addButton("H", WhereTo.buttonType.LETTER_NUMBER);
        btnI = controller.addButton("I", WhereTo.buttonType.LETTER_NUMBER);
        btnJ = controller.addButton("J", WhereTo.buttonType.LETTER_NUMBER);
        btnK = controller.addButton("K", WhereTo.buttonType.LETTER_NUMBER);
        btnL = controller.addButton("L", WhereTo.buttonType.LETTER_NUMBER);
        btnM = controller.addButton("M", WhereTo.buttonType.LETTER_NUMBER);
        btnN = controller.addButton("N", WhereTo.buttonType.LETTER_NUMBER);
        btnO = controller.addButton("O", WhereTo.buttonType.LETTER_NUMBER);
        btnP = controller.addButton("P", WhereTo.buttonType.LETTER_NUMBER);
        btnQ = controller.addButton("Q", WhereTo.buttonType.LETTER_NUMBER);
        btnR = controller.addButton("R", WhereTo.buttonType.LETTER_NUMBER);
        btnS = controller.addButton("S", WhereTo.buttonType.LETTER_NUMBER);
        btnT = controller.addButton("T", WhereTo.buttonType.LETTER_NUMBER);
        btnU = controller.addButton("U", WhereTo.buttonType.LETTER_NUMBER);
        btnV = controller.addButton("V", WhereTo.buttonType.LETTER_NUMBER);
        btnW = controller.addButton("W", WhereTo.buttonType.LETTER_NUMBER);
        btnX = controller.addButton("X", WhereTo.buttonType.LETTER_NUMBER);
        btnY = controller.addButton("Y", WhereTo.buttonType.LETTER_NUMBER);
        btnZ = controller.addButton("Z", WhereTo.buttonType.LETTER_NUMBER);
        
        btnSpace = controller.addButton("_", WhereTo.buttonType.SPACE);
        btnNextPage = controller.addButton("\u2192", WhereTo.buttonType.NEXT_PAGE);
        
        btn1 = controller.addButton("1", WhereTo.buttonType.LETTER_NUMBER);
        btn2 = controller.addButton("2", WhereTo.buttonType.LETTER_NUMBER);
        btn3 = controller.addButton("3", WhereTo.buttonType.LETTER_NUMBER);
        btn4 = controller.addButton("4", WhereTo.buttonType.LETTER_NUMBER);
        btn5 = controller.addButton("5", WhereTo.buttonType.LETTER_NUMBER);
        btn6 = controller.addButton("6", WhereTo.buttonType.LETTER_NUMBER);
        btn7 = controller.addButton("7", WhereTo.buttonType.LETTER_NUMBER);
        btn8 = controller.addButton("8", WhereTo.buttonType.LETTER_NUMBER);
        btn9 = controller.addButton("9", WhereTo.buttonType.LETTER_NUMBER);
        btn0 = controller.addButton("0", WhereTo.buttonType.LETTER_NUMBER);
        
        btnDel = controller.addButton("DEL", WhereTo.buttonType.DEL);
        btnBackPage = controller.addButton("\u2190", WhereTo.buttonType.BACK_PAGE);
        
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
