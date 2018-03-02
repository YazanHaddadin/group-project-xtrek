/**
 * ButtonListener class
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import java.util.EventListener;

interface ButtonListener extends EventListener {
    void selected(ButtonEvent evt);

    void plus(ButtonEvent evt);

    void minus(ButtonEvent evt);
}
