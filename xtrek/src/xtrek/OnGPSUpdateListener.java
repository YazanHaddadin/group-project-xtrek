package xtrek;

import java.util.ArrayList;
import java.util.HashMap;

interface OnGPSUpdateListener {
    void onGPSUpdate(ArrayList<HashMap<Float, String>> data);
}
