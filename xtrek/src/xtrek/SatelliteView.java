/**
 * SatelliteView Class
 * <p>
 * Reads out the current coordinates of the device.
 *
 * @author Liam Vinson
 * @version Sprint 2
 */
package xtrek;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;

/**
 *
 * @author liamvinson
 */
public class SatelliteView extends ModeView {
    SatellitePanel sat = new SatellitePanel();
    
    SatelliteView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
    }
    
    public void setPosition(Float value1, String direction1, Float value2, String direction2) {
        sat.setLabel1(value1 + " " + direction1);
        sat.setLabel2(value2 + " " + direction2);
    }
    
    public void setNoSignal() {
        sat.setLabel1("No Signal");
        sat.setLabel2("");
    }

    @Override
    public void displayMode() {
        frame.setTitle("Satellite");
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1,1,1,1);
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(sat, c);
    }
}
