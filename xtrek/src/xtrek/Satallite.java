/**
 * Satallite Class
 *
 * Reads out the current coordinates of the device.
 *
 * @author Liam Vinson
 * @version Sprint 1
 */
package xtrek;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Satallite extends Mode {

    public Satallite(JFrame frame) {
        super(frame);
    }
    
    @Override
    public void displayMode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  final static String FILE_NAME = "/dev/cu.usbmodem1421";
  /* final static String FILE_NAME = "/dev/cu.usbmodem1441"; */
  final static int    BUFF_SIZE = 1024;

  /*
   * Reader.
   */
  private static void reader( String fileName, SatallitePanel panel ) {
    try {
      FileInputStream in = new FileInputStream( new File( fileName ) );
      
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      String line;
      
      while(true){
          line = br.readLine();
          if (line == null) {
                    Thread.sleep(500);
        } else {
              if(line.startsWith("$GPGLL")){
                panel.setLabel(line);
              }
        }
      }
    } catch ( Exception ex ) {
      System.out.println( ex ); System.exit( 1 );
    }
  }

  /*
   * OSX Ublox7 reader.
   */
  public static void main( String[] argv ) {
      JFrame frame = new JFrame();
        SatallitePanel panel = new SatallitePanel();
        frame.setSize(new Dimension(Constants.screenWidth, Constants.screenHeight));
        frame.setResizable(false);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(panel);
        
        frame.setVisible(true);
      
        reader( FILE_NAME, panel );
  }

    
}