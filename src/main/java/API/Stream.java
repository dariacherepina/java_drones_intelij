package API;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.*;

public class Stream {
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());

    /**
     *
     * @param jsonObject JsonObject
     * @param fileName String
     * @throws InvalidFileNameException custom exception
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
     *
     * @param fileName String
     * @return jsonObject
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
     * this method fetches data from the server
     */
    public static void fetchData() {
        try {
            int countD = APIEndpoints.getDronesUrl(25, 24).get("count").getAsInt();
            Stream.dataStreamIn(APIEndpoints.getDronesUrl(countD, 0), "outputDrones");
            int countDT = APIEndpoints.getDroneTypesUrl(20, 19).get("count").getAsInt();
            Stream.dataStreamIn(APIEndpoints.getDroneTypesUrl(countDT, 0), "outputDroneTypes");
            int countDD = APIEndpoints.getDroneDynamics(36025, 36024).get("count").getAsInt();
            Stream.dataStreamIn(APIEndpoints.getDroneDynamics(countDD, 0), "outputDroneDynamics");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param fileName String
     * @return boolean whether is the name right or not
     */
    public static boolean isValidFileName(String fileName){
        if(!Objects.equals(fileName, "outputDrones") ||
                !Objects.equals(fileName, "outputDroneTypes") ||
                !Objects.equals(fileName, "outputDroneDynamics")){
            return true;
        }else {
            return false;
        }
    }

}
