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

    private static final String TOKEN = "Token 1586b43740b3c8b3686b31e2dc1cf1b4273b838f";

    public APIConnection() {
    }
    /**
     *
     * @param endpoint String
     * @return JsonObject from Response
     * */
    public static JsonObject getResponse(String endpoint) {
        String nextPageUrl = "http://dronesim.facets-labs.com/api/" + endpoint;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        int retries = 3;

        try {

            final HttpURLConnection connection = getHttpURLConnection(nextPageUrl);

            int status = connection.getResponseCode();
            LOGGER.info("Response code " + status);

            reader = new BufferedReader(new InputStreamReader(
                    status > 299 ? connection.getErrorStream() : connection.getInputStream()));


            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
                responseContent.append("\n");
            }
            reader.close();


        } catch (SocketTimeoutException e) {
            if (retries > 0) {
                LOGGER.log(Level.SEVERE, "Socket timeout occurred. Retrying...", e);
                retries--;
            } else {
                LOGGER.log(Level.SEVERE, "Socket timeout occurred. Max retries reached. Giving up...", e);
            }
        } catch (MalformedURLException e) {

            LOGGER.log(Level.SEVERE, "MalformedURLException occurred", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IOException occurred", e);
        }
        JsonElement inputJson = JsonParser.parseString(responseContent.toString());
        return inputJson.getAsJsonObject();
    }

    /**
     *
     * @param nextPageUrl String
     * @return connection of HttpURLConnection datatype
     * @throws IOException
     */
    private static HttpURLConnection getHttpURLConnection(String nextPageUrl) throws IOException {
        URL url = new URL(nextPageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", TOKEN);
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setConnectTimeout(1000000);
        connection.setReadTimeout(1000000);
        return connection;
    }


}