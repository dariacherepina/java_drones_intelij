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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class APIConnection {
    private final String USER_AGENT = "Mozilla Firefox Awesome version";
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
                int responseCode = connection.getResponseCode();
                System.out.println("Response code " + responseCode);

                // Response from the endpoint
                // Handle both unsuccessful and successful responses

                reader = new BufferedReader(new InputStreamReader(
                        responseCode > 299 ? connection.getErrorStream() : connection.getInputStream()));

                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                    responseContent.append("\n");
                    try {
                        nextPageLink = pagination(line);
                    }catch (JSONException e){
                        System.out.println("ignore");
                    }
                }
                reader.close();

//                System.out.println("nextPageLink" + nextPageLink);
                if (nextPageLink == null || nextPageLink.equals("null")) {
                    nextPageUrl = null;
                } else {
                    nextPageUrl = nextPageLink;
                }

            } catch (SocketTimeoutException e) {
                if (retries > 0) {
                    System.out.println("Socket timeout occurred. Retrying...");
                    retries--;
                } else {
                    System.out.println("Socket timeout occurred. Max retries reached. Giving up...");
                    e.printStackTrace();
                    //break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            connection.disconnect();
            System.out.println("connection disconnected");
        }

//        String responseContentStr = fixJson(responseContent.toString());
//        JsonObject inputJson = JsonParser.parseString(responseContent.toString()).getAsJsonObject();
//       JsonElement inputJson = JsonParser.parseString(responseContentStr);
        JsonElement inputJson = JsonParser.parseString(responseContent.toString());


        return inputJson.getAsJsonObject();
    }
    public String pagination (String line){
        try {
            JSONObject jsonObject = new JSONObject(line);
            if (jsonObject.get("next") == null || jsonObject.get("next").toString().equals("null")) {
                return null;
            } else {
                return jsonObject.get("next").toString();
            }
        }catch (JSONException e){
            return null;
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
        }
        return null;
    }
}
