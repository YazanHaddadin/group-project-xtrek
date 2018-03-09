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
    
    void plus(ButtonEvent evt) {
        //This button is disabled, so no code here. 
        //But this placeholder needs to be here due to the interface.
    }

    void minus(ButtonEvent evt) {
        //This button is disabled, so no code here. 
        //But this placeholder needs to be here due to the interface.
    }
    
    void selected(ButtonEvent evt) {
        //No selecting in this mode. 
        //But this placeholder needs to be here due to the interface.
    }
    
    //Class for incrementing the number of seconds the device has been moving every second.
    static class IncreaseMovingTime extends TimerTask {

        static int secondsCounter = 0;
        static int numberOfMinutes = 0;
        static int numberOfSeconds = 0;
        
        public void run() {
            secondsCounter++; 
            numberOfMinutes = secondsCounter/60;
            numberOfSeconds = secondsCounter%60;
            mtLabel = (numberOfMinutes + " min " + numberOfSeconds + " sec");
            TripComputer.updateMovingTime(mtLabel);
        }
    }
    
    //Increment timer for the journey every second
    public void increaseMovingTime() {
        Timer movingTimer = new Timer();
        movingTimer.schedule(new TripComputerModel.IncreaseMovingTime(), 0, 1000);
        
    }
    
    //Class for increasing the trip odometer every 10 seconds, to simulate movement.
    //Later, this will be based on satellite information from the device.
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
}
