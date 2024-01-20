package org.main;

import API.APIEndpoints;
import API.SaveData;
import Drone.*;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    // Define constants

    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {

            SaveData data = new SaveData();
            data.saveInfo();


            ArrayList<Drones> DronesList = helper.initialiseDrones(helper.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(helper.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(helper.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            LOGGER.info(String.valueOf(DronesList.getFirst().getDroneDynamicsList().getFirst()));
           helper.ArrayList2ObjectDroneDynamics(DronesList.getFirst().getDroneDynamicsList());


//check the refresh
//            DronesList.getFirst().refresh();
//            System.out.println(Drones.getOfflineCount());
//            System.out.println(Drones.getOnlineCount());
//            DroneTypesList.getFirst().refresh();
//            System.out.println(DroneTypes.getOfflineCount());
//            System.out.println(DroneTypes.getOnlineCount());
//            DroneDynamicsList.getFirst().refresh();
//            System.out.println(DroneDynamics.getOfflineCount());
//            System.out.println(DroneDynamics.getOnlineCount());


//check the findDrone function
//            int droneId = 85;
//            Drones DroneData= helper.findDrone(DronesList, droneId);
//            System.out.println(DroneData);

        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Problems with JSONException in main ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



