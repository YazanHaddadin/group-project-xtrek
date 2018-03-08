/**
 * ModeView class
 * <p>
 * An abstract that ensure all our different panels are compatible with the main frame (same size and all)
 *
 * @author sebltm
 * @version Sprint 1
 * <p>
 * To use : extends ModeView
 * Implement displayMode() which needs to include your buttons, text labels, images, etc
 */
package xtrek;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;


public abstract class ModeView {
    final JFrame frame;
    JPanel panel;

    ModeView(JFrame frame) {
        this.frame = frame;
        //Dimensions are in pixels, need to be mm
        panel = new JPanel(){
            public void paintComponent(Graphics g) {  
                try{
                    Image img = ImageIO.read(getClass().getResource("display.png"));
                    g.drawImage(img, 0, 0, 600, 750, this);  
                } catch(Exception ex){
                    System.out.println(ex);
                }
            }
        };        
        panel.setPreferredSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        panel.setMaximumSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        panel.setBackground(Color.BLACK);
    }

    public void makeVisible() {
        panel.setVisible(true);
    }

    void hide() {
        panel.setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }

    public abstract void displayMode();
}
