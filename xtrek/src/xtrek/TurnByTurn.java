/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
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
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author sebltm
 */
public class TurnByTurn extends JFrame implements Mode  {
    static private String language = "en-GB";
    final JButton bOff = new LangButton("Off"     , null, null, null);
    final JButton bEng = new LangButton("English" , "en-GB", "Male", "George");
    final JButton bFre = new LangButton("French"  , "fr-FR", "Female", "Julie");
    final JButton bGer = new LangButton("German"  , "de-DE", "Male", "Stefan");
    final JButton bIta = new LangButton("Italian" , "it-IT", "Male", "Cosimo");
    final JButton bJap = new LangButton("Japanese", "ja-JP", "Male", "EkaterinaRUS");
    final static private String KEY1 = "10d30eade54847f881f88da8da8ac8ea";
    final static private String KEY2 = "7277a9230ab04d8ea7f4ed2384077c25";
    
    public TurnByTurn() {
        displayMode();
    }
    
    @Override
    public void displayMode() {
        Container c = getContentPane();
        setLocationRelativeTo(null);
        
        //Dimensions are in pixels, need to be mm
        setSize(new Dimension(350, 650));
        setResizable(false);
        
        setTitle("Turn-by-Turn");
        setLayout(null);
        c.setBackground(Color.BLACK);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        bOff.setBounds(20,  10, 310, 90); add(bOff);
        bEng.setBounds(20, 110, 310, 90); add(bEng);
        bFre.setBounds(20, 210, 310, 90); add(bFre);
        bGer.setBounds(20, 310, 310, 90); add(bGer);
        bIta.setBounds(20, 410, 310, 90); add(bIta);
        bJap.setBounds(20, 510, 310, 90); add(bJap);
        
        validate();
        setVisible(true);
    }
    
    class LangButton extends JButton implements SelectedListener {
        private final String NAME;
        private final String GENDER;
        private final String LANGUAGE;
        private final SelectButton SELECT = new SelectButton();
        
        public LangButton(String display, String language, String gender, String name) {
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
                HashMap<String, String> requestProp = new HashMap<String, String>() {};
                requestProp.put("Content-Type", "application/ssml+xml");
                requestProp.put("X-Microsoft-OutputFormat", "riff-16khz-16bit-mono-pcm");
                requestProp.put("Authorization", "Bearer"+token);
                HttpsURLConnection conn = 
                        setupConnection(
                                "https://speech.platform.bing.com/synthesize", 
                                "POST", 
                                requestProp, 
                                "<speak version='1.0' xml:lang='en-US'><voice xml:lang='"+LANGUAGE+"' xml:gender='"+GENDER+"' name='Microsoft Server Speech Text to Speech Voice ("+LANGUAGE+", "+NAME+")'>"+segment+"</voice></speak>");
                
                DataInputStream is = new DataInputStream(conn.getInputStream());
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                byte[] line = new byte[1000000]; //1MB
                while(true) {
                  int n = is.read(line);
                  if(n > 0) {
                      response.write(line, 0, n);
                  } else {
                      break;
                  }
                }
                rd.close();
                conn.disconnect();
            } catch (IOException e) {
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
                                "POST", 
                                requestProp, 
                                "");

                //Get Response	
                InputStream is = conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer(); 
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
        
        private HttpsURLConnection setupConnection(String u, String method, Map<String, String> requestProp, String body) {
            try {
                URL url = new URL(u);
                HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                
                conn.setRequestMethod(method);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                
                for(Map.Entry<String, String> e : requestProp.entrySet()) {
                    String key = e.getKey();
                    String value = e.getValue();
                    conn.addRequestProperty(key, value);
                }
                
                DataOutputStream wr = new DataOutputStream (conn.getOutputStream ());
                wr.writeBytes(body);
                wr.flush();
                wr.close();
                
                return conn;
            } catch (IOException e) {
                //What do we do ?
            }
            
            return null;
        }
        
        private void writeData( byte[] buffer, String name ) {
            try {
                File             file = new File( name );
                FileOutputStream fos  = new FileOutputStream( file );
                DataOutputStream dos  = new DataOutputStream( fos ); 
                dos.write( buffer );
                dos.flush();
                dos.close();
            } catch ( Exception ex ) {
                System.out.println( ex ); System.exit( 1 ); return;
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
            TurnByTurn.language = language;
            byte[] speech = getNextSegment("Hello, this is a sample sentence in "+language, KEY1);
            writeData(speech, "speech.wav");
            System.out.println(language); //TEST PURPOSES
        }
        
        public String getDisplayLabel() {
            return language;
        }
    }
    
    public static void main(String[] args) {
        Mode testView = new TurnByTurn();
    }
}
