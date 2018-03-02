package xtrek;

import java.util.EventObject;

/**
 * @author Sebastien Michel
 * @version Sprint 2
 */
public class SpeechEvent extends EventObject {
    String speech;

    public SpeechEvent(Object source, String speech) {
        super(source);
        this.speech = speech;
    }
}
