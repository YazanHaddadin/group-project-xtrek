package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * Main Menu View Class
 * <p>
 * Provides the User Interface of the Main Menu for the Xtrek.
 *
 * @author Yazan Haddadin
 * @version Sprint 3
 */
public class MainMenuView extends ModeView {
    private MainMenu controller;

    MainMenuView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
    }

    void setController(MainMenu controller) {
        this.controller = controller;
    }

    @Override
    public void displayMode() {
        //this is the actual display of the Main Menu mode
        frame.setTitle("XTrek");

        panel.removeAll();
        controller.emptyArray();

        JButton whereTo = controller.addButton("whereto", WhereTo.class);
        JButton tripComputer = controller.addButton("tripcomputer", TripComputer.class);
        JButton map = controller.addButton("map", Map.class);
        JButton speech = controller.addButton("speech", TurnByTurn.class);
        JButton satellite = controller.addButton("satellite", Satellite.class);
        JButton about = controller.addButton("about", About.class);

        //using GridBagConstraints to adapt to different SCREEN sizes
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(2, 2, 2, 2);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(whereTo, c);

        c.gridy = 1;
        panel.add(map, c);

        c.gridy = 2;
        panel.add(satellite, c);

        c.gridx = 1;
        c.gridy = 0;
        panel.add(tripComputer, c);

        c.gridy = 1;
        panel.add(speech, c);

        c.gridy = 2;
        panel.add(about, c);

        map.setVisible(Xtrek.isOn);
        about.setVisible(Xtrek.isOn);
        speech.setVisible(Xtrek.isOn);
        whereTo.setVisible(Xtrek.isOn);
        satellite.setVisible(Xtrek.isOn);
        tripComputer.setVisible(Xtrek.isOn);

        panel.validate();
        panel.setVisible(true);

        whereTo.setBackground(Color.ORANGE);

        frame.getRootPane().setDefaultButton(whereTo);
        whereTo.requestFocus();
        controller.giveFocus(whereTo);
    }
}
