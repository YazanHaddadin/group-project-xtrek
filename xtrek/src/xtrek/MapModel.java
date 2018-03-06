/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Alex Vale
 * @version Sprint 2
 */
public class MapModel extends ModeModel {
    final static String OUTPUT = "output.png";  /* Output file        */
    final static String LATITUDE = "50.7184";     /* Inputted latitude  */
    final static String LONGITUDE = "-3.5339";     /* Inputted Longitude */
    final static String ZOOM = "17";           /* 0 .. 21           */
    final static String SIZE = "612x612";     /* Size              */
    private static JLabel label;
    private Timer timer;
    
    
    //Downloads a new map every 5 sec and sets the new image
    public void updateMap() {
        timer.schedule(new MapModel.UpdateMap(), 0, 5000);
        try {
            BufferedImage myPicture = ImageIO.read(new File(OUTPUT));
            label.setIcon(new ImageIcon(myPicture));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //Downloads a static image of the map
    class UpdateMap extends TimerTask {
        @Override
        public void run() {

            System.out.println("Downloaded new image");
            HttpConnection connect = new HttpConnection("https://maps.googleapis.com/maps/api/staticmap"
                    + "?" + "center" + "=" + LATITUDE + "," + LONGITUDE
                    + "&" + "zoom" + "=" + ZOOM
                    + "&" + "size" + "=" + SIZE, "POST", new HashMap<>(), "");
            connect.writeData(OUTPUT, connect.getResponse());
        }
    }
    
    void plus(ButtonEvent evt) {
        //change zoom if + button is pressed
    }
    
    void minus(ButtonEvent evt) {
        //change zoom if - button is pressed
    }
    
    @Override
    void selected(ButtonEvent evt) {
        
    }
    
}
