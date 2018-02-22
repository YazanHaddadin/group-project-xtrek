/*
 * TripComputer Class
 *
 * Provides live trip information to the user, including an odometer, their
 * speed and the time they have spent moving.
 *
 * @author Caleb Blackmore
 * @version Sprint 1
 */
package xtrek;

import java.awt.*;
import javax.swing.*;

public class TripComputer extends Mode {
 
    JPanel odometerPanel = new JPanel();
    JLabel odometerLabel = new JLabel("Trip Odometer:");
    
    JPanel speedPanel = new JPanel();
    JLabel speedLabel = new JLabel("Speed:");
    
    JPanel movingTimePanel = new JPanel();
    JLabel movingTimeLabel = new JLabel("Moving Time:");
    
    
    
   
    public TripComputer(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }
    
    @Override
    public void displayMode() {
        frame.setTitle("Trip Computer");
        
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(odometerPanel, c);

        c.gridy++;
        panel.add(speedPanel, c);

        c.gridy++;
        panel.add(movingTimePanel, c );

        
        odometerPanel.add(odometerLabel);
        speedPanel.add(speedLabel);
        movingTimePanel.add(movingTimeLabel);
        
        frame.setVisible(true);
        
        panel.validate();
        panel.setVisible(true);
    }
    
}
