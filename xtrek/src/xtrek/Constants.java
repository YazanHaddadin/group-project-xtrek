/**
 * Constants Class
 * <p>
 * Provides constants that will be used in multiple classes throughout the
 * project.
 *
 * @author Caleb Blackmore
 * @version Sprint 2
 */
package xtrek;

import java.awt.*;

public class Constants {
    public static final int deviceWidth = 325;
    public static final int deviceHeight = 590;

    public static final Dimension device = new Dimension(deviceWidth, deviceHeight);

    public static final int screenHeight = 176;
    public static final int screenWidth = 220;

    public static final Dimension screen = new Dimension(screenWidth, screenHeight);

    public static final String systemFont = "Arial";
    
    //Defaults for the Directions class
    public static final String travelMode = "WALKING";
    public static final String directionsRegion = "UK";
    
    //Defaults for the Satellite class
    public static final String dongleLocation = "/dev/cu.usbmodem1421";
}
