/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import com.sun.java.accessibility.util.GUIInitializedListener;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.ContentHandler;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import javax.xml.crypto.Data;

/**
 *
 * @author sebltm
 */
public class TurnByTurn extends Mode  {
    static private String language = "en-GB";
    final private JButton bOff = new LangButton("Off"     , null, null, null);
    final private JButton bEng = new LangButton("English" , "en-GB", "Male", "George");
    final private JButton bFre = new LangButton("French"  , "fr-FR", "Female", "Julie");
    final private JButton bGer = new LangButton("German"  , "de-DE", "Male", "Stefan");
    final private JButton bIta = new LangButton("Italian" , "it-IT", "Male", "Cosimo");
    final private JButton bJap = new LangButton("Japanese", "ja-JP", "Male", "EkaterinaRUS");
    final static private String KEY1 = "10d30eade54847f881f88da8da8ac8ea";
    final static private String KEY2 = "7277a9230ab04d8ea7f4ed2384077c25";
    
    public TurnByTurn(JFrame frame) {
        super(frame);

        displayMode();
    }
    
    @Override
    public void displayMode() {

        frame.setTitle("Turn-By-Turn");
        
        bOff.setBounds(20,  10, 310, 90);
        panel.add(bOff);
        bEng.setBounds(20, 110, 310, 90); panel.add(bEng);
        bFre.setBounds(20, 210, 310, 90); panel.add(bFre);
        bGer.setBounds(20, 310, 310, 90); panel.add(bGer);
        bIta.setBounds(20, 410, 310, 90); panel.add(bIta);
        bJap.setBounds(20, 510, 310, 90); panel.add(bJap);
        
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
        
        private byte[] getNextSegment(String segment, String KEY1) {
            ByteArrayOutputStream response = new ByteArrayOutputStream();
            try {
                String token = renewAccessToken(KEY1);
                HashMap<String, String> requestProp = new HashMap<>();
                requestProp.put("Content-Type", "application/ssml+xml");
                requestProp.put("X-Microsoft-OutputFormat", "riff-16khz-16bit-mono-pcm");
                requestProp.put("Authorization", "Bearer "+token);
                HttpURLConnection conn =
                        setupConnection(
                                "https://speech.platform.bing.com/synthesize",
                                requestProp, 
                                "<speak version='1.0' xml:lang='en-us'>" +
                                        "<voice xml:lang='"+this.LANGUAGE+"' " +
                                        "xml:gender='"+GENDER+"' " +
                                        "name='Microsoft Server Speech Text to Speech Voice ("+this.LANGUAGE+", "+NAME+")'>"
                                        +segment+
                                        "</voice></speak>");

                DataInputStream is = new DataInputStream(conn.getInputStream());
                byte[] line = new byte[1000000]; //1MB
                while(true) {
                  int n = is.read(line);
                  if(n > 0) {
                      response.write(line, 0, n);
                  } else {
                      break;
                  }
                }
                is.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                //What do we do ?
            }
            
            return response.toByteArray();
        }
        
        private String renewAccessToken(String key1) {
            try {
                HashMap<String, String> requestProp = new HashMap<String, String>() {};
                requestProp.put("Ocp-Apim-Subscription-Key", key1);
                HttpsURLConnection conn = 
                        setupConnection(
                                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken",
                                requestProp, 
                                "");

                //Get Response
                InputStream is = conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuilder response = new StringBuilder();
                while((line = rd.readLine()) != null) {
                  response.append(line);
                }
                rd.close();
                conn.disconnect();
                return response.toString();
                
                //return new String( response );
            } catch (IOException e) {
                //What do we do ?
            }
            
            return null;
          }
        
        private HttpsURLConnection setupConnection(String u, Map<String, String> requestProp, String body) {
            HttpsURLConnection conn = null;
            try {
                URL url = new URL(u);
                conn = (HttpsURLConnection) url.openConnection();
                
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                
                for(Map.Entry<String, String> e : requestProp.entrySet()) {
                    String key = e.getKey();
                    String value = e.getValue();
                    System.out.println(key + " " + value);
                    conn.addRequestProperty(key, value);
                }

                System.out.println("Content is " + String.valueOf(body.getBytes().length));
                File file = new File("bodyBytes.txt");
                FileOutputStream fileos = new FileOutputStream(file);
                DataOutputStream dos = new DataOutputStream(fileos);
                dos.write(body.getBytes());
                dos.flush();
                dos.close();

                conn.addRequestProperty("Content-Length", String.valueOf(body.getBytes().length));
                conn.connect();

                DataOutputStream out = new DataOutputStream (conn.getOutputStream ());
                out.write(body.getBytes());
                out.flush();
                out.close();

                int status = conn.getResponseCode();
                if(status >= 200 && status < 400) {
                    return conn;
                } else {
                    System.out.println("Error : " + status);
                    InputStream is = conn.getErrorStream();
                    String line;
                    BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                    while((line = bf.readLine()) != null) {
                        System.out.println("WHAT");
                        System.out.println(line);
                    }
                    System.out.println("After loop");

                }

            } catch (Exception e) {
                //e.printStackTrace();
            }
            
            return conn;
        }
        
        private void writeData( byte[] buffer ) {
            try {
                File             file = new File( "speech.wav" );
                FileOutputStream fos  = new FileOutputStream( file );
                DataOutputStream dos  = new DataOutputStream( fos ); 
                dos.write( buffer );
                dos.flush();
                dos.close();
            } catch ( Exception ex ) {
                System.exit( 1 );
            }
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
            TurnByTurn.language = this.LANGUAGE;
            byte[] speech = getNextSegment("Hello, this is a sample sentence in " + this.LANGUAGE, KEY1);
            writeData(speech);
            System.out.println(this.LANGUAGE); //TEST PURPOSES
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        frame.setLocationRelativeTo(null);

        //Dimensions are in pixels, need to be mm
        frame.setSize(new Dimension(350, 650));
        frame.setResizable(true);

        frame.setLayout(null);
        c.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Mode currentView = new TurnByTurn(frame);
        currentView.displayMode();
        frame.getContentPane().add(currentView.getPanel());
        frame.pack();

        frame.validate();
        frame.setVisible(true);
    }
}
