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
import Drone.*;

import javax.swing.*;

public class Main implements Sortable {
    // Define constants
    static Convert helper = new Convert();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            ArrayList<Drones> DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            System.out.println(DronesList);
            //helper.checkToInitialiseAllData(DronesList, DroneTypesList, DroneDynamicsList);

            SwingUtilities.invokeLater(new Runnable() { //runs the gui
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


