package xtrek;

import java.util.EventObject;

/**
 * ButtonEvent Class
 * <p>
 * Event for when buttons are pressed.
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
class ButtonEvent extends EventObject {

    ButtonEvent(Object source) {
        super(source);
    }
}
