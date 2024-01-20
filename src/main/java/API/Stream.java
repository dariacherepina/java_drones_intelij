package API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Stream {
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());

    public static void dataStreamIn(JsonObject jsonObject, String fileName) throws IOException {

        String jsonString = new Gson().toJson(jsonObject);
        // Write the JSON string to a file
        try {
            FileWriter fileWriter = new FileWriter(fileName + ".json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing JSON to file", e);
        }
    }

    public static JsonObject dataStreamOut(String fileName) throws IOException {
        JsonObject jsonObject = null;
        try {
            FileReader fileReader = new FileReader(fileName + ".json");
            jsonObject = new Gson().fromJson(fileReader, JsonObject.class);
            fileReader.close();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading JSON from file", e);
        }
        return jsonObject;
    }

    public void saveData() {
        try {

            dataStreamIn(APIEndpoints.getDronesUrl(100, 0), "outputDrones");
            dataStreamIn(APIEndpoints.getDroneTypesUrl(100, 0), "outputDroneTypes");
            dataStreamIn(APIEndpoints.getDroneDynamics(1000000, 0), "outputDroneDynamics");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error while fetching and saving data", e);
            throw new RuntimeException(e);
        }
    }

}
