package xtrek;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
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
public class Directions implements OnChangeDestinationListener, OnGPSUpdateListener {
    //Default values, will be used if not overriden in the method call.
    static String origin = "Exeter, UK";
    static String dest = "Loughborough, UK";
    private static HashMap<String, String> requestProperties = new HashMap<>();
    private static String body;
    private OnDirectionsUpdateListener listener;
    private Route currentRoute = null;
    private Float latitude;
    private Float longitude;
    private Float prevLatitude = null;
    private Float prevLongitude = null;

    /**
      * @param origin place where you are getting directions from
      * @param dest place where you want to get directions to
     *
     * @return byte array containing the directions from the API
      */
    private static String getDirections(String origin, String dest) {
        try {
            String url = ("https://maps.googleapis.com/maps/api/directions/json"
                    + "?" + "origin" + "=" + URLEncoder.encode(origin, "UTF-8")
                    + "&" + "destination" + "=" + URLEncoder.encode(dest, "UTF-8")
                    + "&" + "region" + "=" + Constants.DIRECTIONS_REGION
                    + "&" + "mode" + "=" + Constants.TRAVEL_MODE)
                    + "key=AIzaSyCD60UxHwClSHYSCxMkhmMkluel7RZByx4";
            
            HttpConnection conn = new HttpConnection(url, "GET", requestProperties, body);
            byte[] response = conn.getResponse();

            return Arrays.toString(response);
            
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }

    public void setListener(OnDirectionsUpdateListener listener) {
        this.listener = listener;
    }

    @Override
    public void onGPSUpdate(Float latitude, Float longitude, String latitudeDirection, String longitudeDirection) {
        this.latitude = latitude;
        if (latitudeDirection.equals("S")) {
            this.latitude = -latitude;
        }

        this.longitude = longitude;
        if (longitudeDirection.equals("W")) {
            this.longitude = -longitude;
        }

        if (currentRoute != null && listener != null) {
            Float nextLat = currentRoute.getNextStep().getStart_lat();
            Float nextLong = currentRoute.getNextStep().getStart_lon();

            if (Map.calculateDistance(nextLat, nextLong, this.latitude, this.longitude) > Constants.GPS_TOLERANCE) {
                SpeechEvent evt = new SpeechEvent(this, currentRoute.getNextStep().getInstructions());
                listener.speakNextSegment(evt);
            }
        }
    }

    @Override
    public void onChangeDestination(String destination) {
        String queryToMake = latitude + "," + longitude;
        currentRoute = new Route(getDirections(queryToMake, destination));
    }

    class Route {
        private int i = 0;
        private ArrayList<Step> steps = new ArrayList<>();
        private ArrayList<String> warnings = new ArrayList<>();

        Route(String data) {
            try {
                JSONObject json = (JSONObject) new JSONParser().parse(data);

                JSONArray routes = (JSONArray) json.get("routes");
                JSONObject route = (JSONObject) routes.get(0);

                JSONArray legs = (JSONArray) route.get("legs");
                JSONObject leg = (JSONObject) legs.get(0);

                JSONArray steps = (JSONArray) leg.get("steps");

                JSONArray warnings = (JSONArray) route.get("warnings");

                for (Object next_warning : warnings) {
                    String warning = (String) next_warning;
                    Route.this.warnings.add(warning);
                }

                for (Object step : steps) {
                    JSONObject next_json = (JSONObject) step;
                    JSONObject startLocation = (JSONObject) next_json.get("start_location");
                    Float lon = (Float) startLocation.get("lon");
                    Float lat = (Float) startLocation.get("lat");
                    String instructions = (String) next_json.get("html_instructions");

                    Step next_step = new Step(lon, lat, instructions);
                    Route.this.steps.add(next_step);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<String> getWarnings() {
            return warnings;
        }

        Step getNextStep() {
            return steps.get(i++);
        }

        class Step {
            private Float start_lon;
            private Float start_lat;
            private String instructions;

            Step(Float lon, Float lat, String instructions) {
                this.start_lon = lon;
                this.start_lat = lat;
                this.instructions = instructions;
            }

            Float getStart_lat() {
                return start_lat;
            }

            Float getStart_lon() {
                return start_lon;
            }

            String getInstructions() {
                return instructions;
            }
        }
    }
}
