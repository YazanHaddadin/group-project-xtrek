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
                br = null;
            }
            
            int i = 0;

            try {
                while (true) {
                    if(br != null) {
                        line = br.readLine();
                        
                    } else {
                        String [] lineArr = {"$GPGLL,5044.24070,N,00331.99608,W,162540.00,A,A*74", "$GPGLL,5044.24038,N,00331.99659,W,162541.00,A,A*7D", "$GPGLL,5044.23985,N,00331.99707,W,162542.00,A,A*7C", "$GPGLL,5044.23932,N,00331.99724,W,162543.00,A,A*70", "$GPGLL,5044.23887,N,00331.99708,W,162544.00,A,A*76", "$GPGLL,5044.23828,N,00331.99736,W,162545.00,A,A*7F", "$GPGLL,5044.23784,N,00331.99743,W,162546.00,A,A*77", "$GPGLL,5044.23761,N,00331.99778,W,162547.00,A,A*75", "$GPGLL,5044.23717,N,00331.99841,W,162548.00,A,A*7E", "$GPGLL,5044.23653,N,00331.99844,W,162549.00,A,A*7B", "$GPGLL,5044.23640,N,00331.99934,W,162550.00,A,A*77", "$GPGLL,5044.23632,N,00331.99974,W,162551.00,A,A*77", "$GPGLL,5044.23637,N,00331.99979,W,162552.00,A,A*7C", "$GPGLL,5044.23671,N,00331.99889,W,162553.00,A,A*71", "$GPGLL,5044.23763,N,00331.99900,W,162554.00,A,A*74", "$GPGLL,5044.23869,N,00331.99870,W,162555.00,A,A*76", "$GPGLL,5044.23984,N,00331.99909,W,162556.00,A,A*78", "$GPGLL,5044.24021,N,00332.00008,W,162557.00,A,A*73", "$GPGLL,5044.24045,N,00332.00085,W,162558.00,A,A*7B", "$GPGLL,5044.24078,N,00332.00167,W,162559.00,A,A*79", "$GPGLL,5044.24080,N,00332.00240,W,162600.00,A,A*77", "$GPGLL,5044.24021,N,00332.00289,W,162601.00,A,A*78", "$GPGLL,5044.23984,N,00332.00281,W,162602.00,A,A*72", "$GPGLL,5044.23914,N,00332.00167,W,162603.00,A,A*71", "$GPGLL,5044.23834,N,00332.00058,W,162604.00,A,A*78", "$GPGLL,5044.23790,N,00331.99957,W,162605.00,A,A*7D", "$GPGLL,5044.23639,N,00331.99802,W,162606.00,A,A*7D", "$GPGLL,5044.23538,N,00331.99648,W,162607.00,A,A*7E", "$GPGLL,5044.23449,N,00331.99515,W,162608.00,A,A*7D", "$GPGLL,5044.23384,N,00331.99448,W,162609.00,A,A*73", "$GPGLL,5044.23406,N,00331.99412,W,162610.00,A,A*79", "$GPGLL,5044.23408,N,00331.99386,W,162611.00,A,A*7C", "$GPGLL,5044.23456,N,00331.99387,W,162612.00,A,A*75", "$GPGLL,5044.23521,N,00331.99361,W,162613.00,A,A*7D", "$GPGLL,5044.23584,N,00331.99329,W,162614.00,A,A*79", "$GPGLL,5044.23637,N,00331.99293,W,162615.00,A,A*73", "$GPGLL,5044.23681,N,00331.99202,W,162616.00,A,A*75", "$GPGLL,5044.23742,N,00331.99188,W,162617.00,A,A*7B"};
                        if(i < lineArr.length) {
                            line = lineArr[i++];
                        } else {
                            i = 0;
                            line = lineArr[i];
                        }
                        Thread.sleep(1000);
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
