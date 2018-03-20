package xtrek;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TurnByTurn Model
 *
 * @author Sebastien Michel
 * @version Sprint 3
 */
class TurnByTurnModel extends ModeModel {
    static private AudioDataStream audioStream;
    private TurnByTurn.Gender gender = TurnByTurn.Gender.MALE;
    private TurnByTurn.Language language = TurnByTurn.Language.ENGLISH;
    private LangButton currentButton;
    private int buttonIndex = 0;
    private ArrayList<HashMap<Float, String>> data;

    String normaliseSentence(String sentence) {
        LinkedHashMap<String, String> abb = new LinkedHashMap<>();
        abb.put("Aly", "Alley");
        abb.put("Apt", "Apartment");
        abb.put("Arc", "Arcade");
        abb.put("Ave", "Avenue");
        abb.put("Bsmt", "Basement");
        abb.put("Bch", "Beach");
        abb.put("Bnd", "Bend");
        abb.put("Btm", "Bottom");
        abb.put("Blvd", "Boulevard");
        abb.put("Br", "Branch");
        abb.put("Bldg", "Building");
        abb.put("Byp", "Bypass");
        abb.put("Cp", "Camp");
        abb.put("Cswy", "Causeway");
        abb.put("Cir", "Circle");
        abb.put("Ctr", "Center");
        abb.put("Ct", "Court");
        abb.put("Cv", "Cove");
        abb.put("Crk", "Creek");
        abb.put("Xing", "Crossing");
        abb.put("Xrd", "Crossroad");
        abb.put("Dr", "Drive");
        abb.put("E", "East");
        abb.put("Expy", "Expressway");
        abb.put("Fld", "Field");
        abb.put("Fwy", "Freeway");
        abb.put("Frnt", "Front");
        abb.put("Gtwy", "Gateway");
        abb.put("Hngr", "Hangar");
        abb.put("Hbr", "Harbor");
        abb.put("Hvn", "Haven");
        abb.put("Hts", "Heights");
        abb.put("Hwy", "Highway");
        abb.put("Is", "Island");
        abb.put("Jct", "Junction");
        abb.put("Lk", "Lake");
        abb.put("Ln", "Lane");
        abb.put("Lbby", "Lobby");
        abb.put("Mdw", "Meadow");
        abb.put("Ml", "Mill");
        abb.put("Mt", "Mount");
        abb.put("Mtn", "Mountain");
        abb.put("NE", "Northeast");
        abb.put("N", "North");
        abb.put("Ofc", "Office");
        abb.put("Pky", "Parkway");
        abb.put("Pl", "Place");
        abb.put("Pln", "Plain");
        abb.put("Plz", "Plaza");
        abb.put("Pt", "Point");
        abb.put("Rnch", "Ranch");
        abb.put("Rpds", "Rapids");
        abb.put("Rdg", "Ridge");
        abb.put("Rd", "Road");
        abb.put("Rm", "Room");
        abb.put("Rte", "Route");
        abb.put("SE", "Southeast");
        abb.put("Skwy", "Skyway");
        abb.put("S", "South");
        abb.put("Spc", "Space");
        abb.put("Spg", "Spring");
        abb.put("Sq", "Square");
        abb.put("Sta", "Station");
        abb.put("Stra", "Stravenue");
        abb.put("St", "Street");
        abb.put("Ste", "Suite");
        abb.put("Smt", "Summit");
        abb.put("SW", "Southwest");
        abb.put("Ter", "Terrace");
        abb.put("Trce", "Trace");
        abb.put("Trlr", "Trailer");
        abb.put("Tpk", "Turnpike");
        abb.put("Vly", "Valley");
        abb.put("Vw", "View");
        abb.put("Vlg", "Village");
        abb.put("Wl", "Well");
        abb.put("W", "West");

        sentence = sentence.replaceAll("\\<.*?>", "");

        for (Map.Entry<String, String> entry : abb.entrySet()) {
            sentence = sentence.replaceAll("\\s(?i)" + entry.getKey() + "(\\s|\\p{Punct}|$)",
                    " " + entry.getValue().toLowerCase() + " ");
        }

        return sentence;
    }

