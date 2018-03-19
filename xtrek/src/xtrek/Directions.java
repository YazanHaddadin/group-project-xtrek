package xtrek;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Directions Class
 * <p>
 * Connects to the Google Maps API and gets directions from one location to
 * another.
 *
 * @author Caleb Blackmore
 * @version Sprint 3
 */
public class Directions implements OnGPSUpdateListener{
    //Default values, will be used if not overriden in the method call.
    static String origin = "Exeter, UK";
    static String dest = "Loughborough, UK";
    
    static HashMap<String, String> requestProperties = new HashMap<>();
    static String body;

    /**
      * @param origin place where you are getting directions from
      * @param dest place where you want to get directions to
      * 
      * @returns byte array containing the directions from the API
      */
    static byte[] getDirections(String origin, String dest) {
        try {
            String url = ("https://maps.googleapis.com/maps/api/directions/json"
                    + "?" + "origin" + "=" + URLEncoder.encode(origin, "UTF-8")
                    + "&" + "destination" + "=" + URLEncoder.encode(dest, "UTF-8")
                    + "&" + "region" + "=" + Constants.directionsRegion
                    + "&" + "mode" + "=" + Constants.travelMode)
                    + "key=AIzaSyCD60UxHwClSHYSCxMkhmMkluel7RZByx4";
            
            HttpConnection conn = new HttpConnection(url, "GET", requestProperties, body);
            byte[] response = conn.getResponse();

            return response;
            
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex);
            return null;
        }
        
    }

    @Override
    public void onGPSUpdate(ArrayList<HashMap<Float, String>> data) {
        String latitudeQuery;
        String longitudeQuery;
        
        if(latitudeDirection == "NORTH") {
            latitudeQuery = "+";
        }
        else {
            latitudeQuery = "-";
        }
        
        latitudeQuery = latitudeQuery + Float.toString(latitude);
        
        if(longitudeDirection == "EAST") {
            longitudeQuery = "+";
        }
        else {
            longitudeQuery = "-";
        }
        
        longitudeQuery = longitudeQuery + Float.toString(longitude);
        
        String queryToMake = latitudeQuery + "," + longitudeQuery;
        
        getDirections(queryToMake, WhereTo.getCurrentDestination());
    }
}
