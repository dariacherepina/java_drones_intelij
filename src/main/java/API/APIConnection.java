package API;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.MalformedJsonException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;


import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIConnection {
    private final String USER_AGENT = "Mozilla Firefox Awesome version";
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    // private static final String START_URL = "https://dronesim.facets-labs.com/api/";
    private final String TOKEN = "Token 1586b43740b3c8b3686b31e2dc1cf1b4273b838f";


    // Adjusted the variable to be non-static
    private HttpURLConnection connection;

    public APIConnection() {
    }

    //public JsonObject getResponse(String endpoint) { // TODO: PAGINATION: figue out how to do pagination without getHeaderField?

    public JsonObject getResponse(String endpoint) {
        String nextPageUrl = "http://dronesim.facets-labs.com/api/" + endpoint;
        String nextPageLink = null;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        int retries = 3;

        // Method 1: java.net.HttpURLConnection (getting response from remote server)
        while (nextPageUrl != null) {
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
                LOGGER.info(STR."Response code \{status}");

                // Response from the endpoint
                // Handle both unsuccessful and successful responses

                reader = new BufferedReader(new InputStreamReader(
                        status > 299 ? connection.getErrorStream() : connection.getInputStream()));


                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                    responseContent.append("\n");

 //                   try {
 //                       nextPageLink = pagination(line);
 //                   }catch (JSONException e){
 //                       LOGGER.log(Level.SEVERE,"JSONException e in APIConnection",e);
 //                 }

                }
                reader.close();

//                System.out.println("nextPageLink" + nextPageLink);
                if (nextPageLink == null || nextPageLink.equals("null")) {

                    nextPageUrl = null;
                } else {
                    nextPageUrl = nextPageLink;
                }

            } catch (SocketTimeoutException e) {
                //Handle SocketTimeoutException with retries
                if (retries > 0) {
                    LOGGER.log(Level.SEVERE,"Socket timeout occurred. Retrying...",e);
                    retries--;
                } else {
                    LOGGER.log(Level.SEVERE,"Socket timeout occurred. Max retries reached. Giving up...",e);

                    //break;
                }
            } catch (MalformedURLException e) {
                // Handle MaldformedURLException
                LOGGER.log(Level.SEVERE,"MalformedURLException", e);
            } catch (IOException e) {
                //Handle IOException
                LOGGER.log(Level.SEVERE,"IOException",e);
            }
        }
        if (connection != null) {
            connection.disconnect();
            LOGGER.info("connection disconnected") ;
        }


//        String responseContentStr = fixJson(responseContent.toString());
//        JsonObject inputJson = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
//       JsonElement inputJson = JsonParser.parseString(responseContentStr);
        JsonElement inputJson = JsonParser.parseString(responseContent.toString());


        return inputJson.getAsJsonObject();
    }

//    public String pagination (String line){
//        try {
//            JSONObject jsonObject = new JSONObject(line);
//            if (jsonObject.get("next") == null || jsonObject.get("next").toString().equals("null")) {
//                LOGGER.log(Level.INFO, "next is null");
//                return null;
//            } else {
//                LOGGER.log(Level.INFO, "next is not null");
//                return jsonObject.get("next").toString();
//            }
//        }catch (JSONException e){
//            LOGGER.log(Level.SEVERE, "JSONException");
//        }catch (NullPointerException e){
//            LOGGER.log(Level.SEVERE, "NullPointerException");
//        }
//        return null;
//    }

}


// get respond neu funktion
