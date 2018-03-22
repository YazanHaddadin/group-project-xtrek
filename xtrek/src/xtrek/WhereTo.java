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
    private static OnChangeDestinationListener listener;
    static final ArrayList<OnChangeDestinationListener> listeners = new ArrayList<>();
    
    WhereTo(JFrame frame) {
        model = new WhereToModel();
        view = new WhereToView(frame);

        whereModel = (WhereToModel) model;
        whereView = (WhereToView) view;
    }

    static void callListener() {
        listener.onChangeDestination(WhereToView.destination.getText());
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

    static void showLetterButtons() {
        //Show the letters keyboard
        whereView.btnA.requestFocus();
        whereView.showLetters();
        whereView.panel.revalidate();
        whereView.panel.repaint();
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

    static void showNumberButtons() {
        //Show the numbers keyboard
        whereView.showNumbers();
        whereView.btn1.requestFocus();
        whereView.panel.revalidate();
        whereView.panel.repaint();
    }

    void giveFocus(WhereToModel.KeyboardButton button) {
        whereModel.giveFocus(button);
    }

    void setListener(OnChangeDestinationListener listener) {
        WhereTo.listener = listener;
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