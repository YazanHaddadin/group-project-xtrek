package xtrek;

import java.util.EventListener;

public interface SpeechListener extends EventListener {
    void speakNextSegment(SpeechEvent evt);
}
