package API;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIConnection {
    private static final String USER_AGENT = "Mozilla Firefox Awesome version";
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    // private static final String START_URL = "https://dronesim.facets-labs.com/api/";
    private static final String TOKEN = "Token 1586b43740b3c8b3686b31e2dc1cf1b4273b838f";


    // Adjusted the variable to be non-static
    private static HttpURLConnection connection;

    public APIConnection() {
    }



    public static JsonObject getResponse(String endpoint) {
        String nextPageUrl = "http://dronesim.facets-labs.com/api/" + endpoint;
        String nextPageLink = null;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        int retries = 3;

        try {
            // Define our URL
            URL url = new URL(nextPageUrl);

            // Opening connection
            connection = (HttpURLConnection) url.openConnection();

            // Request setup with a GET Method (fundamental part of the HTTP request)
            connection.setRequestMethod("GET");

            // Set request properties (additional details to the request)
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);

            // Connection timeout in milliseconds (not necessary)
            connection.setConnectTimeout(1000000);
            connection.setReadTimeout(1000000);

            // Getting Response code from URL


            int status = connection.getResponseCode();
            LOGGER.info("Response code " + status);

            // Response from the endpoint
            // Handle both unsuccessful and successful responses

            reader = new BufferedReader(new InputStreamReader(
                    status > 299 ? connection.getErrorStream() : connection.getInputStream()));


            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
                responseContent.append("\n");
            }
            reader.close();


        } catch (SocketTimeoutException e) {
            //Handle SocketTimeoutException with retries
            if (retries > 0) {
                LOGGER.log(Level.SEVERE, "Socket timeout occurred. Retrying...", e);
                retries--;
            } else {
                LOGGER.log(Level.SEVERE, "Socket timeout occurred. Max retries reached. Giving up...", e);
                //break;
            }
        } catch (MalformedURLException e) {
            // Handle MaldformedURLException
            LOGGER.log(Level.SEVERE, "MalformedURLException occurred", e);
        } catch (IOException e) {
            // Handle IOException
            LOGGER.log(Level.SEVERE, "IOException occurred", e);
        }

        JsonElement inputJson = JsonParser.parseString(responseContent.toString());
        return inputJson.getAsJsonObject();
    }

//    public static String pagination(String line) {
//        try {
//            JSONObject jsonObject = new JSONObject(line);
//            if (jsonObject.get("next") == null || jsonObject.get("next").toString().equals("null")) {
//                LOGGER.log(Level.INFO, "next is null");
//                return null;
//            } else {
//                LOGGER.log(Level.INFO, "next is not null");
//                return jsonObject.get("next").toString();
//            }
//        } catch (JSONException e) {
//            LOGGER.log(Level.INFO, "JSONException");
//        } catch (NullPointerException e) {
//            LOGGER.log(Level.INFO, "NullPointerException");
//        }
//        return null;
//    }

}