package xtrek;

import java.util.EventListener;

/**
 * @author Sebastien Michel
 * @version Sprint 2
 */
public interface SpeechListener extends EventListener {
    void speakNextSegment(SpeechEvent evt);
}
