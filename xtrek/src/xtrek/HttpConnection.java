/**
 * HttpConnection Class
 * 
 * Provides an HTTP connection for the TurnByTurn class.
 *
 * @author sebltm
 * @version Sprint1
 */

package xtrek;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Map;


class HttpConnection {
    private byte[] response;

    /**
     *
     * @param u String of the url to connect to
     * @param requestProp a Map of the request properties in key, value pairs
     * @param body The body of the request
     */
    public HttpConnection(String u, String method, Map<String, String> requestProp, String body) {
        try {
            URL url = new URL(u);
            System.out.println(u);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setRequestMethod(method);

            conn.setDoInput(true);
            if(method == "GET") conn.setDoOutput(false);
            else conn.setDoOutput(true);
            conn.setUseCaches(false);

            conn.setRequestMethod(method);

            for(Map.Entry<String, String> e : requestProp.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                conn.addRequestProperty(key, value);
            }

            //conn.addRequestProperty("Content-Length", String.valueOf(body.getBytes().length));
            conn.connect();
            System.out.println(conn.getRequestMethod());

            if(method != "GET") {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.write(body.getBytes());
                out.flush();
                out.close();
            }

            int status = conn.getResponseCode();
            if(status >= 200 && status < 400) {
                //Get Response
                DataInputStream       is = new DataInputStream(conn.getInputStream());
                ByteArrayOutputStream os = new ByteArrayOutputStream();

                byte[] buffer = new byte[1000000]; //Maybe too much ?
                while(true) {
                    int n = is.read(buffer);
                    if ( n > 0 ) {
                        os.write( buffer, 0, n );
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
                while((line = bf.readLine()) != null) {
                    System.out.println(line);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getResponse() {
        return response;
    }

    public void writeData(String fileName, byte[] buffer) {
        try {
            File             file = new File( fileName );
            FileOutputStream fos  = new FileOutputStream( file );
            DataOutputStream dos  = new DataOutputStream( fos );
            dos.write( buffer );
            dos.flush();
            dos.close();
        } catch ( Exception ex ) {
            System.exit( 1 );
        }
    }
}
