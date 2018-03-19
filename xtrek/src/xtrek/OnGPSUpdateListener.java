package xtrek;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;

interface OnGPSUpdateListener extends EventListener {
    void onGPSUpdate(ArrayList<HashMap<Float, String>> data);
}
