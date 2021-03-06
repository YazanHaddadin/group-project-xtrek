package xtrek;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
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
    private OnDirectionsUpdateListener listener;
    private Route currentRoute = null;
    private boolean routeSet = false;
    private Double latitude;
    private Double longitude;
    private Route.Step nextStep;
    private Double prevDistance;
    private String destination;

    /**
     * @param origin place where you are getting directions from
     * @param dest   place where you want to get directions to
     * @return byte array containing the directions from the API
     */
    private static String getDirections(String origin, String dest) {
        if (dest.isEmpty()) {
            return "";
        }

        try {
            String url = ("https://maps.googleapis.com/maps/api/directions/json"
                    + "?" + "origin" + "=" + URLEncoder.encode(origin, "UTF-8")
                    + "&" + "destination" + "=" + URLEncoder.encode(dest, "UTF-8")
                    + "&" + "region" + "=" + Constants.DIRECTIONS_REGION
                    + "&" + "mode" + "=" + Constants.TRAVEL_MODE)
                    + "&key=AIzaSyCD60UxHwClSHYSCxMkhmMkluel7RZByx4";


            HttpConnection conn = new HttpConnection(url, "GET", requestProperties, "");

            byte[] response = conn.getResponse();

            return new String(response);

        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return "";
        } catch (IOException e) {
            return "";
        }

    }

    void setListener(OnDirectionsUpdateListener listener) {
        this.listener = listener;
    }

    /**
     * @param latitude
     * @param longitude
     * @param latitudeDirection
     * @param longitudeDirection Set the sign on latitude and longitude
     *                           If there is a current route and GPS signal, check how far away we
     *                           are to the next step in the route and if close enough, play the
     *                           instructions and get the next step
     *                           <p>
     *                           If we are >500m off course, get a new route from the current location
     */
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

        if (currentRoute != null && listener != null && this.longitude != null && this.latitude != null && routeSet) {
            if (nextStep.getStart_lat() != null && nextStep.getStart_lon() != null) {
                Double nextLat = nextStep.getStart_lat();
                Double nextLong = nextStep.getStart_lon();

                Double nextDistance = Map.calculateDistance(nextLat, nextLong, this.latitude, this.longitude);

                if (prevDistance != null && (prevDistance - nextDistance) > 0.5) {
                    routeSet = true;
                    String queryToMake = latitude + "," + longitude;
                    //Default values, will be used if not overriden in the method call.
                    currentRoute = new Route(getDirections(queryToMake, destination));
                    nextStep = currentRoute.getNextStep();
                }

                if (nextDistance < Constants.GPS_TOLERANCE) {
                    SpeechEvent evt = new SpeechEvent(this, nextStep.getInstructions());
                    listener.speakNextSegment(evt);

                    if (currentRoute != null) nextStep = currentRoute.getNextStep();
                }

                prevDistance = nextDistance;
            }
        } else if (routeSet && currentRoute == null) {
            SpeechEvent evt = new SpeechEvent(this, "");
            listener.speakNextSegment(evt);
        }
    }

    /**
     * @param destination if there a new destination, create a new route from current location
     *                    to destination
     */
    @Override
    public void onChangeDestination(String destination) {
        this.destination = destination;
        String queryToMake = latitude + "," + longitude;
        if (!destination.isEmpty()) {
            routeSet = true;
            currentRoute = new Route(getDirections(queryToMake, destination));

            if (currentRoute.steps.isEmpty()) {
                routeSet = false;
                currentRoute = null;
                return;
            }

            nextStep = currentRoute.getNextStep();
        }
    }

    class Route {
        private final ArrayList<Step> steps = new ArrayList<>();
        private final ArrayList<String> warnings = new ArrayList<>();
        private int i = 0;

        /**
         * @param data Parse through JSON direction data, or say "no route available" in
         *             case of an error
         */
        Route(String data) {
            try {
                JSONObject json = (JSONObject) new JSONParser().parse(data);

                JSONArray routes = (JSONArray) json.get("routes");

                if (routes.isEmpty()) {
                    SpeechEvent evt = new SpeechEvent(this, "No route available");
                    listener.speakNextSegment(evt);
                    return;
                }

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
                }
            } catch (ParseException e) {
                SpeechEvent evt = new SpeechEvent(this, "No route available");
                listener.speakNextSegment(evt);
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
                routeSet = false;
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
