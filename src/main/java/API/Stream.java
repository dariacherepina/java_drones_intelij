package API;

import Threads.ThreadDrone;
import Threads.ThreadDroneDynamic;
import Threads.ThreadDroneType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import Exception.InvalidFileNameException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import Exception.*;

public class Stream {
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());

    public static void dataStreamIn(JsonObject jsonObject, String fileName) throws IOException, InvalidFileNameException {
        if (!isValidFileName(fileName)) {
            throw new InvalidFileNameException("The filename provided is incorrect.");
        }//TODO: Exception

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

    public static void fetchData() {
        try {
            int countD = APIEndpoints.getDronesUrl(25, 24).get("count").getAsInt();
            Stream.dataStreamIn(APIEndpoints.getDronesUrl(countD, 0), "outputDrones");
            int countDT = APIEndpoints.getDroneTypesUrl(20, 19).get("count").getAsInt();
            Stream.dataStreamIn(APIEndpoints.getDroneTypesUrl(countDT, 0), "outputDroneTypes");
            int countDD = APIEndpoints.getDroneDynamics(36025, 36024).get("count").getAsInt();
            Stream.dataStreamIn(APIEndpoints.getDroneDynamics(countDD, 0), "outputDroneDynamics");
//            ThreadDrone threadDrone = new ThreadDrone() ;
//            Thread threadD = new Thread (threadDrone);
//            ThreadDroneType threadDroneType = new ThreadDroneType() ;
//            Thread threadDT = new Thread (threadDroneType);
//            ThreadDroneDynamic threadDroneDynamic = new ThreadDroneDynamic() ;
//            Thread threadDD = new Thread (threadDroneDynamic);
//            threadD.start();
//            threadDT.start();
//            threadDD.start();
//            threadD.join();
//            threadDT.join();
//            threadDD.join();//TODO: join?
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
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