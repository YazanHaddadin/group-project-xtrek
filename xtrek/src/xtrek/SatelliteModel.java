/**
 * Satellite Class
 * <p>
 * Reads out the current coordinates of the DEVICE.
 *
 * @author Liam Vinson
 * @version Sprint 3
 */
package xtrek;

import java.io.*;
import java.util.ArrayList;

public class SatelliteModel extends ModeModel {

    private Direction latitudeDirection;
    private Direction longitudeDirection;
    private Double latitude;
    private Double longitude;

    private Boolean flag;
    private Thread thread = new Thread(new Reader());
    private ArrayList<OnGPSUpdateListener> listeners = new ArrayList<>();

    private void callListener(Double latitude, Double longitude, Direction latitudeDirection, Direction longitudeDirection) {
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

    Double getLatitude() {
        return latitude;
    }

    Double getLongitude() {
        return longitude;
    }

    String getLatitudeDirection() {
        if (latitudeDirection == null) {
            return "";
        } else {
            return latitudeDirection.getDirection();
        }
    }

    String getLongitudeDirection() {
        if (longitudeDirection == null) {
            return "";
        } else {
            return longitudeDirection.getDirection();
        }
    }

    void setListener(OnGPSUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    void plus(ButtonEvent evt) {
    }

    @Override
    void minus(ButtonEvent evt) {
    }

    @Override
    void selected(ButtonEvent evt) {
    }

    enum Direction {
        NORTH("N"), SOUTH("S"), WEST("W"), EAST("E");

        String direction;

        Direction(String direction) {
            this.direction = direction;
        }

        public String getDirection() {
            return direction;
        }
    }

    public class Reader implements Runnable {
        @Override
        public void run() {
            String line;
            BufferedReader br;
            try {
                FileInputStream in = new FileInputStream(new File(Constants.DONGLE_LOCATION));
                br = new BufferedReader(new InputStreamReader(in));
                
            } catch (IOException e) {
                e.printStackTrace();
                br = null;
            }

            try {
                while (true) {
                    if(br != null) {
                        line = br.readLine();
                    } else {
                        line = "$GPGLL,5015.74572,N,00504.57661,W,234108.00,A,A*77"; //Default to location
                    }
                    
                    if (line.startsWith("$GPGLL")) {
                        System.out.println(line);
                        String[] splits = line.split(",");
                        if (splits[1].equals("") || splits[2].equals("") || splits[3].equals("") || splits[4].equals(""))
                            ;
                        else {
                            if (splits[2].equals("N")) {
                                latitudeDirection = Direction.NORTH;
                            } else {
                                latitudeDirection = Direction.SOUTH;
                            }

                            if (splits[4].equals("E")) {
                                longitudeDirection = Direction.EAST;
                            } else {
                                longitudeDirection = Direction.WEST;
                            }

                            Double latMin = Double.parseDouble(splits[1]) % 100 / 60;
                            Double lonMin = Double.parseDouble(splits[3]) % 100 / 60;

                            Long latDeg = Math.round(Double.parseDouble(splits[1]) / 100 - latMin);
                            Long lonDeg = Math.round(Double.parseDouble(splits[3]) / 100 - lonMin);

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
    
}
