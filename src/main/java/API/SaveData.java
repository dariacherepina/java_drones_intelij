package API;

import Drone.Convert;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.IOException;

import java.util.ArrayList;

import Drone.*;
import java.util.logging.Level;
import java.util.logging.Logger;




public class SaveData {
    private static final Logger LOGGER = Logger.getLogger(SaveData.class.getName());
    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();
    public void saveInfo(boolean initial){

//    if(initial) {
//
//    }

//        try {
//            JsonObject o = helper.dataStreamOut("outputDrones");
//            o.get("count").getAsInt();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {

            helper.dataStreamIn(apiEndpoints.getDrones(), "outputDrones");
//            helper.dataStreamIn(apiEndpoints.getDroneTypes(), "outputDroneTypes");
//            helper.dataStreamIn(apiEndpoints.getDroneDynamics(), "outputDroneDynamics");
//            LOGGER.info(helper.dataStreamOut("outputDrones"));
//            LOGGER.info(helper.dataStreamOut("outputDroneTypes"));
//            LOGGER.info(helper.dataStreamOut("outputDroneDynamics"));
            LOGGER.info("Data saved successfully.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An error occurred while saving data.", e);
            throw new RuntimeException(e);
        }
    }

}
