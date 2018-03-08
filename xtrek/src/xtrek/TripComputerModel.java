/**
 * TripComputer View Class
 * <p>
 * Provides the logic and methods for the Trip Computer mode of the XTrek, 
 * which will provide real time information about the current trip.
 *
 * @author Caleb Blackmore
 * @version Sprint 2
 */
package xtrek;


import java.util.Timer;
import java.util.TimerTask;

public class TripComputerModel extends ModeModel{
    
    static String mtLabel;
    static String odoLabel;
    
    void plus(ButtonEvent evt) {
        //This button is disabled, so no code here. 
        //But this placeholder needs to be here due to interface.
    }

    void minus(ButtonEvent evt) {
        //This button is disabled, so no code here. 
        //But this placeholder needs to be here due to interface.
    }
    
    void selected(ButtonEvent evt) {
        //No selecting in this mode. 
        //But this placeholder needs to be here due to interface.
    }
    
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
    
    
    
    
    
    
    static class IncreaseTripOdometer extends TimerTask {

        static double kmTravelled = 0;
        
        public void run() {
            kmTravelled = kmTravelled + 0.01; 
            odoLabel = String.valueOf(kmTravelled) + " KM";
            TripComputer.updateTripOdometer(odoLabel);
        }
    }
    
    public void increaseTripOdometer() {
        Timer odoTimer = new Timer();
        odoTimer.schedule(new TripComputerModel.IncreaseTripOdometer(), 0, 10000);
    }
}
