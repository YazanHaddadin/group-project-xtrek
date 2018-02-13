/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.swing.JButton;

/**
 *
 * @author sebltm
 */
public class TurnByTurn implements Speech {
    static private String language = "en-GB";
    
    public void displayLanguages() {
        JButton bOff = new LangButton("Off"     , null);
        JButton bEng = new LangButton("English" , "en-GB");
        JButton bFre = new LangButton("French"  , "fr-FR");
        JButton bGer = new LangButton("German"  , "de-DE");
        JButton bIta = new LangButton("Italian" , "it-IT");
        JButton bJap = new LangButton("Japanese", "ja-JP");
    }
    
    class LangButton extends JButton implements SelectedListener {
        private String language;
        
        public LangButton(String display, String language) {
            super(display);
            this.language = language;
        }
        
        @Override
        public void selected() {
            TurnByTurn.language = language;
        }
        
        public String getDisplayLabel() {
            return language;
        }
    }
}
