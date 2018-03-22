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
