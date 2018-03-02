/**
 * TurnByTurn class
 * <p>
 * Provides UI to choose and language for the Turn By Turn, translates the directions and speaks them out when
 * needed
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import javax.swing.*;
import java.awt.*;

public class TurnByTurn extends Mode {
    private static TurnByTurnView view;
    private static TurnByTurnModel model;

    TurnByTurn(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        view = new TurnByTurnView(frame, panel);
        model = new TurnByTurnModel();
        displayMode();
    }

    public enum Language {
        OFF("Off", null),
        ENGLISH("English", "en-GB"),
        FRENCH("French", "fr-FR"),
        GERMAN("German", "de-DE"),
        ITALIAN("Italian", "it-IT"),
        JAPANESE("Japanese", "ja-JP");

        private String language;
        private String display;

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

        private String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setPreferredSize(new Dimension(700, 850));
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new TurnByTurn(frame);
        currentView.displayMode();
        currentView.makeVisible();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(currentView.panel, con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

    @Override
    public void displayMode() {
        view.setController(this);
        view.setView();
    }

    void setLanguage(String language) {
        model.setLanguage(language);
    }

    void setGender(String gender) {
        model.setGender(gender);
    }

    void playAudio(String segment) {
        model.stopAudio();
        TurnByTurnModel.playAudio(segment);
    }

    JButton addButton(Language language, Gender gender) {
        return new LangButton(language, gender, this);
    }
}
