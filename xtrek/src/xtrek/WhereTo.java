package xtrek;

import javax.swing.*;
import java.util.ArrayList;

/**
 * WhereTo Controller Class
 * <p>
 * Provides an interface for communication between the model and the view of
 * the keyboard on the XTrek where the user will input their destination.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class WhereTo extends Mode {
    
    private static WhereToView whereView;
    private static WhereToModel whereModel;
    static OnChangeDestinationListener listener;
    static ArrayList<OnChangeDestinationListener> listeners = new ArrayList<>();
    
    WhereTo(JFrame frame) {
        model = new WhereToModel();
        view = new WhereToView(frame);

        whereModel = (WhereToModel) model;
        whereView = (WhereToView) view;
    }

    public static void callListener() {
        int n = listeners.size();
        int i;
        for (i = 0; i < n; i++) {
            listeners.get(i).onChangeDestination(WhereToView.destination.getText());
        }
    }

    static void addToDestination(String letter) {
        //Add the typed letter to the destination field
        WhereToView.destination.setText(WhereToView.destination.getText() + letter);
    }
    
    @Override
    void displayMode() {
        whereView.setController(this);
        whereView.displayMode();
        showLetterButtons();
    }
    
    @Override
    public void selected(ButtonEvent evt) {
        whereModel.selected(evt);
    }

    @Override
    public void plus(ButtonEvent evt) {
        whereModel.plus(evt);
    }

    @Override
    public void minus(ButtonEvent evt) {
        whereModel.minus(evt);
    }
    
    //Add a button to the model.
    JButton addButton(String letter, WhereTo.buttonType type) {
        return whereModel.addButton(letter, type);
    }
    
    void giveFocus(JButton button) {
        whereModel.giveFocus((WhereToModel.KeyboardButton) button);
    }
    
    static void deleteFromDestination() {
        // Delete 1 character from the destination field
        if (WhereToView.destination.getText().length() > 0) {
            WhereToView.destination.setText(WhereToView.destination.getText().substring(0, WhereToView.destination.getText().length() - 1));
            callListener();
        }
    }

    public static String getCurrentDestination() {
        return WhereToView.destination.getText();
    }

    public void setListener(OnChangeDestinationListener listener) {
        WhereTo.listener = listener;
    }
    
    static void hideLetterButtons() {
        //Hide all buttons for the main letter keyboard
        whereView.btnA.setVisible(false);
        whereView.btnB.setVisible(false);
        whereView.btnC.setVisible(false);
        whereView.btnD.setVisible(false);
        whereView.btnE.setVisible(false);
        whereView.btnF.setVisible(false);
        whereView.btnG.setVisible(false);
        whereView.btnH.setVisible(false);
        whereView.btnI.setVisible(false);
        whereView.btnJ.setVisible(false);
        whereView.btnK.setVisible(false);
        whereView.btnL.setVisible(false);
        whereView.btnM.setVisible(false);
        whereView.btnN.setVisible(false);
        whereView.btnO.setVisible(false);
        whereView.btnP.setVisible(false);
        whereView.btnQ.setVisible(false);
        whereView.btnR.setVisible(false);
        whereView.btnS.setVisible(false);
        whereView.btnT.setVisible(false);
        whereView.btnU.setVisible(false);
        whereView.btnV.setVisible(false);
        whereView.btnW.setVisible(false);
        whereView.btnX.setVisible(false);
        whereView.btnY.setVisible(false);
        whereView.btnZ.setVisible(false);
        whereView.btnSpace.setVisible(false);
        whereView.btnNextPage.setVisible(false);
    }
    
    static void showLetterButtons() {
        //Hide all buttons for the main letter keyboard
        whereView.btnA.setVisible(true);
        whereView.btnB.setVisible(true);
        whereView.btnC.setVisible(true);
        whereView.btnD.setVisible(true);
        whereView.btnE.setVisible(true);
        whereView.btnF.setVisible(true);
        whereView.btnG.setVisible(true);
        whereView.btnH.setVisible(true);
        whereView.btnI.setVisible(true);
        whereView.btnJ.setVisible(true);
        whereView.btnK.setVisible(true);
        whereView.btnL.setVisible(true);
        whereView.btnM.setVisible(true);
        whereView.btnN.setVisible(true);
        whereView.btnO.setVisible(true);
        whereView.btnP.setVisible(true);
        whereView.btnQ.setVisible(true);
        whereView.btnR.setVisible(true);
        whereView.btnS.setVisible(true);
        whereView.btnT.setVisible(true);
        whereView.btnU.setVisible(true);
        whereView.btnV.setVisible(true);
        whereView.btnW.setVisible(true);
        whereView.btnX.setVisible(true);
        whereView.btnY.setVisible(true);
        whereView.btnZ.setVisible(true);
        whereView.btnSpace.setVisible(true);
        whereView.btnNextPage.setVisible(true);
    }
    
    static void showNumberButtons() {
        //Show all buttons for the numerical keypad.
        whereView.btn1.setVisible(true);
        whereView.btn2.setVisible(true);
        whereView.btn3.setVisible(true);
        whereView.btn4.setVisible(true);
        whereView.btn5.setVisible(true);
        whereView.btn6.setVisible(true);
        whereView.btn7.setVisible(true);
        whereView.btn8.setVisible(true);
        whereView.btn9.setVisible(true);
        whereView.btn0.setVisible(true);
        whereView.btnDel.setVisible(true);
        whereView.btnBackPage.setVisible(true);
    }
    
    static void hideNumberButtons() {
        //Hide all buttons on the numerical keypad.
        whereView.btn1.setVisible(false);
        whereView.btn2.setVisible(false);
        whereView.btn3.setVisible(false);
        whereView.btn4.setVisible(false);
        whereView.btn5.setVisible(false);
        whereView.btn6.setVisible(false);
        whereView.btn7.setVisible(false);
        whereView.btn8.setVisible(false);
        whereView.btn9.setVisible(false);
        whereView.btn0.setVisible(false);
        whereView.btnDel.setVisible(false);
        whereView.btnBackPage.setVisible(false);
    }

    //Enumeration for the different types of keyboard button
    enum buttonType {
        LETTER_NUMBER,
        DEL,
        SPACE,
        NEXT_PAGE,
        BACK_PAGE
    }
}