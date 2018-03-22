package xtrek;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
public class Directions implements OnChangeDestinationListener, OnGPSUpdateListener {
    private static final HashMap<String, String> requestProperties = new HashMap<>();
    static String dest = "University of Exeter, Exeter, UK";
    private static String body;
    private OnDirectionsUpdateListener listener;
    private Route currentRoute = null;
    private Double latitude;
    private Double longitude;
    private Route.Step nextStep;


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
                    + "&key=AIzaSyCD60UxHwClSHYSCxMkhmMkluel7RZByx4";
            
            
            HttpConnection conn = new HttpConnection(url, "GET", requestProperties, body);
            
            byte[] response = conn.getResponse();

            return new String(response);
            
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        }
        
    }

    void setListener(OnDirectionsUpdateListener listener) {
        this.listener = listener;
    }

    @Override
    public void onGPSUpdate(Double latitude,
                            Double longitude,
                            SatelliteModel.Direction latitudeDirection,
                            SatelliteModel.Direction longitudeDirection) {

        this.latitude = latitude;
        if (latitudeDirection == SatelliteModel.Direction.SOUTH) {
            this.latitude = -latitude;
        }

        this.longitude = longitude;
        if (longitudeDirection == SatelliteModel.Direction.WEST) {
            this.longitude = -longitude;
        }

        if (currentRoute != null && listener != null && this.longitude != null && this.latitude != null) {

            System.out.println(Map.calculateDistance(nextStep.getStart_lat(), 
                    nextStep.getStart_lon(), this.latitude, this.longitude));
            
            if (nextStep.getStart_lat() != null && nextStep.getStart_lon() != null) {
                Double nextLat = nextStep.getStart_lat();
                Double nextLong = nextStep.getStart_lon();
                if (Map.calculateDistance(nextLat, nextLong, this.latitude, this.longitude) < Constants.GPS_TOLERANCE) {
                    SpeechEvent evt = new SpeechEvent(this, nextStep.getInstructions());
                    System.out.println(nextStep.getInstructions());
                    listener.speakNextSegment(evt);
                    
                    if (currentRoute != null) nextStep = currentRoute.getNextStep();
                }
            }
        }
    }

    @Override
    public void onChangeDestination(String destination) {
        String queryToMake = latitude + "," + longitude;
        //Default values, will be used if not overriden in the method call.
        currentRoute = new Route(getDirections(queryToMake, destination));
        nextStep = currentRoute.getNextStep();
    }

    class Route {
        private int i = 0;
        private final ArrayList<Step> steps = new ArrayList<>();
        private final ArrayList<String> warnings = new ArrayList<>();

        Route(String data) {
            try {
                System.out.println(data);

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
                    Double lon = (Double) startLocation.get("lng");
                    Double lat = (Double) startLocation.get("lat");
                    String instructions = (String) next_json.get("html_instructions");

                    Step next_step = new Step(lon, lat, instructions);
                    Route.this.steps.add(next_step);
                    System.out.println(instructions);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<String> getWarnings() {
            return warnings;
        }

        Step getNextStep() {
            Step step;
            if (i < steps.size() - 1) {
                return steps.get(i++);
            } else {
                step = steps.get(i);
                currentRoute = null;
            }

            return step;
        }

        class Step {
            private final Double start_lon;
            private final Double start_lat;
            private final String instructions;

            Step(Double lon, Double lat, String instructions) {
                this.start_lon = lon;
                this.start_lat = lat;
                this.instructions = instructions;
            }

            Double getStart_lat() {
                return start_lat;
            }

            Double getStart_lon() {
                return start_lon;
            }

            String getInstructions() {
                return instructions;
            }
        }
    }
}
