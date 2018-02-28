/**
 * TurnByTurn class
 * 
 * Provides UI to choose and language for the Turn By Turn, translates the directions and speaks them out when
 * needed
 * 
 * @author Sebastien Michel
 * @version Sprint 1
 */
package xtrek;

import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.StringReader;
import java.time.Instant;
import java.util.HashMap;

public class TurnByTurn extends Mode  {
    final private JButton bOff = new LangButton("Off"     , null, null, null);
    final private JButton bEng = new LangButton("English" , "en-GB", "Male",  "(en-GB, George, Apollo)");
    final private JButton bFre = new LangButton("French"  , "fr-FR", "Female","(fr-FR, Julie, Apollo)");
    final private JButton bGer = new LangButton("German"  , "de-DE", "Male",  "(de-DE, Stefan, Apollo)");
    final private JButton bIta = new LangButton("Italian" , "it-IT", "Male",  "(it-IT, Cosimo, Apollo)");
    final private JButton bJap = new LangButton("Japanese", "ja-JP", "Male",  "(ja-JP, Ichiro, Apollo)");

    static private String token;
    static private Instant keyExpiry;

    static private String name;
    static private String gender;
    static private String language;

    
    TurnByTurn(JFrame frame) {
        super(frame);
        panel.setLayout(new GridBagLayout());
        displayMode();
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
        panel.add(bFre,c );

        c.gridy++;
        panel.add(bGer, c);

        c.gridy++;
        panel.add(bIta, c);

        c.gridy++;
        panel.add(bJap, c);
        
        panel.validate();
        panel.setVisible(false);
    }

    class LangButton extends JButton implements SelectedListener {
        private final String NAME;
        private final String GENDER;
        private final String LANGUAGE;
        private final SelectButton SELECT = new SelectButton();
        
        LangButton(String display, String language, String gender, String name) {
            super(display);

            setStyle();
            this.NAME = name;
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
            TurnByTurn.name = this.NAME;
            TurnByTurn.gender = this.GENDER;
            TurnByTurn.language = this.LANGUAGE;
            new Thread(() -> downloadNextSegment(translateSegment("Hello, this is a sample sentence translated and spoken from English to my native language"))).start();
        }
    }

    private String translateSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Translate?" +
                        "text='"+segment.replaceAll("\\s+","%20")+"'&" +
                        "to="+TurnByTurn.language.toLowerCase()+"&" +
                        "options="+TurnByTurn.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        byte[] response = conn.getResponse();
        String xml = new String(response);

        String translatedString = parseXML(xml);
        return translatedString;
    }

    private String parseXML(String xml) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document doc = db.parse(is);
            NodeList line = doc.getElementsByTagName("string");
            Element xmlString = (Element) line.item(0);
            Node child = xmlString.getFirstChild();
            return ((CharacterData)child).getData();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    private void downloadNextSegment(String segment) {
        String key1 = "b496988cc4d34a69a1410c097a7e56ca";
        HashMap<String, String> requestProp = new HashMap<>();
        requestProp.put("Ocp-Apim-Subscription-Key", key1);
        HttpConnection conn = new HttpConnection(
                "https://api.microsofttranslator.com/V2/Http.svc/Speak?" +
                        "text="+segment.replaceAll("\\s+","%20")+"&" +
                        "language="+TurnByTurn.language.toLowerCase()+"&" +
                        "options="+TurnByTurn.gender.toLowerCase(),
                "GET",
                requestProp,
                ""
        );

        conn.writeData("speech.wav", conn.getResponse());
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
}
