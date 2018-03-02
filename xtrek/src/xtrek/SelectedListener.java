/**
 * SelectedListener class
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import java.util.EventListener;

interface SelectedListener extends EventListener {
    void selected(SelectedEvent evt);
}
