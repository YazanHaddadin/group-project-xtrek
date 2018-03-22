package xtrek;

/**
 * GPS Enumeration
 * <p>
 * Provides an enumeration for the direction of the latitude and longitude.
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
public enum GPS {
    NORTH("+"), SOUTH("-"), EAST("+"), WEST("-");

    final String val;

    GPS(String val) {
        this.val = val;
    }
}
