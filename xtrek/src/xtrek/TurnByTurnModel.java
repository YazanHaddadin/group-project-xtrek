package xtrek;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import javax.swing.*;
import java.util.HashMap;

/**
 * @author Sebastien Michel
 * @version Sprint 2
 */
public class TurnByTurnModel extends ModeModel {
    static private AudioDataStream audioStream;
    private String gender;
    private String language;
    private LangButton currentButton;
    private int buttonIndex = 0;

    private String translateSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Translate?" +
                        "text='" + segment.replaceAll("\\s+", "%20") + "'&" +
                        "to=" + this.language.toLowerCase() + "&" +
                        "options=" + this.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        byte[] response = conn.getResponse();
        String xml = new String(response);

        return conn.parseXML(xml);
    }

    private byte[] downloadNextSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Speak?" +
                        "text=" + segment.replaceAll("\\s+", "%20") + "&" +
                        "language=" + this.language.toLowerCase() + "&" +
                        "options=" + this.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        return conn.getResponse();
    }

    void playAudio(String segment) {
        byte[] audio = downloadNextSegment(translateSegment(segment));

        AudioData audioData = new AudioData(audio);
        audioStream = new AudioDataStream(audioData);
        AudioPlayer.player.start(audioStream);
    }

    private void stopAudio() {
        if (AudioPlayer.player.isAlive()) AudioPlayer.player.stop(audioStream);
    }

    void selected(ButtonEvent evt) {
        if (currentButton != null && !currentButton.getLanguage().getDisplay().equals("Off")) {
            language = currentButton.getLanguage().getLanguage();
            gender = currentButton.getGender().getGender();
            stopAudio();
            this.playAudio("The language has been set to " + currentButton.getLanguage().getDisplay());
        } else {
            stopAudio();
            currentButton = buttons.get(0);
            language = currentButton.getLanguage().getLanguage();
            gender = currentButton.getGender().getGender();
            this.playAudio("Speech function turned off");
        }
    }

    void plus(ButtonEvent evt) {
        if (buttonIndex < buttons.size() - 1) buttonIndex++;
        else buttonIndex = 0;

        currentButton = buttons.get(buttonIndex);
        currentButton.giveFocus(buttons);
    }

    void minus(ButtonEvent evt) {
        if (buttonIndex > 0) buttonIndex--;
        else buttonIndex = buttons.size() - 1;

        currentButton = buttons.get(buttonIndex);
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
}
