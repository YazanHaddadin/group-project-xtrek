package xtrek;

import javax.swing.*;

/**
 * turnByTurn class, Controller
 * Provides UI to choose a language for the Turn By Turn, translates the directions and speaks them out when
 * needed
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
public class TurnByTurn extends Mode implements OnDirectionsUpdateListener {
    private final TurnByTurnView TBTView;
    private final TurnByTurnModel TBTModel;

    TurnByTurn(JFrame frame) {
        model = new TurnByTurnModel();
        view = new TurnByTurnView(frame);

        TBTModel = (TurnByTurnModel) model;
        TBTView = (TurnByTurnView) view;
    }

    @Override
    void displayMode() {
        TBTView.setController(this);
        TBTView.displayMode();
        TBTModel.initClass();
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
