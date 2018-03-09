/**
 * AboutView Class
 * <p>
 * Provides information about the device
 *
 * @author Liam Vinson
 * @version Sprint 2
 */
package xtrek;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;

/**
 *
 * @author liamvinson
 */
public class AboutView extends ModeView {
    AboutPanel about = new AboutPanel();

    public AboutView(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
    }

    @Override
    public void displayMode() {
        frame.setTitle("About");
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(1,1,1,1);
        c.weightx = 1.0;
        c.weighty = 1.0;
        panel.add(about, c);
    }
    
}
