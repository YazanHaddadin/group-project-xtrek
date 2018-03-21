package xtrek;

import java.util.EventListener;

interface OnGPSUpdateListener extends EventListener {
    void onGPSUpdate(Float latitude,
                     Float longitude,
                     SatelliteModel.Direction latitudeDirection,
                     SatelliteModel.Direction longitudeDirection);
}
