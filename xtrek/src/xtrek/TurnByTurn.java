package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * TurnByTurn class
 * Provides UI to choose a language for the Turn By Turn, translates the directions and speaks them out when
 * needed
 *
 * @author Sebastien Michel
 * @version Sprint 2
 */
public class TurnByTurn extends Mode implements SpeechListener {
    private final TurnByTurnView TBTView;
    private final TurnByTurnModel TBTModel;

    TurnByTurn(JFrame frame) {
        model = new TurnByTurnModel();
        view = new TurnByTurnView(frame);

        TBTModel = (TurnByTurnModel) model;
        TBTView = (TurnByTurnView) view;
    }

    /**
     * The main method is just for testing purposes, allowing me to test my class independently of others
     * It will not be present in the final product
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new TurnByTurn(frame);
        ControlLayout controlPanel = new ControlLayout(frame, currentView);

        currentView.displayMode();
        currentView.show();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(controlPanel.getPanel(), con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

    @Override
    void displayMode() {
        TBTView.setController(this);
        TBTView.displayMode();
    }

    JButton addButton(Language language, Gender gender) {
        return TBTModel.addButton(language, gender);
    }

    void giveFocus(JButton button) {
        TBTModel.giveFocus((TurnByTurnModel.LangButton) button);
    }

    @Override
    public void selected(ButtonEvent evt) {
        TBTModel.selected(evt);
    }

    @Override
    public void plus(ButtonEvent evt) {
        TBTModel.plus(evt);
    }

    @Override
    public void minus(ButtonEvent evt) {
        TBTModel.minus(evt);
    }

    @Override
    public void speakNextSegment(SpeechEvent evt) {
        TBTModel.playAudio(evt.speech);
    }

    public enum Language {
        OFF("Off", "en-GB"),
        ENGLISH("English", "en-GB"),
        FRENCH("French", "fr-FR"),
        GERMAN("German", "de-DE"),
        ITALIAN("Italian", "it-IT"),
        SPANISH("Spanish", "es-es");

        private final String language;
        private final String display;

        Language(String display, String language) {
            this.display = display;
            this.language = language;
        }

        public String getLanguage() {
            return language;
        }

        public String getDisplay() {
            return display;
        }
    }

    public enum Gender {
        MALE("Male"),
        FEMALE("Female");

        private final String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }
    }
}