    private String translateSegment(String segment) {
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", Constants.MICROSOFT_VOICE_API);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Translate?" +
                        "text='" + segment.replaceAll("\\s+", "%20") + "'&" +
                        "to=" + this.language.getLanguage().toLowerCase() + "&" +
                        "options=" + this.gender.getGender().toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        byte[] response = conn.getResponse();
        String xml = new String(response);

        return conn.parseXML(xml);
    }

    private byte[] downloadNextSegment(String segment) {
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", Constants.MICROSOFT_VOICE_API);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Speak?" +
                        "text=" + segment.replaceAll("\\s+", "%20") + "&" +
                        "language=" + this.language.getLanguage().toLowerCase() + "&" +
                        "options=" + this.gender.getGender().toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        return conn.getResponse();
    }

    void playAudio(String segment) {
        segment = normaliseSentence(segment);
        byte[] audio = downloadNextSegment(translateSegment(segment));

        if (AudioPlayer.player.isAlive()) {
            AudioPlayer.player.stop(audioStream);
        }
        AudioData audioData = new AudioData(audio);
        audioStream = new AudioDataStream(audioData);
        AudioPlayer.player.start(audioStream);
    }

    private void stopAudio() {
        if (AudioPlayer.player.isAlive()) AudioPlayer.player.stop(audioStream);
    }

    void selected(ButtonEvent evt) {
        if (currentButton != null && !currentButton.getLanguage().getDisplay().equals("Off")) {
            language = currentButton.getLanguage();
            gender = currentButton.getGender();
            stopAudio();
            this.playAudio("The language has been set to " + currentButton.getLanguage().getDisplay());
        } else {
            stopAudio();
            currentButton = (LangButton) buttons.get(0);
            language = currentButton.getLanguage();
            gender = currentButton.getGender();
            this.playAudio("Speech function turned off");
        }
    }

    void plus(ButtonEvent evt) {
        if (buttonIndex < buttons.size() - 1) buttonIndex++;
        else buttonIndex = 0;

        currentButton = (LangButton) buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void minus(ButtonEvent evt) {
        if (buttonIndex > 0) buttonIndex--;
        else buttonIndex = buttons.size() - 1;

        currentButton = (LangButton) buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void giveFocus(LangButton button) {
        button.giveFocus(buttons);
    }

    JButton addButton(TurnByTurn.Language language, TurnByTurn.Gender gender) {
        LangButton button = new LangButton(language, gender);
        buttons.add(button);
        return button;
    }

    class LangButton extends JButton {
        private final TurnByTurn.Gender GENDER;
        private final TurnByTurn.Language LANGUAGE;

        LangButton(TurnByTurn.Language language, TurnByTurn.Gender gender) {
            super(language.getDisplay());

            setStyle();
            this.GENDER = gender;
            this.LANGUAGE = language;
        }

        private void focusGained() {
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (isSelected()) {
                setBorder(BorderFactory.createEmptyBorder());
            } else {
                setBorder(BorderFactory.createLoweredBevelBorder());
            }
        }

        private void setStyle() {
            setOpaque(true);
            setFocusable(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setBackground(Color.WHITE);
            setFont(new Font(Constants.SYSTEM_FONT, Font.BOLD, 30));
            setHorizontalAlignment(SwingConstants.LEFT);

            setVisible(true);
        }

        TurnByTurn.Language getLanguage() {
            return LANGUAGE;
        }

        TurnByTurn.Gender getGender() {
            return GENDER;
        }

        void giveFocus(ArrayList<JButton> buttons) {
            for (JButton randButton : buttons) ((LangButton) randButton).focusLost();
            this.focusGained();
        }
    }
}
