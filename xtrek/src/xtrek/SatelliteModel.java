/**
 * Satellite Class
 * <p>
 * Reads out the current coordinates of the device.
 *
 * @author Liam Vinson
 * @version Sprint 2
 */

package xtrek;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author liamvinson
 */
public class SatelliteModel extends ModeModel {
    
    Float latitude;
    String latitudeDirection;
    Float longitude;
    String longitudeDirection;
    
    Boolean flag;
    
    Thread thread = new Thread(new Reader());
    
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

    @Override
    void plus(ButtonEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void minus(ButtonEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void selected(ButtonEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
