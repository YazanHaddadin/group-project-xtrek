package xtrek;

import java.util.EventObject;

public class SpeechEvent extends EventObject {
    String speech;

    public SpeechEvent(Object source, String speech) {
        super(source);
        this.speech = speech;
    }
}
