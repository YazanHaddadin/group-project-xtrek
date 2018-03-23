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
class Constants {
    public static final int DEVICE_WIDTH = 325;
    public static final int DEVICE_HEIGHT = 590;

    public static final Dimension DEVICE = new Dimension(DEVICE_WIDTH, DEVICE_HEIGHT);

    static final int SCREEN_HEIGHT = 350;
    static final int SCREEN_WIDTH = 220;

    static final Dimension SCREEN = new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT);

    static final int MAP_DIM = (int) Math.round(Math.sqrt(Math.pow(SCREEN_WIDTH, 2) + Math.pow(SCREEN_HEIGHT, 2)));

    static final String SIZE_MAP = MAP_DIM + "x" + MAP_DIM;

    static final String SYSTEM_FONT = "Arial";

    //Defaults for the Directions class
    static final String TRAVEL_MODE = "walking";
    static final String DIRECTIONS_REGION = "UK";

    //Defaults for the Satellite class
    static final String DONGLE_LOCATION = "/dev/cu.usbmodem1421";

    static final String GOOGLE_MAP_API = "AIzaSyBlYfw7NDb9icLC-Ssq0EVi2d5EAjhqst0";
    static final String MICROSOFT_VOICE_API = "b496988cc4d34a69a1410c097a7e56ca";

    static final Float GPS_TOLERANCE = 0.01f; //5 meters
}
