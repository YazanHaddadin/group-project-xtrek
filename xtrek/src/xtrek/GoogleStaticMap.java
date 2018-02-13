/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xtrek;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author sebltm
 */
public class GoogleStaticMap implements Map {
    
    private byte[] readData(float latitude, float longitude, int zoom,
            int sizeX, int sizeY) {
        final String METHOD = "GET";
        final String API    = "AIzaSyBlYfw7NDb9icLC-Ssq0EVi2d5EAjhqst0";
        int byteSize        = sizeX * sizeY * 3; //X*Y*bitDepth
        byte[] image        = new byte[byteSize];
        try {
            URL url = new URL( "https://maps.googleapis.com/maps/api/staticmap"
            + "?" + "center" + "=" + latitude + "," + longitude
            + "&" + "zoom"   + "=" + zoom
            + "&" + "size"   + "=" + sizeX + "x" + sizeY
            + "&" + "key"    + "=" + API
            );
        
            HttpURLConnection urlConnection = 
                    (HttpURLConnection) url.openConnection();
            
            image = readStream(urlConnection);
            
            urlConnection.disconnect();
        } catch(IOException e) {
            e.printStackTrace(); //Could handle this better...    
        }
        
        return image;
    }
    
    private byte[] readStream(HttpURLConnection urlConnection) {
        
        int nRead;
        byte[] data = null;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        
        try {
            InputStream in = 
                        new BufferedInputStream(urlConnection.getInputStream());
            
            while((nRead = in.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            
            buffer.flush();
        } catch(IOException e) {
            e.printStackTrace(); //Again, could handle this better
        }
        
        return buffer.toByteArray();
    }
}