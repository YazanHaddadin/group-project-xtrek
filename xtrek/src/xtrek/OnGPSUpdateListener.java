package xtrek;

import java.util.EventListener;

interface OnGPSUpdateListener extends EventListener {
    void onGPSUpdate(Float latitude, Float longitude, String latitudeDirection, String longitudeDirection);
}
