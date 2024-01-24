package org.main;

import API.Stream;
import Drone.*;
import GUI.MyFrame;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main implements Sortable {
    // Define constants
    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {// TODO: auftelung
            File file1 = new File("outputDrones.json");
            File file2 = new File("outputDroneTypes.json");
            File file3 = new File("outputDroneDynamics.json");
            ArrayList<Drones> DronesList;
            ArrayList<DroneTypes> DroneTypesList;
            ArrayList<DroneDynamics> DroneDynamicsList;
            if (file1.exists() && file1.isFile() && file1.length() > 0
                    && file2.exists() && file2.isFile() && file2.length() > 0
                    && file3.exists() && file3.isFile() && file3.length() > 0) {
                //initialise data with data from files
                DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
                DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
                DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
                helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            }else {
                Stream.fetchData();
                DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
                DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
                DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
                helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
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

        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Problems with JSONException in main ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



