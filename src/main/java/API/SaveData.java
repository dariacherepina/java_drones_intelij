package API;

import Drone.Convert;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.IOException;


public class SaveData {
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
            
            helper.dataStreamIn(apiEndpoints.getDronesUrl(100, 0), "outputDrones");
            helper.dataStreamIn(apiEndpoints.getDroneTypesUrl(100, 0), "outputDroneTypes");
            helper.dataStreamIn(apiEndpoints.getDroneDynamics(1000000, 0), "outputDroneDynamics");
            //System.out.println(helper.dataStreamOut("outputDrones"));
//            System.out.println(helper.dataStreamOut("outputDroneTypes"));
//           System.out.println(helper.dataStreamOut("outputDroneDynamics"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
