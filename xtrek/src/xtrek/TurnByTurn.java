/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

/**
 *
 * @author Sebastien Michel
 * @version Sprint 1
 *
 * Provides UI to choose and language for the Turn By Turn, translates the directions and speaks them out when
 * needed
 */
public class TurnByTurn extends Mode  {
    final private JButton bOff = new LangButton("Off"     , null, null, null);
    final private JButton bEng = new LangButton("English" , "en-GB", "Male",  "(en-GB, George, Apollo)");
    final private JButton bFre = new LangButton("French"  , "fr-FR", "Female","(fr-FR, Julie, Apollo)");
    final private JButton bGer = new LangButton("German"  , "de-DE", "Male",  "(de-DE, Stefan, Apollo)");
    final private JButton bIta = new LangButton("Italian" , "it-IT", "Male",  "(it-IT, Cosimo, Apollo)");
    final private JButton bJap = new LangButton("Japanese", "ja-JP", "Male",  "(ja-JP, Ichiro, Apollo)");
    final static private String KEY1 = "10d30eade54847f881f88da8da8ac8ea";
    final static private String KEY2 = "7277a9230ab04d8ea7f4ed2384077c25";
    
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
        
        private void getNextSegment(String segment) {
            String token = renewAccessToken();
            HashMap<String, String> requestProp = new HashMap<>();
            String put = requestProp.put("Content-Type", "application/ssml+xml");
            requestProp.put("X-Microsoft-OutputFormat", "riff-16khz-16bit-mono-pcm");
            requestProp.put("Authorization", "Bearer "+token);
            HttpConnection conn = new HttpConnection(
                            "https://speech.platform.bing.com/synthesize",
                            requestProp,
                            "<speak version='1.0' xml:lang='en-us'>" +
                                    "<voice xml:lang='"+this.LANGUAGE+"' " +
                                    "xml:gender='"+GENDER+"' " +
                                    "name='Microsoft Server Speech Text to Speech Voice "+NAME+"'>"
                                    +segment+
                                    "</voice></speak>");

            byte[] response = conn.getResponse();
            conn.writeData("speech.wav", response);
        }
        
        private String renewAccessToken() {
            HashMap<String, String> requestProp = new HashMap<String, String>() {
            };
            requestProp.put("Ocp-Apim-Subscription-Key", TurnByTurn.KEY1);
            HttpConnection conn = new HttpConnection(
                    "https://api.cognitive.microsoft.com/sts/v1.0/issueToken",
                    requestProp,
                    "");

            return new String(conn.getResponse());
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
            new Thread(() -> getNextSegment("Hello, this is a sample sentence in " + this.LANGUAGE)).start();
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
}
