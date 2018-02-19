/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JFrame;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


/**
 *
 * @author Alex
 */
public class Map extends Mode {
   
  final static String OUTPUT    = "output.png";  /* Output file        */
  final static String LATITUDE  = "50.7184";     /* Inputted latitude  */
  final static String LONGITUDE = "-3.5339";     /* Inputted Longitude */
  final static String ZOOM      = "5";           /* 0 .. 21           */
  final static String SIZE      = "612x612";     /* Size              */
  
public Map(JFrame frame){
        super(frame);
        displayMode();
    }
  
  static byte[] readData( String latitude
                        , String longitude
                        , String zoom
                        , String size
                        ) {
    final String method = "GET";
    final String url
      = ( "https://maps.googleapis.com/maps/api/staticmap"
        + "?" + "center" + "=" + latitude + "," + longitude
        + "&" + "zoom"   + "=" + zoom
        + "&" + "size"   + "=" + size
        );
    
    final byte[] body
        = {};
    final String[][] headers
        = {};
    byte[] response = HttpConnect.httpConnect( method, url, headers, body );
    return response;
  }
  
  static void writeData( String file, byte[] data ) {
    try {
      OutputStream os = new FileOutputStream( file );
      os.write( data, 0, data.length );
      os.close();
    } catch ( IOException ex ) {
      ex.printStackTrace(); System.exit( 1 );
    }
  }
  
      @Override
  public void displayMode() {
      frame.setTitle("Main Menu");
      panel.setBackground(Color.BLACK);
      
      panel.validate();
      panel.setVisible(true);
  }
  
      /*Container c = getContentPane();
      setLocationRelativeTo(null);
      
      //Dimensions are in pixels, need to be mm
      setSize(new Dimension(350, 650));
      setResizable(false);
      
      setTitle("Map");
      setLayout(null);
      c.setBackground(Color.BLACK);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      
      try {
        BufferedImage mapImage = ImageIO.read(new File(OUTPUT));
        JLabel picLabel = new JLabel(new ImageIcon(mapImage));
        add(picLabel);
      }
      catch (IOException ex){
        //do whatever
      }
       
      validate();
      setVisible(true);
    }*/
  
  
  /*public static void main( String[] argv ) {
    final byte[] data = readData( LATITUDE, LONGITUDE, ZOOM, SIZE ); 
    writeData( OUTPUT, data );
    Mode mapTest = new Map();
  }*/  
}
