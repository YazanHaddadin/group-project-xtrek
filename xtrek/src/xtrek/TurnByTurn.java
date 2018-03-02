/**
 * TurnByTurn class
 * <p>
 * Provides UI to choose and language for the Turn By Turn, translates the directions and speaks them out when
 * needed
 *
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import sun.audio.AudioData;
import sun.audio.AudioDataStream;
import sun.audio.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class TurnByTurn extends Mode {
    static private String gender;
    static private String language;
    final private JButton bOff = new LangButton(Language.OFF, null);
    final private JButton bEng = new LangButton(Language.ENGLISH, Gender.MALE);
    final private JButton bFre = new LangButton(Language.FRENCH, Gender.FEMALE);
    final private JButton bGer = new LangButton(Language.GERMAN, Gender.MALE);
    final private JButton bIta = new LangButton(Language.ITALIAN, Gender.MALE);
    final private JButton bJap = new LangButton(Language.JAPANESE, Gender.MALE);

    TurnByTurn(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
    }

    public enum Language {
        OFF("Off", null),
        ENGLISH("English", "en-GB"),
        FRENCH("French", "fr-FR"),
        GERMAN("German", "de-DE"),
        ITALIAN("Italian", "it-IT"),
        JAPANESE("Japanese", "ja-JP");

        private String language;
        private String display;

        Language(String display, String language) {
            this.display = display;
            this.language = language;
        }

        public String getLanguage() {
            return language;
        }

        public String getDisplay() {
            return display;
        }
    }

    public enum Gender {
        MALE("Male"),
        FEMALE("Female");

        private String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        public String getGender() {
            return gender;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setPreferredSize(new Dimension(700, 850));
        frame.setResizable(true);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints con = new GridBagConstraints();

        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new TurnByTurn(frame);
        currentView.displayMode();
        currentView.makeVisible();

        con.gridx = 1;
        con.gridy = 1;
        con.weighty = 1.0;
        con.weightx = 1.0;
        frame.getContentPane().add(currentView.panel, con);

        frame.pack();
        frame.validate();
        frame.setVisible(true);
    }

    @Override
    public void displayMode() {

        frame.setTitle("Turn-By-Turn");

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 1.0;
        c.weighty = 1.0;

        c.gridx = 0;
        c.gridy = 0;
        panel.add(bOff, c);

        c.gridy++;
        panel.add(bEng, c);

        c.gridy++;
        panel.add(bFre, c);

        c.gridy++;
        panel.add(bGer, c);

        c.gridy++;
        panel.add(bIta, c);

        c.gridy++;
        panel.add(bJap, c);

        panel.validate();
        panel.setVisible(false);
    }

    private String translateSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Translate?" +
                        "text='" + segment.replaceAll("\\s+", "%20") + "'&" +
                        "to=" + TurnByTurn.language.toLowerCase() + "&" +
                        "options=" + TurnByTurn.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        byte[] response = conn.getResponse();
        String xml = new String(response);

        String translatedString = conn.parseXML(xml);
        return translatedString;
    }

    private byte[] downloadNextSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Speak?" +
                        "text=" + segment.replaceAll("\\s+", "%20") + "&" +
                        "language=" + TurnByTurn.language.toLowerCase() + "&" +
                        "options=" + TurnByTurn.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        return conn.getResponse();
    }

    private void playAudio(byte[] audio) {
        AudioData audioData = new AudioData(audio);
        AudioDataStream audioStream = new AudioDataStream(audioData);
        AudioPlayer.player.start(audioStream);
    }

    class LangButton extends JButton implements SelectedListener {
        private final Gender GENDER;
        private final Language LANGUAGE;
        private final SelectButton SELECT = new SelectButton();

        LangButton(Language language, Gender gender) {
            super(language.getDisplay());

            setStyle();
            this.GENDER = gender;
            this.LANGUAGE = language;

            /*THE MOUSE LISTENER WILL GET REMOVED WHEN THE BUTTONS ARE PROPERLY
            IMPLEMENTED. THIS IS ONLY FOR TESTING PURPOSE ATM
            */
            this.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LangButton.this.selected();
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    LangButton.this.focusGained();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    LangButton.this.focusLost();
                }
            });

            this.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    LangButton.this.focusGained();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    LangButton.this.focusLost();
                }
            });

            SELECT.setSelectedListener(this);
        }

        private void focusGained() {
            setBackground(Color.ORANGE);
        }

        private void focusLost() {
            setBackground(Color.WHITE);
        }

        private void setStyle() {
            setBackground(Color.WHITE);
            setBorderPainted(false);
            setFont(new Font("Arial", Font.BOLD, 36));
            setHorizontalAlignment(SwingConstants.LEFT);
        }

        @Override
        public void selected() {
            TurnByTurn.gender = this.GENDER.getGender();
            TurnByTurn.language = this.LANGUAGE.getLanguage();
            new Thread(() -> playAudio(downloadNextSegment(translateSegment("Hello, this is a sample sentence translated and spoken from English to my native language")))).start();
        }
    }
}
