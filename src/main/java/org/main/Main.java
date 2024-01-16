

import API.APIEndpoints;
import API.SaveData;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;



public class Main {
    // Define constants

    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();

    public static void main(String[] args) {
        try {
//            SaveData data = new SaveData();
//            data.saveInfo();
//            ArrayList<DroneDynamics> DroneDynamicsList2 = helper.Input2DroneDynamicsObject(helper.dataStreamOut("outputDroneDynamics"));


//            ArrayList<Drones> DronesList = helper.Input2DronesObject(helper.dataStreamOut("outputDrones"));
//            ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(helper.dataStreamOut("outputDroneTypes"));
//            ArrayList<DroneDynamics> DroneDynamicsList = helper.Input2DroneDynamicsObject(helper.dataStreamOut("outputDroneDynamics"));
//            System.out.println(DronesList);
//            System.out.println(DroneTypesList);
//            System.out.println(DroneDynamicsList);


//            Object[][] DroneTypeObj= helper.ArrayList2ObjectDroneType(DroneTypesList);

            int droneId = 85;
            System.out.println(apiEndpoints.getDroneDynamicsIndivData(droneId));
            Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(droneId));
            //Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
//            System.out.println(DronesListFull);

//        } catch (IOException e) {
//        throw new RuntimeException(e);
    }catch (JsonSyntaxException e) {
            System.out.println("Problems with JSONException e in main ");
    }

    }
}



