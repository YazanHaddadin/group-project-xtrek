package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class LangButton extends JButton {
    private final TurnByTurn controller;
    private final TurnByTurn.Gender GENDER;
    private final TurnByTurn.Language LANGUAGE;

    LangButton(TurnByTurn.Language language, TurnByTurn.Gender gender, TurnByTurn controller) {
        super(language.getDisplay());

        setStyle();
        this.GENDER = gender;
        this.LANGUAGE = language;
        this.controller = controller;

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                LangButton.this.focusGained();
            }

            @Override
            public void focusLost(FocusEvent e) {
                LangButton.this.focusLost();
            }
        });
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
}