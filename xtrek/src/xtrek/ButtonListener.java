package xtrek;

import java.util.EventListener;

/**
 * ButtonListener class
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
interface ButtonListener extends EventListener {
    void selected(ButtonEvent evt);

    void plus(ButtonEvent evt);

    void minus(ButtonEvent evt);
}
