package org.main;

import API.APIEndpoints;
import API.SaveData;
import Drone.*;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;


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

            // Aufrufen meiner Klasse Historical Analysis
            try {
                //  historische Daten in DronesListFull und cch möchte den Status der letzten 5 Minuten analysieren von Drone 85
              //  Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(85));

                ArrayList<DroneDynamics> historicalData = DronesListFull.getDroneDynamicsList();

                // Aktuelles Datum
                Date currentDate = new Date();

                // Aufruf der historischen Analyse-Funktion
                ArrayList<DroneDynamics> result = HistoricalAnalysis.analyzeHistoricalData(
                        historicalData, currentDate, 5);


                System.out.println("Historische Daten für die letzten 5 Minuten: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }
    }catch (JsonSyntaxException e) {
            System.out.println("Problems with JSONException e in main ");
    // Hab ich jetzt noch hinzugefügt weiß nicht ob wir diese Exceptions noch benötigen,
    } catch (JsonIOException e) {
        System.out.println("Problems with JsonIOException in main ");
    } catch (JSONException e) {
        System.out.println("Problems with JSONException in main ");
    }
    }
}



