package org.main;

import API.APIEndpoints;
import Drone.Convert;
import Drone.Drones;

import java.util.ArrayList;


public class Main {
    // Define constants


    public static void main(String[] args) {
//        APIConnection apiClient = new APIConnection();
//        String dronesResponse = apiClient.getResponse("drones/?format=json&limit=20&offset=0");
//        System.out.println("Drones: " + dronesResponse);
//        String droneTypesResponse = apiClient.getResponse("dronetypes/?format=json&limit=20&offset=0");
//        System.out.println("Drone Types: " + droneTypesResponse);
//        String droneDynamicsResponse = apiClient.getResponse("dronedynamics/?format=json&limit=20&offset=0");
//        System.out.println("Drone Types: " + droneDynamicsResponse);


        APIEndpoints droneIndivData = new APIEndpoints();
//        System.out.println(droneIndivData.getDroneTypesIndivData(55));
//        String drone55 = droneIndivData.getDroneTypesIndivData(55);
//        System.out.println(droneIndivData.getDroneTypes());
//        System.out.println(droneIndivData.getDronesIndivData(65));
//        System.out.println(droneIndivData.getDroneDynamics());
//        System.out.println(droneIndivData.formatJson(drone55)); // formats the string input to json

        ArrayList<Object> DronesList= Convert.Input2Object(droneIndivData.getDrones());
        //System.out.println(DronesList);
        ArrayList<Object> DroneTypesList = Convert.Input2Object(droneIndivData.getDroneTypes());
        //System.out.println(DroneTypesList);
        ArrayList<Object> DroneDynamicsList = Convert.Input2Object(droneIndivData.getDroneDynamics());
        System.out.println(DroneDynamicsList);

    }
}