package xtrek;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 *
 * @author sebltm
 * @version Sprint1
 */
class HttpConnection {
    private byte[] response;

    /**
     *
     * @param u String of the url to connect to
     * @param requestProp a Map of the request properties in key, value pairs
     * @param body The body of the request
     */
    public HttpConnection(String u, Map<String, String> requestProp, String body) {
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            for(Map.Entry<String, String> e : requestProp.entrySet()) {
                String key = e.getKey();
                String value = e.getValue();
                conn.addRequestProperty(key, value);
            }

            conn.addRequestProperty("Content-Length", String.valueOf(body.getBytes().length));
            conn.connect();

            DataOutputStream out = new DataOutputStream (conn.getOutputStream());
            out.write(body.getBytes());
            out.flush();
            out.close();

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

    public void writeData(byte[] buffer) {
        try {
            File             file = new File( "speech.wav" );
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
