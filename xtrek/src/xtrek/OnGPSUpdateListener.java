package xtrek;

import java.util.EventListener;

/**
 * On GPS Update Listener Class
 * <p>
 * Event listener for when the GPS dongle gets updated coordinates.
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
interface OnGPSUpdateListener extends EventListener {
    void onGPSUpdate(Double latitude,
                     Double longitude,
                     SatelliteModel.Direction latitudeDirection,
                     SatelliteModel.Direction longitudeDirection);
}
