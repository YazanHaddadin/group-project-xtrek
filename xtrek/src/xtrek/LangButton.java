package xtrek;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class LangButton extends JButton {
    private final TurnByTurn.Gender GENDER;
    private final TurnByTurn.Language LANGUAGE;

    LangButton(TurnByTurn.Language language, TurnByTurn.Gender gender) {
        super(language.getDisplay());

        setStyle();
        this.GENDER = gender;
        this.LANGUAGE = language;
    }

    private void focusGained() {
        setBackground(Color.ORANGE);
    }

    private void focusLost() {
        setBackground(Color.WHITE);
    }

    private void setStyle() {
        setBackground(Color.WHITE);
        setBorderPainted(false);
        setFont(new Font("Arial", Font.BOLD, 36));
        setHorizontalAlignment(SwingConstants.LEFT);
    }

    TurnByTurn.Language getLanguage() {
        return LANGUAGE;
    }

    TurnByTurn.Gender getGender() {
        return GENDER;
    }

    void giveFocus(ArrayList<LangButton> buttons) {
        for(LangButton randButton : buttons) randButton.focusLost();
        this.focusGained();
    }
}