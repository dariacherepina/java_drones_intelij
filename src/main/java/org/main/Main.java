package org.main;

import API.Stream;
import Drone.*;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main implements Sort {
    // Define constants

    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            Stream data = new Stream();
            data.saveData(false);


            ArrayList<Drones> DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            LOGGER.info(String.valueOf(DronesList.getFirst().getDroneDynamicsList().getFirst()));
            //helper.ArrayList2ObjectDroneDynamics(DronesList.getFirst().getDroneDynamicsList();

//check sort the ArrayLists by the CarriageWeight, MaximumCarriage, sortSpeed, sortStatus
//            LOGGER.info(String.valueOf(Sort.sortCarriageWeight(DronesList)));
//            LOGGER.info(String.valueOf(Sort.sortMaximumCarriage(DroneTypesList)));
//            LOGGER.info(String.valueOf(Sort.sortSpeed(DroneTypesList)));
//            LOGGER.info(String.valueOf(Sort.sortStatus(DroneDynamicsList)));


//check the refresh :TODO: threads for refresh every 5 min?
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



