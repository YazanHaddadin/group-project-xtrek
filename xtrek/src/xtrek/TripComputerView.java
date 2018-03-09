package xtrek;

import java.awt.*;
import javax.swing.*;

/**
 * TripComputer View Class
 * <p>
 * Provides the interface for the Trip Computer function of the XTrek, which 
 * will provide information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class TripComputerView extends ModeView{
    private TripComputer controller;
    
    private int fontSize = 26;
    
    JPanel odometerPanel = new JPanel();
    JLabel odometerLabel = new JLabel("Trip Odometer:");
    JLabel odometerReading = new JLabel("0.00 KM");

    JPanel speedPanel = new JPanel();
    JLabel speedLabel = new JLabel("Speed:");
    JLabel speedReading = new JLabel("6.0 KM/H");

    JPanel movingTimePanel = new JPanel();
    JLabel movingTimeLabel = new JLabel("Moving Time:");
    JLabel movingTimeReading = new JLabel("0 min 0 sec");
    
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
        odometerLabel.setFont(new Font(Constants.systemFont, Font.BOLD, fontSize));
        speedLabel.setFont(new Font(Constants.systemFont, Font.BOLD, fontSize));
        movingTimeLabel.setFont(new Font(Constants.systemFont, Font.BOLD, fontSize));

        odometerReading.setFont(new Font(Constants.systemFont, Font.BOLD, fontSize));
        speedReading.setFont(new Font(Constants.systemFont, Font.BOLD, fontSize));
        movingTimeReading.setFont(new Font(Constants.systemFont, Font.BOLD, fontSize));

        //Add labels to each panel
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
