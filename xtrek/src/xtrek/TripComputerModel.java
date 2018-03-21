package xtrek;

import java.text.DecimalFormat;
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
public class TripComputerModel extends ModeModel implements OnChangeDestinationListener, OnGPSUpdateListener {
    
    static String mtLabel;
    static String odoLabel;
    
    static float lastLatitude = (float) 0.00;
    static float lastLongitude = (float) 0.00;

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

    //Determine if the DEVICE is moving or not.
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

    //Increment timer for the journey every second
    public void increaseMovingTime() {
        Timer movingTimer = new Timer();
        movingTimer.schedule(new TripComputerModel.IncreaseMovingTime(), 0, 1000);

    }

    @Override
    public void onChangeDestination(String destination) {
        //Reset moving time and distance when destination is changed
        secondsCounter = 0;
        kmTravelled = 0;
    }

    @Override
    public void onGPSUpdate(Float latitude, Float longitude, String latitudeDirection, String longitudeDirection) {
        //Determine if the device is moving or not.
        moving = lastLatitude != latitude || lastLongitude != longitude;
        lastLatitude = latitude;
        lastLongitude = longitude;
    }

    //If the satellite coordinates are changing, moving time will be increased.
    static class IncreaseTripOdometer extends TimerTask {


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

    //Class for incrementing the number of seconds the DEVICE has been moving every second.
    static class IncreaseMovingTime extends TimerTask {
        public void run() {
            if (determineIfMoving()) {
                secondsCounter++;
                numberOfMinutes = secondsCounter / 60;
                numberOfSeconds = secondsCounter % 60;
                mtLabel = (numberOfMinutes + " min " + numberOfSeconds + " sec");
                TripComputer.updateMovingTime(mtLabel);
            }
        }
    }
}
