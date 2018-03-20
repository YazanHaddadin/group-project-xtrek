package xtrek;

import javax.swing.*;
import java.awt.*;

/**
 * About Class
 * <p>
 * Provides information about the DEVICE
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
public class About extends Mode {
    private AboutView AView;
    private AboutModel AModel;

    About(JFrame frame) {
        view = new AboutView(frame);
        model = new AboutModel();

        AModel = (AboutModel) model;
        AView = (AboutView) view;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setResizable(false);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new About(frame);
        ControlLayout controlPanel = new ControlLayout(frame, currentView);

        currentView.displayMode();
        currentView.show();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(controlPanel.getPanel(), con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

    @Override
    public void selected(ButtonEvent evt) {

    }

    @Override
    public void plus(ButtonEvent evt) {

    }

    @Override
    public void minus(ButtonEvent evt) {

    }
}
