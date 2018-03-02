package xtrek;

import javax.swing.*;
import java.util.ArrayList;

/**
 * @author Sebastien Michel
 * @version Sprint 2
 */
public abstract class ModeModel {
    ArrayList<LangButton> buttons = new ArrayList<>();

    abstract JButton addButton(TurnByTurn.Language language, TurnByTurn.Gender gender);

    abstract void plus(ButtonEvent evt);

    abstract void minus(ButtonEvent evt);

    abstract void selected(ButtonEvent evt);
}
