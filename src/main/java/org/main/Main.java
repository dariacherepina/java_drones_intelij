

import API.APIEndpoints;
import API.SaveData;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;
import Drone.*;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


import java.io.IOException;
import java.util.ArrayList;

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


            //Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
//            System.out.println(DronesListFull);

//        } catch (IOException e) {
//        throw new RuntimeException(e);
    }catch (JsonSyntaxException e) {
            System.out.println("Problems with JSONException e in main ");
    } catch (IOException e) {
            throw new RuntimeException(e);
        }


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



