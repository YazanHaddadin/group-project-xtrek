/**
 * Satellite Class
 * <p>
 * Reads out the current coordinates of the DEVICE.
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
package xtrek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SatelliteModel extends ModeModel {

    private String latitudeDirection;
    private String longitudeDirection;
    
    Boolean flag;
    Thread thread = new Thread(new Reader());
    ArrayList<OnGPSUpdateListener> listeners = new ArrayList<>();

    public void callListener(Float latitude, Float longitude, String latitudeDirection, String longitudeDirection) {
        int n = listeners.size();
        int i;
        for (OnGPSUpdateListener listener : listeners) {
            listener.onGPSUpdate(latitude, longitude, latitudeDirection, longitudeDirection);
        }
    }

    public void startThread() {
        flag = true;
        thread.start();
    }

    public void stopThread() {
        flag = false;
    }
    
    public String getLatitudeDirection() {
        return latitudeDirection;
    }

    public String getLongitudeDirection() {
        return longitudeDirection;
    }

    public void setListener(OnGPSUpdateListener listener) {
        listeners.add(listener);
    }
    
    public class Reader implements Runnable {
        @Override
        public void run() {
            try{
                FileInputStream in = new FileInputStream(new File(Constants.DONGLE_LOCATION));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;

                while (true) {
                    line = br.readLine();
                    if (line==null) { Thread.sleep(500); }
                    if (line.startsWith("$GPGLL")) {
                        System.out.println(line);
                        String[] splits = line.split(",");
                        if(splits[1].equals("") || splits[2].equals("") || splits[3].equals("") || splits[4].equals("")) {
                            //Skip
                        } else {
                            latitudeDirection = splits[2];
                            longitudeDirection = splits[4];

                            Integer latDeg = Integer.parseInt(splits[1]) % 100;
                            Integer lonDeg = Integer.parseInt(splits[3]) % 100;

                            Float latMin = (Float.parseFloat(splits[1]) - latDeg) / 60;
                            Float lonMin = (Float.parseFloat(splits[1]) - lonDeg) / 60;

                            Float latitude = latDeg + latMin;
                            Float longitude = lonDeg + lonMin;

                            callListener(latitude, longitude, latitudeDirection, longitudeDirection);
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    void plus(ButtonEvent evt) {}

    @Override
    void minus(ButtonEvent evt) {}

    @Override
    void selected(ButtonEvent evt) {}
    
}
