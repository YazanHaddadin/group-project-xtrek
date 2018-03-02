package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class LangButton extends JButton implements SelectedListener {
    private final TurnByTurn controller;
    private final TurnByTurn.Gender GENDER;
    private final TurnByTurn.Language LANGUAGE;
    private final SelectButton SELECT = new SelectButton();

    LangButton(TurnByTurn.Language language, TurnByTurn.Gender gender, TurnByTurn controller) {
        super(language.getDisplay());

        setStyle();
        this.GENDER = gender;
        this.LANGUAGE = language;
        this.controller = controller;

            /*THE MOUSE LISTENER WILL GET REMOVED WHEN THE BUTTONS ARE PROPERLY
            IMPLEMENTED. THIS IS ONLY FOR TESTING PURPOSE ATM
            */
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LangButton.this.selected();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                LangButton.this.focusGained();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                LangButton.this.focusLost();
            }
        });

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

        SELECT.setSelectedListener(this);
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
    public void selected() {
        controller.setGender(this.GENDER.getGender());
        controller.setLanguage(this.LANGUAGE.getLanguage());
        controller.playAudio("Hello, this is a sentence translated from English to my native" +
                "language");
    }
}