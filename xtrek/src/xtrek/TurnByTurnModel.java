package xtrek;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TurnByTurnModel extends ModeModel {
    static private String gender;
    static private String language;

    static private AudioData audioData;
    static private AudioDataStream audioStream;

    static String translateSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Translate?" +
                        "text='" + segment.replaceAll("\\s+", "%20") + "'&" +
                        "to=" + TurnByTurnModel.language.toLowerCase() + "&" +
                        "options=" + TurnByTurnModel.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        byte[] response = conn.getResponse();
        String xml = new String(response);

        String translatedString = conn.parseXML(xml);
        return translatedString;
    }

    static byte[] downloadNextSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Speak?" +
                        "text=" + segment.replaceAll("\\s+", "%20") + "&" +
                        "language=" + TurnByTurnModel.language.toLowerCase() + "&" +
                        "options=" + TurnByTurnModel.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        return conn.getResponse();
    }

    void playAudio(String segment) {
        byte[] audio = TurnByTurnModel.downloadNextSegment(TurnByTurnModel.translateSegment(segment));

        audioData = new AudioData(audio);
        audioStream = new AudioDataStream(audioData);
        AudioPlayer.player.start(audioStream);
    }

    void stopAudio() {
        if (AudioPlayer.player.isAlive()) {
            AudioPlayer.player.stop(audioStream);
        }
    }

    void setLanguage(String language) {
        TurnByTurnModel.language = language;
    }

    void setGender(String gender) {
        TurnByTurnModel.gender = gender;
    }

    String getGender() {
        return gender;
    }

    String getLanguage() {
        return language;
    }

    void selected(LangButton currentButton) {
        System.out.println("SELECTED");
        if(gender != null) {
            language = currentButton.getLanguage().getLanguage();
            gender = currentButton.getGender().getGender();
            this.playAudio("The language has been set to " + currentButton.getLanguage().getDisplay());
        }
    }

    int plus(int buttonIndex, ArrayList<JButton> buttons) {
        System.out.println("PLUS");
        if(buttonIndex < buttons.size()) {
            buttonIndex++;

        } else {
            buttonIndex = 0;
        }

        return buttonIndex;
    }

    int minus(int buttonIndex, ArrayList<JButton> buttons) {
        System.out.println("MINUS");
        if(buttonIndex > 0) {
            buttonIndex--;
        } else {
            buttonIndex = buttons.size();
        }

        return buttonIndex;
    }

    JButton addButton(TurnByTurn.Language language, TurnByTurn.Gender gender, TurnByTurn controller) {
        return new LangButton(language, gender, controller);
    }
}
