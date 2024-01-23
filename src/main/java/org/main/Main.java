package org.main;

import API.Stream;
import Drone.*;
import GUI.MyFrame;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main implements Sortable {
    // Define constants
    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            //initialise with neu data, even if there is none
            ArrayList<Drones> DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            //check if the count is different, to fetch neu data
            while (DronesList.getFirst().checkRefresh() || DroneTypesList.getFirst().checkRefresh() || DroneDynamicsList.getFirst().checkRefresh()) {
                Stream.fetchData();
                //initialise with neu data
//                DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
//                DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
//                DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
//                helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList); // TODO: how to do it the best, if i want to check whethere there is a new data or not and then if yes fetch it
            }

            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        new MyFrame(DronesList, DroneTypesList, DroneDynamicsList);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "IOException in MyFrame", e);
                    }
                }
            });
//            Threads threadRefresh = new Threads();
//            threadRefresh.start(); //TODO:Threads


        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Problems with JSONException in main ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



