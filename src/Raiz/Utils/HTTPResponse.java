package Raiz.Utils;

import Raiz.Core.impressaoLog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public abstract class httpResponse {

    /**
     * Método que retorna o response do endpoint informado no parâmetro uri
     */
    public static String responseContent(String uri) {

        impressaoLog.logGenerico<httpResponse> printLog = new impressaoLog.logGenerico<>();
        @SuppressWarnings("unchecked") Logger log = printLog.getLogRetorno((Class<httpResponse>) (Object) (httpResponse.class));
        StringBuilder response = new StringBuilder();
        int responseCode = 0;

        try {
            final int OK = 200;
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            responseCode = connection.getResponseCode();
            if (responseCode == OK) {
                InputStreamReader reader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader in = new BufferedReader(reader);
                //StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine.replace('\u00A0', ' '));
                }
                in.close();
                return response.toString();
            } else return responseCode + "_Status_Code";
        } catch (Exception e) {
            log.warning("[" + httpResponse.class.getSimpleName() + "] ["+ responseCode + "_Status_Code" + "]" + e.getMessage());
        }
        return null;
    }
}

