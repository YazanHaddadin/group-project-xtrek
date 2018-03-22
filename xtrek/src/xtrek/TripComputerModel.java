package xtrek;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TripComputer Model Class
 * <p>
 * Provides the logic and methods for the Trip Computer mode of the XTrek, 
 * which will provide real time information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class TripComputerModel extends ModeModel {
    
    static String mtLabel;
    static String odoLabel;

    private static Double lastLatitude;
    private static Double lastLongitude;

    static int secondsCounter = 0;
    static int numberOfMinutes = 0;
    static int numberOfSeconds = 0;
    
    static boolean moving = false;

    static double kmTravelled = 0;
    
    void plus(ButtonEvent evt) {
        /*
         * In this mode, the plus button is disabled.
         */
    }

    void minus(ButtonEvent evt) {
        /*
         * In this mode, the minus button is disabled.
         */
    }
    
    void selected(ButtonEvent evt) {
        /*
         * In this mode, the select button is disabled.
         */
    }

    //Increment timer for the journey every second
    public void increaseMovingTime() {
        Timer movingTimer = new Timer();
        movingTimer.schedule(new TripComputerModel.IncreaseMovingTime(), 0, 1000);

    }

    void onChangeDestination(String destination) {
        //Reset moving time and distance when destination is changed
        secondsCounter = 0;
        kmTravelled = 0;
        increaseMovingTime();
    }

    void onGPSUpdate(Double latitude, Double longitude,
                            SatelliteModel.Direction latitudeDirection,
                            SatelliteModel.Direction longitudeDirection) {

        //Determine if the device is moving or not.
        
        if(latitudeDirection == SatelliteModel.Direction.SOUTH) {
            latitude = -latitude;
        }
        
        if(longitudeDirection == SatelliteModel.Direction.WEST) {
            longitude = -longitude;
        }
        
        if(lastLatitude == null) {
            lastLatitude = latitude;
        }
        
        if(lastLongitude == null) {
            lastLongitude = longitude;
        }
        
        System.out.println(Map.calculateDistance(lastLatitude, lastLongitude, latitude, longitude));
        
        if (Map.calculateDistance(lastLatitude, lastLongitude, latitude, longitude) > 0.001) {
            moving = true;

            double distanceTravelled;
            distanceTravelled = Map.calculateDistance(lastLatitude, lastLongitude, latitude, longitude);
            System.out.println(distanceTravelled);
            
            double speed = Math.round(distanceTravelled*3600*100.00)/100D;
            TripComputer.updateSpeed(Double.toString(speed));
            
            kmTravelled += distanceTravelled;
            TripComputer.updateTripOdometer(Double.toString(Math.round(kmTravelled*100.00)/100D));
 
            lastLatitude = latitude;
            lastLongitude = longitude;
            
        } else moving = false;
    }

    //Class for incrementing the number of seconds the DEVICE has been moving every second.
    static class IncreaseMovingTime extends TimerTask {
        public void run() {
            if (moving) {
                secondsCounter++;
                
                //Convert seconds to minutes and seconds.
                numberOfMinutes = secondsCounter / 60;
                numberOfSeconds = secondsCounter % 60;
                mtLabel = (numberOfMinutes + " min " + numberOfSeconds + " sec");
                TripComputer.updateMovingTime(mtLabel);
            }
        }
    }
}
