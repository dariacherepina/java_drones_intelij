package API;

import Drone.Convert;

import java.io.IOException;
import java.util.ArrayList;

import Drone.*;


public class SaveData {
    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();
    public void saveInfo(){
        try {
            helper.dataStreamIn(apiEndpoints.getDrones(), "outputDrones");
//            helper.dataStreamIn(apiEndpoints.getDroneTypes(), "outputDroneTypes");
//            helper.dataStreamIn(apiEndpoints.getDroneDynamics(), "outputDroneDynamics");
//            System.out.println(helper.dataStreamOut("outputDrones"));
//            System.out.println(helper.dataStreamOut("outputDroneTypes"));
//           System.out.println(helper.dataStreamOut("outputDroneDynamics"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
