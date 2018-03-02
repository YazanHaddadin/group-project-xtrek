package xtrek;

import javax.swing.*;
import java.awt.*;

public class TurnByTurnView extends ModeView {
    private JButton bOff;
    private JButton bEng;
    private JButton bFre;
    private JButton bGer;
    private JButton bIta;
    private JButton bJap;
    private TurnByTurn controller;

    TurnByTurnView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }

    void setController(TurnByTurn controller) {
        this.controller = controller;
    }

    @Override
    public void displayMode() {
        frame.setTitle("Turn-By-Turn");

        bOff = controller.addButton(TurnByTurn.Language.OFF, null);
        bEng = controller.addButton(TurnByTurn.Language.ENGLISH, TurnByTurn.Gender.MALE);
        bFre = controller.addButton(TurnByTurn.Language.FRENCH, TurnByTurn.Gender.FEMALE);
        bGer = controller.addButton(TurnByTurn.Language.GERMAN, TurnByTurn.Gender.MALE);
        bIta = controller.addButton(TurnByTurn.Language.ITALIAN, TurnByTurn.Gender.MALE);
        bJap = controller.addButton(TurnByTurn.Language.JAPANESE, TurnByTurn.Gender.MALE);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(bOff, c);

        c.gridy++;
        panel.add(bEng, c);

        c.gridy++;
        panel.add(bFre, c);

        c.gridy++;
        panel.add(bGer, c);

        c.gridy++;
        panel.add(bIta, c);

        c.gridy++;
        panel.add(bJap, c);

        panel.validate();
        panel.setVisible(false);
    }
}
