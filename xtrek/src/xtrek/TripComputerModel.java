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

    static Double lastLatitude = 0.00;
    static Double lastLongitude = 0.00;

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

    @Override
    public void onChangeDestination(String destination) {
        //Reset moving time and distance when destination is changed
        secondsCounter = 0;
        kmTravelled = 0;
    }

    @Override
    public void onGPSUpdate(Double latitude, Double longitude,
                            SatelliteModel.Direction latitudeDirection,
                            SatelliteModel.Direction longitudeDirection) {

        //Determine if the device is moving or not.
        if (Map.calculateDistance(lastLatitude, lastLongitude, latitude, longitude) > 0.005) {
            moving = true;
            lastLatitude = latitude;
            lastLongitude = longitude;
            
            double distanceTravelled = 0.00;
            distanceTravelled = Map.calculateDistance(lastLatitude, lastLongitude, latitude, longitude);
            
            kmTravelled += distanceTravelled;
            
            TripComputer.updateTripOdometer(Double.toString(kmTravelled));
            
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
