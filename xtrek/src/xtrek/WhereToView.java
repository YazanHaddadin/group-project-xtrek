package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * WhereTo View Class
 * <p>
 * Provides the view for the keyboard on the XTrek DEVICE, where the user will
 * input their destination.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class WhereToView extends ModeView{
    private WhereTo controller;
    
    // Text field where destination will go.
    public static final JTextField destination = new JTextField();

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

    JPanel letterPanel;
    JPanel numberPanel;
    JPanel destinationPanel;
    
    
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
        frame.setTitle("XTrek");

        panel.removeAll();

        //Set up the 2 different panels - one for letters and one for numbers
        letterPanel = new JPanel();
        letterPanel.setLayout(new GridBagLayout());
        letterPanel.setBackground(Color.BLACK);
        numberPanel = new JPanel();
        numberPanel.setLayout(new GridBagLayout());
        numberPanel.setBackground(Color.BLACK);
        
        //Add each button to the controller
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
        destination.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, 22));
        destination.setBorder(null);
        destination.setOpaque(true);
        destination.setEditable(false);

        destinationPanel = new JPanel();
        
        //Layout setup for the destination panel
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

        //Grid bag layout setup for the overall panel which holds the destination and current keyboard
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 0.1;

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        panel.add(destinationPanel, c);

        c.fill = GridBagConstraints.BOTH;

        c.weightx = 1.0;
        c.weighty = 1.0;

        //Add letter buttons to the grid
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        letterPanel.add(btnA, c);

        c.gridx++;
        letterPanel.add(btnB, c);

        c.gridx++;
        letterPanel.add(btnC, c);

        c.gridx++;
        letterPanel.add(btnD, c);

        c.gridx = 0;
        c.gridy++;
        letterPanel.add(btnE, c);

        c.gridx++;
        letterPanel.add(btnF, c);

        c.gridx++;
        letterPanel.add(btnG, c);

        c.gridx++;
        letterPanel.add(btnH, c);

        c.gridx = 0;
        c.gridy++;
        letterPanel.add(btnI, c);

        c.gridx++;
        letterPanel.add(btnJ, c);

        c.gridx++;
        letterPanel.add(btnK, c);

        c.gridx++;
        letterPanel.add(btnL, c);

        c.gridx = 0;
        c.gridy++;
        letterPanel.add(btnM, c);

        c.gridx++;
        letterPanel.add(btnN, c);

        c.gridx++;
        letterPanel.add(btnO, c);

        c.gridx++;
        letterPanel.add(btnP, c);

        c.gridx = 0;
        c.gridy++;
        letterPanel.add(btnQ, c);

        c.gridx++;
        letterPanel.add(btnR, c);

        c.gridx++;
        letterPanel.add(btnS, c);

        c.gridx++;
        letterPanel.add(btnT, c);

        c.gridx = 0;
        c.gridy++;
        letterPanel.add(btnU, c);

        c.gridx++;
        letterPanel.add(btnV, c);

        c.gridx++;
        letterPanel.add(btnW, c);

        c.gridx++;
        letterPanel.add(btnX, c);

        c.gridx = 0;
        c.gridy++;
        letterPanel.add(btnY, c);

        c.gridx++;
        letterPanel.add(btnZ, c);

        c.gridx++;
        letterPanel.add(btnSpace, c);

        c.gridx++;
        letterPanel.add(btnNextPage, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 1;
        c.gridwidth = 1;
        panel.add(letterPanel, c);

        //Add numerical keyboard buttons to the grid
        c.gridx = 0;
        c.gridy = 1;
        numberPanel.add(btn1, c);

        c.gridx++;
        numberPanel.add(btn2, c);

        c.gridx++;
        numberPanel.add(btn3, c);

        c.gridx = 0;
        c.gridy++;
        numberPanel.add(btn4, c);

        c.gridx++;
        numberPanel.add(btn5, c);

        c.gridx++;
        numberPanel.add(btn6, c);

        c.gridx = 0;
        c.gridy++;
        numberPanel.add(btn7, c);

        c.gridx++;
        numberPanel.add(btn8, c);

        c.gridx++;
        numberPanel.add(btn9, c);

        c.gridx = 0;
        c.gridy++;
        numberPanel.add(btn0, c);

        c.gridx = 0;
        c.gridy++;
        numberPanel.add(btnBackPage, c);

        c.gridx++;
        c.gridy--;
        c.gridheight = 2;
        c.gridwidth = 2;
        numberPanel.add(btnDel, c);

        //Validate panels and show
        numberPanel.validate();
        letterPanel.validate();

        panel.validate();
        panel.setVisible(true);
        
        //Place cursor on the default button - A in this case
        frame.getRootPane().setDefaultButton(btnA);
        btnA.requestFocus();
        controller.giveFocus((WhereToModel.KeyboardButton) btnA);
    }

    void showLetters() {
        //Show the letters keyboard, hide the numbers keyboard
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;

        panel.removeAll();
        panel.add(destinationPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;

        letterPanel.setVisible(true);

        panel.add(letterPanel, c);

        frame.getRootPane().setDefaultButton(btnA);
        btnA.requestFocus();
        controller.giveFocus((WhereToModel.KeyboardButton) btnA);

        panel.validate();
        panel.repaint();
    }

    void showNumbers() {
        //Show the numbers keyboard, hide the letters.
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(1, 1, 1, 1);
        c.weightx = 1.0;
        c.weighty = 0.1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;

        panel.removeAll();
        panel.add(destinationPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.BOTH;

        numberPanel.setVisible(true);

        panel.add(numberPanel, c);
        panel.validate();
        panel.repaint();
    }
}
