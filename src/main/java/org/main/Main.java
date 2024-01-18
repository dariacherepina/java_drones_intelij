package org.main;

import API.APIEndpoints;
import API.SaveData;
import Drone.*;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    // Define constants

    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

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
            LOGGER.info("Fetching drone dynamics individual data for drone ID: " + droneId);
            LOGGER.info(apiEndpoints.getDroneDynamicsIndivData(droneId));
            Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(droneId));
            //Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
//            System.out.println(DronesListFull);

//        } catch (IOException e) {
//        throw new RuntimeException(e);

            // Aufrufen meiner Klasse Historical Analysis
            try {
                //  historische Daten in DronesListFull und cch möchte den Status der letzten 5 Minuten analysieren von Drone 85
              //  Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(85));

                ArrayList<DroneDynamics> historicalData = DronesListFull.getDroneDynamicsList();

                // Aktuelles Datum
                Date currentDate = new Date();

                // Aufruf der historischen Analyse-Funktion
                LOGGER.info("Analyzing historical data for the last 5 minutes");
                ArrayList<DroneDynamics> result = HistoricalAnalysis.analyzeHistoricalData(
                        historicalData, currentDate, 5);


                LOGGER.info("Historische Daten für die letzten 5 Minuten: " + result.toString());

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Exception in historical data analysis", e);
            }
        } catch (JsonSyntaxException e) {
            LOGGER.log(Level.SEVERE, "Problems with JsonSyntaxException in main ", e);
        } catch (JsonIOException e) {
            LOGGER.log(Level.SEVERE, "Problems with JsonIOException in main ", e);
        } catch (JSONException e) {
            LOGGER.log(Level.SEVERE, "Problems with JSONException in main ", e);
        }
    }
}



