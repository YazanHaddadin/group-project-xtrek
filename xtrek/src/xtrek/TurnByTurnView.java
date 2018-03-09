package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * TurnByTurn View
 *
 * @author Sebastien Michel
 * @version Sprint 2
 */
public class TurnByTurnView extends ModeView {
    private TurnByTurn controller;

    TurnByTurnView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
    }

    void setController(TurnByTurn controller) {
        this.controller = controller;
    }

    @Override
    public void displayMode() {
        frame.setTitle("Turn-By-Turn");

        JButton bOff = controller.addButton(TurnByTurn.Language.OFF, TurnByTurn.Gender.MALE);
        JButton bEng = controller.addButton(TurnByTurn.Language.ENGLISH, TurnByTurn.Gender.MALE);
        JButton bFre = controller.addButton(TurnByTurn.Language.FRENCH, TurnByTurn.Gender.FEMALE);
        JButton bGer = controller.addButton(TurnByTurn.Language.GERMAN, TurnByTurn.Gender.MALE);
        JButton bIta = controller.addButton(TurnByTurn.Language.ITALIAN, TurnByTurn.Gender.MALE);
        JButton bJap = controller.addButton(TurnByTurn.Language.SPANISH, TurnByTurn.Gender.MALE);

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1, 1, 1, 1);
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

        bOff.setBackground(Color.ORANGE);

        frame.getRootPane().setDefaultButton(bOff);
        bOff.requestFocus();
        controller.giveFocus(bOff);
    }
}
