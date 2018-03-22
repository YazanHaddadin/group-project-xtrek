package xtrek;

import java.util.EventListener;

/**
 * Speech Listener Class
 * 
 * @author Sebastien Michel
 * @version Sprint 3
 */
interface OnDirectionsUpdateListener extends EventListener {
    void speakNextSegment(SpeechEvent evt);
}
