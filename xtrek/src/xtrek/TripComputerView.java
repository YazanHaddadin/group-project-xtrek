/**
 * TripComputer View Class
 * <p>
 * Provides the interface for the Trip Computer function of the XTrek, which 
 * will provide information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 2
 */
package xtrek;

import java.awt.*;
import javax.swing.*;


public class TripComputerView extends ModeView{
    private TripComputer controller;
    
    JPanel odometerPanel = new JPanel();
    JLabel odometerLabel = new JLabel("Trip Odometer:");
    JLabel odometerReading = new JLabel("1.0 KM");

    JPanel speedPanel = new JPanel();
    JLabel speedLabel = new JLabel("Speed:");
    JLabel speedReading = new JLabel("4.5 KM/H");

    JPanel movingTimePanel = new JPanel();
    JLabel movingTimeLabel = new JLabel("Moving Time:");
    JLabel movingTimeReading = new JLabel("28 mins 35 secs");
    
    @Override
    public void displayMode() {
        frame.setTitle("Trip Computer");

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(10, 10, 10, 10);
        c.weightx = 1.0;
        c.weighty = 1.0;

        //Set relative position for each panel in the grid bag layout.
        c.gridx = 0;
        c.gridy = 0;
        panel.add(odometerPanel, c);

        c.gridy++;
        panel.add(speedPanel, c);

        c.gridy++;
        panel.add(movingTimePanel, c);

        //Set background colours for each panel.
        odometerPanel.setBackground(Color.WHITE);
        speedPanel.setBackground(Color.WHITE);
        movingTimePanel.setBackground(Color.WHITE);

        //Set correct font for each label.
        odometerLabel.setFont(new Font(Constants.systemFont, Font.BOLD, 50));
        speedLabel.setFont(new Font(Constants.systemFont, Font.BOLD, 50));
        movingTimeLabel.setFont(new Font(Constants.systemFont, Font.BOLD, 50));

        odometerReading.setFont(new Font(Constants.systemFont, Font.BOLD, 50));
        speedReading.setFont(new Font(Constants.systemFont, Font.BOLD, 50));
        movingTimeReading.setFont(new Font(Constants.systemFont, Font.BOLD, 50));

        odometerPanel.add(odometerLabel);
        odometerPanel.add(odometerReading);
        speedPanel.add(speedLabel);
        speedPanel.add(speedReading);
        movingTimePanel.add(movingTimeLabel);
        movingTimePanel.add(movingTimeReading);

        frame.setVisible(true);
        panel.validate();
        panel.setVisible(true);
    }
    
    public TripComputerView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
    
    void setController(TripComputer controller) {
        this.controller = controller;
    }
}