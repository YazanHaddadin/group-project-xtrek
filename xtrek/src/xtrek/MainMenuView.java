/**
 * Main Menu View Class
 * <p>
 * Provides the User Interface of the Main Menu for the Xtrek.
 *
 * @author Yazan Haddadin
 * @version Sprint 1
 */
package xtrek;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenuView extends ModeView{
    private MainMenu controller;
    
    private JButton whereTo;
    private JButton tripComputer;
    private JButton map;
    private JButton speech;
    private JButton satellite;
    private JButton about;

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
        frame.setTitle("Main Menu");

        whereTo      = controller.addButton("whereto", WhereTo.class);
        tripComputer = controller.addButton("tripcomputer", TripComputer.class);
        map          = controller.addButton("map", Map.class);
        speech       = controller.addButton("speech", TurnByTurn.class);
        satellite    = controller.addButton("satellite", Satellite.class);
        about        = controller.addButton("about", About.class);    
        
        
        //using GridBagConstraints to adapt to different screen sizes
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 0, 40, 40);
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
    }
}
