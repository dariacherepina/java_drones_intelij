package org.main;

import API.APIEndpoints;
import API.SaveData;
import Drone.*;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Define constants

    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();
    static  HistoricalAnalysis history = new HistoricalAnalysis();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            SaveData data = new SaveData();
            //data.saveInfo(true);



            ArrayList<Drones> DronesList = helper.initialiseDrones(helper.dataStreamOut("outputDrones"));
            ArrayList<DroneTypes> DroneTypesList = helper.initialiseDroneTypes(helper.dataStreamOut("outputDroneTypes"));
            ArrayList<DroneDynamics> DroneDynamicsList = helper.initialiseDroneDynamics(helper.dataStreamOut("outputDroneDynamics"));
            helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
            DronesList.getFirst().getOnlineCount();
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
//
            // Aufrufen meiner Klasse Historical Analysis
            try {
                //  historische Daten in DronesListFull und cch möchte den Status der letzten 5 Minuten analysieren von Drone 85
              //  Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(85));

               // ArrayList<DroneDynamics> historicalData = history.analyzeHistoricalData(DroneDynamicsList, "26.12.2023 06:32");
                // Aktuelles Datum
                //Date currentDate = new Date();



                // Aufruf der historischen Analyse-Funktion
                LOGGER.info("Analyzing historical data for the last 5 minutes");
                ArrayList<DroneDynamics> result = HistoricalAnalysis.analyzeHistoricalData(
                        DroneDynamicsList,"26.12.2023 06:32" );


                LOGGER.info("Historische Daten für die letzten 5 Minuten: " + result);

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Exception in historical data analysis", e);
            }
        } catch (JsonSyntaxException e) {
            LOGGER.log(Level.SEVERE, "Problems with JsonSyntaxException in main ", e);
        } catch (JsonIOException e) {
            LOGGER.log(Level.SEVERE, "Problems with JsonIOException in main ", e);
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Problems with JSONException in main ", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



