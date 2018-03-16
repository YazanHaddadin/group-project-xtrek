package xtrek;

import java.util.Timer;
import java.util.TimerTask;
import java.text.DecimalFormat;

/**
 * TripComputer Model Class
 * <p>
 * Provides the logic and methods for the Trip Computer mode of the XTrek, 
 * which will provide real time information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class TripComputerModel extends ModeModel{
    
    static String mtLabel;
    static String odoLabel;
    
    static float lastLatitude = (float) 0.00;
    static float lastLongitude = (float) 0.00;
    
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
    
    //Class for incrementing the number of seconds the device has been moving every second.
    static class IncreaseMovingTime extends TimerTask {

        static int secondsCounter = 0;
        static int numberOfMinutes = 0;
        static int numberOfSeconds = 0;
        
        public void run() {
            if (determineIfMoving()) {
            secondsCounter++; 
            numberOfMinutes = secondsCounter/60;
            numberOfSeconds = secondsCounter%60;
            mtLabel = (numberOfMinutes + " min " + numberOfSeconds + " sec");
            TripComputer.updateMovingTime(mtLabel);
            }
        }
    }
    
    //Increment timer for the journey every second
    public void increaseMovingTime() {
        Timer movingTimer = new Timer();
        movingTimer.schedule(new TripComputerModel.IncreaseMovingTime(), 0, 1000);
        
    }
    
    //If the satellite coordinates are changing, moving time will be increased.
    static class IncreaseTripOdometer extends TimerTask {

        static double kmTravelled = 0;
        
        public void run() {
            kmTravelled = kmTravelled + 0.01; 
            
            //Ensure the distance is always exactly 2 decimal places in length
            DecimalFormat df = new DecimalFormat("#########0.00"); 
            odoLabel = df.format(kmTravelled) + " KM";
            
            TripComputer.updateTripOdometer(odoLabel);
        }
    }
    
    public void increaseTripOdometer() {
        Timer odoTimer = new Timer();
        odoTimer.schedule(new TripComputerModel.IncreaseTripOdometer(), 0, 10000);
    }
    
    //Determine if the device is moving or not.
    public static boolean determineIfMoving() {
        float currentLatitude = 0;
        float currentLongitude = 0;
        
        SatelliteModel sat = new SatelliteModel();
        
        try {
            currentLatitude = sat.getLatitude();
            currentLongitude = sat.getLongitude();
        } catch (Exception e) {
            /* 
             * Code for handling the exception will go here...
             */
        }
        
        
        if(currentLatitude != lastLatitude) {
            //Update last values and return true
            lastLatitude = currentLatitude;
            lastLongitude = currentLatitude;
            return true;
        }
        else if (currentLongitude != lastLongitude) {
            //Update last values and return true
            lastLatitude = currentLatitude;
            lastLongitude = currentLatitude;
            return true;
        }
        else {
            //Update last values and return false as not moving
            lastLatitude = currentLatitude;
            lastLongitude = currentLatitude;
            return false;
        }
    }
}
