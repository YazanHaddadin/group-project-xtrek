package xtrek;

import java.util.EventListener;

/**
 * ButtonListener class
 * <p>
 * Listens for button presses.
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
interface ButtonListener extends EventListener {
    void selected(ButtonEvent evt);

    void plus(ButtonEvent evt);

    void minus(ButtonEvent evt);
}
