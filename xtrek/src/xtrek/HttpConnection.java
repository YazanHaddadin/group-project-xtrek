package xtrek;

import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * HttpConnection Class
 * <p>
 * Provides an HTTP connection for the turnByTurn, Map and Directions classes.
 *
 * @author Sebatien Michel
 * @version Sprint 3
 */
class HttpConnection {
    private byte[] response;

    /**
     * @param u           String of the url to connect to
     * @param requestProp a Map of the request properties in key, value pairs
     * @param body        The body of the request
     */
    HttpConnection(String u, String method, Map<String, String> requestProp, String body) throws IOException {
        try {
            URL url = new URL(u);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod(method);

            conn.setDoInput(true);
            if (method.equals("GET")) conn.setDoOutput(false);
            else conn.setDoOutput(true);
            conn.setUseCaches(false);

            conn.setRequestMethod(method);

            for (Map.Entry<String, String> e : requestProp.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                conn.addRequestProperty(key, value);
            }

            //conn.addRequestProperty("Content-Length", String.valueOf(body.getBytes().length));
            conn.connect();

            if (!method.equals("GET")) {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.write(body.getBytes());
                out.flush();
                out.close();
            }

            int status = conn.getResponseCode();
            if (status >= 200 && status < 400) {
                //Get Response
                DataInputStream is = new DataInputStream(conn.getInputStream());
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                byte[] buffer = new byte[1000000]; //Maybe too much ?
                while (true) {
                    int n = is.read(buffer);
                    if (n > 0) {
                        os.write(buffer, 0, n);
                    } else {
                        break;
                    }
                }

                response = os.toByteArray();
                is.close();
                conn.disconnect();
            } else {
                System.out.println("Error : " + status);
                InputStream is = conn.getErrorStream();
                String line;
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                while ((line = bf.readLine()) != null) {
                    System.out.println(line);
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    byte[] getResponse() {
        return response;
    }

    String parseXML(String xml) {
        try {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            Document doc = db.parse(is);
            NodeList line = doc.getElementsByTagName("string");
            Element xmlString = (Element) line.item(0);
            Node child = xmlString.getFirstChild();
            return ((CharacterData) child).getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
