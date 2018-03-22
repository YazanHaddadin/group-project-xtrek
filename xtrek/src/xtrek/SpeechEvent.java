package xtrek;

import java.util.EventObject;

/**
 * Speech Event Class
 * 
 * @author Sebastien Michel
 * @version Sprint 3
 */
class SpeechEvent extends EventObject {
    String speech;

    public SpeechEvent(Object source, String speech) {
        super(source);
        this.speech = speech;
    }
}
