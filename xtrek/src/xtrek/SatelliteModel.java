/**
 * Satellite Class
 * <p>
 * Reads out the current coordinates of the device.
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

    private Float latitude;
    private String latitudeDirection;
    private Float longitude;
    private String longitudeDirection;
    
    Boolean flag;
    Thread thread = new Thread(new Reader());
    ArrayList<OnGPSUpdateListener> listeners = new ArrayList<>();
    
    public class Reader implements Runnable {
        @Override
        public void run() {
            try{
                FileInputStream in = new FileInputStream(new File(Constants.dongleLocation));
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String line;

                while (true) {
                    line = br.readLine();
                    if (line==null) { Thread.sleep(500); }
                    if (line.startsWith("$GPGLL")) {
                        System.out.println(line);
                        String[] splits = line.split(",");
                        if(splits[1].equals("") || splits[2].equals("") || splits[3].equals("") || splits[4].equals("")) {
                            latitude = null;
                            latitudeDirection =null;
                            longitude = null;
                            longitudeDirection = null;
                        } else {
                            latitudeDirection = splits[2];
                            longitudeDirection = splits[4];
                            
                            latitude = Float.parseFloat(splits[1])/100;
                            longitude = Float.parseFloat(splits[3])/100;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    
    public void startThread(){
        flag = true;
        thread.start();
    }
    
    public void stopThread(){
        flag = false;
    }
    
    public Float getLatitude() {
        return latitude;
    }
    
    public Float getLongitude() {
        return longitude;
    }
    
    public String getLatitudeDirection(){
        return latitudeDirection;
    }
    
    public String getLongitudeDirection(){
        return longitudeDirection;
    }
    
    public void setListener(OnGPSUpdateListener listener){
        listeners.add(listener);
    }
    
    public void callListener() {
        int n = listeners.size();
        int i;
        for(i=0; i<n; i++){
            listeners.get(i).onGPSUpdate(latitude, longitude, latitudeDirection, longitudeDirection);
        }
    }

    @Override
    void plus(ButtonEvent evt) {}

    @Override
    void minus(ButtonEvent evt) {}

    @Override
    void selected(ButtonEvent evt) {}
    
}
