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


import java.util.TimerTask;

public class TripComputerModel extends ModeModel{
    
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
            System.out.println("Moving time is currently " + numberOfMinutes + " minutes, " + numberOfSeconds + " seconds.");
        }
    }
}
