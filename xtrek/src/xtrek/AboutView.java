package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * About View Class
 * <p>
 * Provides information about the DEVICE
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
public class AboutView extends ModeView {
    AboutPanel about = new AboutPanel();

    public AboutView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
    }

    @Override
    public void displayMode() {
        frame.setTitle("XTrek");
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1,1,1,1);
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(about, c);
    }
    
}
