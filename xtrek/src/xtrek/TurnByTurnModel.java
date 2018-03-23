package xtrek;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TurnByTurn Model
 * Model for the turn by turn mode of the device.
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

    @Override
    void onDisplay() {
        super.onDisplay();
        buttonIndex = 0;
    }

    /**
     * @param String sentence : the sentence to normalise
     * @return String sentence : the normalised sentence
     * <p>
     * The normalisation process includes three steps:
     * 1. If the sentence contains any HTML tags, remove them while keeping
     * the contents
     * 2. If the sentence contains road abbreviations such as "Rd" or "Ln",
     * expand the abbreviations
     * 3. Add punctuation
     */
    private String normaliseSentence(String sentence) {
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

        sentence = sentence.replaceAll("(\\w)(\\p{Upper})", "$1. $2");
        sentence = sentence.replaceAll("/([AB]\\d{1,4})(\\p{Upper})", " $1. $2");

        return sentence;
    }

    /**
     * @param String segment : the normalised sentence to translate
     * @return String segment : the normalised translated sentence
     * @throws IOException This process includes sending a request to the Microsoft Translator API
     *                     which returns a translated sentence from one language to another. This
     *                     API renews its own key as needed on the server.
     */
    private String translateSegment(String segment) throws IOException {
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

    /**
     * @param String segment : The normalised, translated sentence
     * @return byte[] audio : The bytes of the audio file
     * @throws IOException Send a request to the Microsoft Translator API to speak a sentence
     *                     in a certain language. This API renews its own key as needed on the
     *                     server.
     */
    private byte[] downloadNextSegment(String segment) throws IOException {
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

    /**
     * @param resource : name of the resource
     * @return byte[] audio : bytes of the audio file
     * <p>
     * If a resource is not available (GPS/Internet), play the required file.
     */
    private byte[] noResource(String resource) {
        try {
            Path fileLocation = Paths.get(getClass().getResource("assets/" + resource).toURI());
            return Files.readAllBytes(fileLocation);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param segment : Sentence to translate and play
     *                <p>
     *                If the voice is set to off, do not do anything
     *                If the segment is empty, then there was no GPS data received,
     *                play the file "noGPS"
     *                Otherwise, normalise the sentence, translate it and play it.
     *                If that fails, most likely an Internet exception : play "noInternet"
     */
    void playAudio(String segment) {
        if (currentButton == null || currentButton.getLanguage() == TurnByTurn.Language.OFF) {
            return;
        }

        byte[] audio;
        if (segment.isEmpty()) {
            audio = noResource("noGPS.wav");
            if (AudioPlayer.player.isAlive()) {
                AudioPlayer.player.stop(audioStream);
            }
            AudioData audioData = new AudioData(audio);
            audioStream = new AudioDataStream(audioData);
            AudioPlayer.player.start(audioStream);
            return;
        }

        String normalisedSegment = normaliseSentence(segment);
        try {
            audio = downloadNextSegment(translateSegment(normalisedSegment));
        } catch (IOException e) {
            audio = noResource("noInternet.wav");
        }

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

    /**
     * @param evt If a language is selected, set the variables to reflect the choice and
     *            play an audio file in the required language saying so
     */
    void selected(ButtonEvent evt) {
        if (currentButton != null && !currentButton.getLanguage().getDisplay().equals("Off")) {
            language = currentButton.getLanguage();
            gender = currentButton.getGender();
            stopAudio();
            this.playAudio("The language has been set to " + currentButton.getLanguage().getDisplay());
        } else {
            stopAudio();
            this.playAudio("Speech function turned off");
            currentButton = (LangButton) buttons.get(0);
            language = currentButton.getLanguage();
            gender = currentButton.getGender();
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

    /**
     * An extension of JButton that include Gender/Language state
     */
    class LangButton extends JButton {
        private final TurnByTurn.Gender GENDER;
        private final TurnByTurn.Language LANGUAGE;

        LangButton(TurnByTurn.Language language, TurnByTurn.Gender gender) {
            super(language.getDisplay());

            setStyle();
            this.GENDER = gender;
            this.LANGUAGE = language;
        }

        /* Focus = Orange background*/
        private void focusGained() {
            setBackground(Color.ORANGE);
        }

        /* No focus = White background */
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

        /* Take focus away for all buttons except "this" */
        void giveFocus(ArrayList<JButton> buttons) {
            for (JButton randButton : buttons) ((LangButton) randButton).focusLost();
            this.focusGained();
        }
    }
}
