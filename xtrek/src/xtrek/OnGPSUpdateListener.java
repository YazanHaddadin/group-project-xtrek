package xtrek;

import java.util.EventListener;

interface OnGPSUpdateListener extends EventListener {
    void onGPSUpdate(Double latitude,
                     Double longitude,
                     SatelliteModel.Direction latitudeDirection,
                     SatelliteModel.Direction longitudeDirection);
}
