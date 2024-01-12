package org.main;

import API.APIEndpoints;
import API.SaveData;
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
        SaveData data = new SaveData();
        data.saveInfo();


//        System.out.println(apiEndpoints.getDroneTypesIndivData(55));
//        String drone55 = apiEndpoints.getDroneTypesIndivData(55);
//        System.out.println(apiEndpoints.getDroneTypes());
//        System.out.println(apiEndpoints.getDronesIndivData(65));
//        System.out.println(apiEndpoints.getDroneDynamics());
//        System.out.println(apiEndpoints.formatJson(drone55)); // formats the string input to json
//
//
//        System.out.println(DroneTypesList);
//        System.out.println(DroneDynamicsList);
//
//        ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(apiEndpoints.getDroneTypes());
//        Object[][] DroneTypeObj= helper.ArrayList2ObjectDroneType(DroneTypesList);
//
//        int droneId = 85;
//        Drones DronesListFull = helper.Input2DronesObjectIndiv(apiEndpoints.getDronesIndivData(droneId));
//        //Object[][] data = helper.ArrayList2ObjectDronesIndiv(DronesListFull);
//        System.out.println(DronesListFull);
//


    }
    //:TODO: File stream for gui ?
}


