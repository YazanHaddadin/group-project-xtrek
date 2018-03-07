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
        //Plus button action will go here
    }

    void minus(ButtonEvent evt) {
        //Minus button action will go here
    }
    
    void selected(ButtonEvent evt) {
       //Nothing to be done here - get rid of?
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
