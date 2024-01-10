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

    static Convert helper = new Convert();
    public static void main(String[] args) throws MalformedURLException {
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
        ArrayList<Drones> DronesList= helper.Input2DronesObject(droneIndivData.getDrones()); //:TODO ApiEndpoint als parameter weg löschen
        //System.out.println(DronesList);
//        ArrayList<DroneTypes>  DroneTypesList= Convert.Input2DroneTypesObject(droneIndivData.getDroneTypes(), apiEndpoints);
//        System.out.println(DroneTypesList);
        ArrayList<DroneDynamics> DroneDynamicsList = Convert.Input2DroneDynamicsObject(droneIndivData.getDroneDynamics());
        System.out.println(DroneDynamicsList);
        helper.addDroneDynamics(DronesList);
        System.out.println(DronesList.get(0));



    }

}