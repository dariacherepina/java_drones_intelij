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
            helper.dataStreamIn(apiEndpoints.getDroneTypes(), "outputDroneTypes");
//            helper.dataStreamIn(apiEndpoints.getDroneDynamics(), "outputDroneDynamics");
            System.out.println(helper.dataStreamOut("outputDrones"));
            System.out.println(helper.dataStreamOut("outputDroneTypes"));
//            System.out.println(helper.dataStreamOut("outputDroneDynamics"));
            //           ArrayList<Drones> DronesList = helper.Input2DronesObject(helper.dataStreamOut("outputDrones"));
//            ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(helper.dataStreamOut("outputDroneTypes"));
//            ArrayList<DroneDynamics> DroneDynamicsList = helper.Input2DroneDynamicsObject(apiEndpoints.getDroneDynamics());
            //           System.out.println(DronesList);
//            System.out.println(DroneTypesList);
//            System.out.println(DroneDynamicsList);
//            helper.ArrayList2ObjectDroneType(DroneTypesList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
