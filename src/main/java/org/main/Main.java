package org.main;

import API.APIEndpoints;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;
import java.util.ArrayList;


public class Main {
    // Define constants

    static Convert helper = new Convert();

    public static void main(String[] args) {
        APIEndpoints droneIndivData = new APIEndpoints();
//        System.out.println(droneIndivData.getDroneTypesIndivData(55));
//        String drone55 = droneIndivData.getDroneTypesIndivData(55);
//        System.out.println(droneIndivData.getDroneTypes());
//        System.out.println(droneIndivData.getDronesIndivData(65));
//        System.out.println(droneIndivData.getDroneDynamics());
//        System.out.println(droneIndivData.formatJson(drone55)); // formats the string input to json

        ArrayList<Drones> DronesList = helper.Input2DronesObject(droneIndivData.getDrones());
        ArrayList<DroneTypes> DroneTypesList = helper.Input2DroneTypesObject(droneIndivData.getDroneTypes());
        ArrayList<DroneDynamics> DroneDynamicsList = helper.Input2DroneDynamicsObject(droneIndivData.getDroneDynamics());
        helper.addDroneDynamics(DronesList);
        //System.out.println(DronesList);
        System.out.println(DronesList.get(0));
        System.out.println(DroneTypesList);
        // System.out.println(DroneDynamicsList);


    }

}