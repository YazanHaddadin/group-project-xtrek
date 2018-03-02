package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

class LangButton extends JButton implements SelectedListener {
    private final TurnByTurn controller;
    private final TurnByTurn.Gender GENDER;
    private final TurnByTurn.Language LANGUAGE;

    LangButton(TurnByTurn.Language language, TurnByTurn.Gender gender, TurnByTurn controller) {
        super(language.getDisplay());

        setStyle();
        this.GENDER = gender;
        this.LANGUAGE = language;
        this.controller = controller;

            /*THE MOUSE LISTENER WILL GET REMOVED WHEN THE BUTTONS ARE PROPERLY
            IMPLEMENTED. THIS IS ONLY FOR TESTING PURPOSE ATM
            */

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

        ControlLayout.getSelectButton().addSelectedListener(this);

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

    @Override
    public void selected(SelectedEvent e) {
        System.out.println(this.LANGUAGE.getLanguage());
        controller.setGender(this.GENDER.getGender());
        controller.setLanguage(this.LANGUAGE.getLanguage());
        controller.playAudio("Hello, this is a sentence translated from English to my native" +
                "language");
    }
}