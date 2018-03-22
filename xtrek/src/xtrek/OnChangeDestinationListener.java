package xtrek;

import java.util.EventListener;

/**
 * OnChangeDestination Listener Interface
 * <p>
 * Event listener for when the destination of the device is changes in Where To
 * mode.
 * 
 * @author Caleb Blackmore
 * @version Sprint 3
 */
interface OnChangeDestinationListener extends EventListener {
    void onChangeDestination(String destination);
}
