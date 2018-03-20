package xtrek;

import java.awt.*;

/**
 * Constants Class
 * <p>
 * Provides constants that will be used in multiple classes throughout the
 * project.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class Constants {
    public static final int DEVICE_WIDTH = 325;
    public static final int DEVICE_HEIGHT = 590;

    public static final Dimension DEVICE = new Dimension(DEVICE_WIDTH, DEVICE_HEIGHT);

    public static final int SCREEN_HEIGHT = 350;
    public static final int SCREEN_WIDTH = 220;

    public static final Dimension SCREEN = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

    public static final String SYSTEM_FONT = "Arial";
    
    //Defaults for the Directions class
    public static final String TRAVEL_MODE = "WALKING";
    public static final String DIRECTIONS_REGION = "UK";
    
    //Defaults for the Satellite class
    public static final String DONGLE_LOCATION = "/dev/cu.usbmodem1421";

    public static final String GOOGLE_MAP_API = "AIzaSyBlYfw7NDb9icLC-Ssq0EVi2d5EAjhqst0";
    public static final String MICROSOFT_VOICE_API = "b496988cc4d34a69a1410c097a7e56ca";
}
