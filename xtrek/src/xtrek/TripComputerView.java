package xtrek;

import javax.swing.*;
import java.awt.*;

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

    //Set up the display components for the trip computer.
    private final JPanel odometerPanel = new JPanel();
    private final JLabel odometerLabel = new JLabel("Trip Odometer:");
    final JLabel odometerReading = new JLabel("0.00 KM");

    private final JPanel speedPanel = new JPanel();
    private final JLabel speedLabel = new JLabel("Speed:");
    final JLabel speedReading = new JLabel("0.0 KM/H");

    private final JPanel movingTimePanel = new JPanel();
    private final JLabel movingTimeLabel = new JLabel("Moving Time:");
    final JLabel movingTimeReading = new JLabel("0 min 0 sec");

    TripComputerView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("XTrek");

        //Set up grid bag constraints.
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
        final int FONT_SIZE = 26;
        odometerLabel.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, FONT_SIZE));
        speedLabel.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, FONT_SIZE));
        movingTimeLabel.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, FONT_SIZE));
        odometerReading.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, FONT_SIZE));
        speedReading.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, FONT_SIZE));
        movingTimeReading.setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, FONT_SIZE));

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

}
