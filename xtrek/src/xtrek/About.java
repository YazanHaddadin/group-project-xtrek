package xtrek;

import javax.swing.*;

/**
 * About Class
 * <p>
 * Provides information about the DEVICE
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
public class About extends Mode {

    About(JFrame frame) {
        view = new AboutView(frame);
        model = new AboutModel();

        AboutModel AModel = (AboutModel) model;
        AboutView AView = (AboutView) view;
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
