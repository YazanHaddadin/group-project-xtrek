package xtrek;

import java.util.EventListener;

/**
 * Speech Listener Class
 * 
 * @author Sebastien Michel
 * @version Sprint 3
 */
public interface SpeechListener extends EventListener {
    void speakNextSegment(SpeechEvent evt);
}
