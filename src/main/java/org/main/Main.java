package org.main;

import API.APIEndpoints;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
 //      System.out.println(droneIndivData.getDroneTypesIndivData(55));
//        String drone55 = droneIndivData.getDroneTypesIndivData(55);
//      System.out.println(droneIndivData.getDroneTypes());
//        System.out.println(droneIndivData.getDronesIndivData(65));
//        System.out.println(droneIndivData.getDroneDynamics());
//        System.out.println(droneIndivData.formatJson(drone55)); // formats the string input to json
        API.APIEndpoints apiEndpoints = new API.APIEndpoints();
        ArrayList<Object> DronesList= Convert.Input2Object(droneIndivData.getDrones(), apiEndpoints);
        System.out.println(DronesList);
        ArrayList<Object> DroneTypesList = Convert.Input2Object(droneIndivData.getDroneTypes(), apiEndpoints);
        System.out.println(DroneTypesList);
        //ArrayList<Object> DroneDynamicsList = Convert.Input2Object(droneIndivData.getDroneDynamics());
        //System.out.println(DroneDynamicsList);
        // trying to filter the arraylist DroneDynamicsList
//        List<DroneDynamics> filteredList = DroneDynamicsList.stream()
//                .filter(droneDynamics -> droneDynamics.getId() == specificId)
//                .collect(Collectors.toList());

    }

}