package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exception.InvalidFileNameException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class manages data streams and has a method to fetch data from specific server endpoints
 *
 * @author Daria Cherepina
 */
public class Stream {
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());

    /**
     * Writes the json response to a json file
     *
     * @param jsonObject JsonObject a response from specific server endpoint
     * @param fileName   String name of the file
     * @throws InvalidFileNameException custom exception if the name of the file is invalid
     */
    public static void dataStreamIn(JsonObject jsonObject, String fileName) throws InvalidFileNameException {
        if (!isValidFileName(fileName)) {
            throw new InvalidFileNameException("The filename provided is incorrect.");
        }
        String jsonString = new Gson().toJson(jsonObject);
        try {
            FileWriter fileWriter = new FileWriter(fileName + ".json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error writing JSON to file", e);
        }
    }

    /**
     * Reads the json response from a json file
     *
     * @param fileName String name of the file
     * @throws IOException Error reading JSON from file
     */
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

    /**
     * This method fetches data from specific server endpoints
     * and writes it to the json files
     */
    public static void fetchData() {
        int countD = APIEndpoints.getDronesUrl(25, 24).get("count").getAsInt();
        int countDT = APIEndpoints.getDroneTypesUrl(20, 19).get("count").getAsInt();
        int countDD = APIEndpoints.getDroneDynamics(36025, 36024).get("count").getAsInt();
        try {
            Stream.dataStreamIn(APIEndpoints.getDronesUrl(countD, 0), "outputDrones");
            Stream.dataStreamIn(APIEndpoints.getDroneTypesUrl(countDT, 0), "outputDroneTypes");
            Stream.dataStreamIn(APIEndpoints.getDroneDynamics(countDD, 0), "outputDroneDynamics");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The method checks whether the Name is valid
     *
     * @param fileName String
     * @return boolean whether is the name right or not
     */
    public static boolean isValidFileName(String fileName) {
        if (!Objects.equals(fileName, "outputDrones") ||
                !Objects.equals(fileName, "outputDroneTypes") ||
                !Objects.equals(fileName, "outputDroneDynamics")) {
            return true;
        } else {
            return false;
        }
    }

}