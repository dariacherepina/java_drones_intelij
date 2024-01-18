

import API.APIEndpoints;
import API.SaveData;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;


public class Main {
    // Define constants

    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();

    public static void main(String[] args) {
        try {

            SaveData data = new SaveData();
            data.saveInfo(true);

//            ArrayList<DroneDynamics> DroneDynamicsList2 = helper.Input2DroneDynamicsObject(helper.dataStreamOut("outputDroneDynamics"));


            ArrayList<Drones> DronesList = helper.initialiseDrones(helper.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(helper.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(helper.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            DronesList.getFirst().getOnlineCount();
//            System.out.println(DronesList);
//            System.out.println(DroneTypesList);
            System.out.println(helper.findDrone(DronesList, 81));
//            System.out.println(DroneDynamicsList);


//            Object[][] DroneTypeObj= helper.ArrayList2ObjectDroneType(DroneTypesList);

//            int droneId = 85;
//            System.out.println(apiEndpoints.getDroneDynamicsIndivData(droneId));
//            Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(droneId));
            //Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
//            System.out.println(DronesListFull);

//        } catch (IOException e) {
//        throw new RuntimeException(e);
    }catch (JsonSyntaxException e) {
            System.out.println("Problems with JSONException e in main ");
    } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



