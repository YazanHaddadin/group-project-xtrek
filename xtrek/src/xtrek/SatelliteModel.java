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
                        String [] lineArr = {"$GPGLL,5044.24749,N,00331.98208,W,153445.00,A,A*7A", "$GPGLL,5044.24644,N,00331.98234,W,153446.00,A,A*7A", "$GPGLL,5044.24576,N,00331.98366,W,153447.00,A,A*7F", "$GPGLL,5044.24524,N,00331.98481,W,153448.00,A,A*79", "$GPGLL,5044.23668,N,00331.99046,W,153642.00,A,A*73", "$GPGLL,5044.23662,N,00331.99004,W,153643.00,A,A*7E", "$GPGLL,5044.23660,N,00331.98942,W,153644.00,A,A*71", "$GPGLL,5044.23658,N,00331.98879,W,153645.00,A,A*72", "$GPGLL,5044.23657,N,00331.98857,W,153646.00,A,A*72", "$GPGLL,5044.23647,N,00331.98833,W,153647.00,A,A*70", "$GPGLL,5044.23648,N,00331.98810,W,153648.00,A,A*71", "$GPGLL,5044.23654,N,00331.98781,W,153649.00,A,A*7A", "$GPGLL,5044.23658,N,00331.98826,W,153650.00,A,A*7C", "$GPGLL,5044.23662,N,00331.98847,W,153651.00,A,A*73", "$GPGLL,5044.23645,N,00331.98778,W,153652.00,A,A*76", "$GPGLL,5044.23662,N,00331.98661,W,153653.00,A,A*7B", "$GPGLL,5044.23628,N,00331.98628,W,153654.00,A,A*7F", "$GPGLL,5044.23622,N,00331.98635,W,153655.00,A,A*78", "$GPGLL,5044.23660,N,00331.98601,W,153656.00,A,A*7A", "$GPGLL,5044.23655,N,00331.98592,W,153657.00,A,A*74", "$GPGLL,5044.23638,N,00331.98573,W,153658.00,A,A*7F", "$GPGLL,5044.23699,N,00331.98558,W,153659.00,A,A*7C", "$GPGLL,5044.23691,N,00331.98564,W,153700.00,A,A*76", "$GPGLL,5044.23669,N,00331.98527,W,153701.00,A,A*77", "$GPGLL,5044.23656,N,00331.98547,W,153702.00,A,A*7E", "$GPGLL,5044.23644,N,00331.98522,W,153703.00,A,A*7F", "$GPGLL,5044.23602,N,00331.98478,W,153704.00,A,A*74", "$GPGLL,5044.23578,N,00331.98473,W,153705.00,A,A*70", "$GPGLL,5044.23551,N,00331.98463,W,153706.00,A,A*79", "$GPGLL,5044.23529,N,00331.98467,W,153707.00,A,A*73", "$GPGLL,5044.23482,N,00331.98419,W,153708.00,A,A*75", "$GPGLL,5044.23435,N,00331.98377,W,153709.00,A,A*77", "$GPGLL,5044.23402,N,00331.98350,W,153710.00,A,A*7E", "$GPGLL,5044.23386,N,00331.98326,W,153711.00,A,A*75", "$GPGLL,5044.23360,N,00331.98342,W,153712.00,A,A*7C", "$GPGLL,5044.23353,N,00331.98352,W,153713.00,A,A*7C", "$GPGLL,5044.23325,N,00331.98342,W,153714.00,A,A*7B", "$GPGLL,5044.23302,N,00331.98305,W,153715.00,A,A*7C", "$GPGLL,5044.23269,N,00331.98278,W,153716.00,A,A*78", "$GPGLL,5044.23231,N,00331.98216,W,153717.00,A,A*7C", "$GPGLL,5044.23222,N,00331.98099,W,153718.00,A,A*74", "$GPGLL,5044.23212,N,00331.98058,W,153719.00,A,A*7B", "$GPGLL,5044.23219,N,00331.98013,W,153720.00,A,A*75", "$GPGLL,5044.23228,N,00331.97996,W,153721.00,A,A*7D", "$GPGLL,5044.23236,N,00331.97978,W,153722.00,A,A*71", "$GPGLL,5044.23246,N,00331.97959,W,153723.00,A,A*74", "$GPGLL,5044.23253,N,00331.97946,W,153724.00,A,A*79", "$GPGLL,5044.23281,N,00331.97929,W,153725.00,A,A*7E", "$GPGLL,5044.23326,N,00331.97917,W,153726.00,A,A*7C", "$GPGLL,5044.23357,N,00331.97886,W,153727.00,A,A*72", "$GPGLL,5044.23393,N,00331.97836,W,153728.00,A,A*7E", "$GPGLL,5044.23474,N,00331.97807,W,153729.00,A,A*73", "$GPGLL,5044.23544,N,00331.97825,W,153730.00,A,A*79", "$GPGLL,5044.23582,N,00331.97836,W,153731.00,A,A*70", "$GPGLL,5044.23629,N,00331.97852,W,153732.00,A,A*73", "$GPGLL,5044.23680,N,00331.97834,W,153733.00,A,A*71", "$GPGLL,5044.23706,N,00331.97866,W,153734.00,A,A*7E", "$GPGLL,5044.23730,N,00331.97896,W,153735.00,A,A*75", "$GPGLL,5044.23769,N,00331.97931,W,153736.00,A,A*76", "$GPGLL,5044.23815,N,00331.97981,W,153737.00,A,A*78", "$GPGLL,5044.23884,N,00331.98054,W,153738.00,A,A*71", "$GPGLL,5044.23935,N,00331.98135,W,153739.00,A,A*7D", "$GPGLL,5044.24011,N,00331.98167,W,153740.00,A,A*7C", "$GPGLL,5044.24077,N,00331.98182,W,153741.00,A,A*76", "$GPGLL,5044.24136,N,00331.98208,W,153742.00,A,A*70", "$GPGLL,5044.24193,N,00331.98164,W,153743.00,A,A*77"};
                        if(i < lineArr.length) {
                            line = lineArr[i++];
                        } else {
                            i = 0;
                            line = lineArr[i];
                        }
                        System.out.println(line);
                        Thread.sleep(1000);
                    }
                    
                    if (line.startsWith("$GPGLL")) {
       
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
