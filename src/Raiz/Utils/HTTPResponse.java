package Raiz.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HTTPResponse {

    public static String responseContent(String uri) throws Exception {

        final int OK = 200;
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if(responseCode == OK) {
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            BufferedReader in = new BufferedReader(reader);
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();;

            return response.toString();
        }
        return null;
    }
}
