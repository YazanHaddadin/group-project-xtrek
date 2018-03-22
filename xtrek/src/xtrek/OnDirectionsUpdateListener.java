package xtrek;

import java.util.EventListener;

/**
 * Speech Listener Class
 * <p>
 * Event listener for when the directions are updated.
 * 
 * @author Sebastien Michel
 * @version Sprint 3
 */
interface OnDirectionsUpdateListener extends EventListener {
    void speakNextSegment(SpeechEvent evt);
}
