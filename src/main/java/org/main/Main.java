package org.main;

import API.APIEndpoints;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import java.io.*;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    // Define constants

    static Convert helper = new Convert();
    static APIEndpoints apiEndpoints = new APIEndpoints();

    public static void main(String[] args) {
        APIEndpoints droneIndivData = new APIEndpoints();
//        System.out.println(droneIndivData.getDroneTypesIndivData(55));
//        String drone55 = droneIndivData.getDroneTypesIndivData(55);
//        System.out.println(droneIndivData.getDroneTypes());
//        System.out.println(droneIndivData.getDronesIndivData(65));
//        System.out.println(droneIndivData.getDroneDynamics());
//        System.out.println(droneIndivData.formatJson(drone55)); // formats the string input to json
//        ArrayList<Drones> DronesList = helper.Input2DronesObject(droneIndivData.getDrones());
//        ArrayList<DroneDynamics> DroneDynamicsList = helper.Input2DroneDynamicsObject(droneIndivData.getDroneDynamics());
//        helper.addDroneDynamics(DronesList);
//        System.out.println(DronesList);
//        System.out.println(DronesList.get(0));
//        helper.ArrayList2ObjectDroneType(DroneTypesList);
//        System.out.println(DroneTypesList);
//        System.out.println(DroneDynamicsList);

//        ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(droneIndivData.getDroneTypes());
//        Object[][] DroneTypeObj= helper.ArrayList2ObjectDroneType(DroneTypesList);
//
        int droneId = 85;
        Drones DronesListFull = helper.Input2DronesObjectIndiv(droneIndivData.getDronesIndivData(droneId));
        //Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
        System.out.println(DronesListFull);


//        try {
//            helper.dataStreamIn(apiEndpoints.getDrones(), "outputDrones");
//            helper.dataStreamIn(apiEndpoints.getDroneTypes(), "outputDroneTypes");
//            helper.dataStreamOut("outputDroneDynamics");
//            //System.out.println(helper.dataStreamOut("outputDroneTypes"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
    //:TODO: File stream for gui ?
}


