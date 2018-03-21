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
    private Float latitude;
    private Float longitude;

    private Boolean flag;
    private Thread thread = new Thread(new Reader());
    private ArrayList<OnGPSUpdateListener> listeners = new ArrayList<>();

    private void callListener(Float latitude, Float longitude, String latitudeDirection, String longitudeDirection) {
        for (OnGPSUpdateListener listener : listeners) {
            listener.onGPSUpdate(latitude, longitude, latitudeDirection, longitudeDirection);
        }
    }

    void startThread() {
        flag = true;
        thread.start();
    }

    public void stopThread() {
        flag = false;
    }

    Float getLatitude() {
        return latitude;
    }

    Float getLongitude() {
        return longitude;
    }

    String getLatitudeDirection() {
        return latitudeDirection;
    }


    String getLongitudeDirection() {
        return longitudeDirection;
    }

    void setListener(OnGPSUpdateListener listener) {
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
                    //line = "$GPGLL,5015.74572,N,00504.57661,W,234108.00,A,A*77";
                    if (line==null) { Thread.sleep(500); }
                    if (line.startsWith("$GPGLL")) {
                        System.out.println(line);
                        String[] splits = line.split(",");
                        if(splits[1].equals("") || splits[2].equals("") || splits[3].equals("") || splits[4].equals("")) {
                            //Skip
                        } else {
                            latitudeDirection = splits[2];
                            longitudeDirection = splits[4];

                            Float latMin = Float.parseFloat(splits[1]) % 100 / 60;
                            Float lonMin = Float.parseFloat(splits[3]) % 100 / 60;

                            Integer latDeg = Math.round(Float.parseFloat(splits[1]) / 100 - latMin);
                            Integer lonDeg = Math.round(Float.parseFloat(splits[3]) / 100 - lonMin);

                            latitude = latDeg + latMin;
                            longitude = lonDeg + lonMin;

                            callListener(latitude, longitude, latitudeDirection, longitudeDirection);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
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
