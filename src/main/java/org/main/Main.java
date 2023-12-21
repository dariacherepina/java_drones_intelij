package org.main;

import API.APIConnection;
import API.APIEndpoints;
public class Main {
    // Define constants


    public static void main(String[] args){
        APIConnection apiClient = new APIConnection();
        String dronesResponse = apiClient.getResponse("drones/?format=json&limit=20&offset=0");
        System.out.println("Drones: " + dronesResponse);
        String droneTypesResponse = apiClient.getResponse("dronetypes/?format=json&limit=20&offset=0");
        System.out.println("Drone Types: " + droneTypesResponse);
        String droneDynamicsResponse = apiClient.getResponse("dronedynamics/?format=json&limit=20&offset=0");
        System.out.println("Drone Types: " + droneDynamicsResponse);
        //apiClient.Drones2Json(dronesResponse.toString());
        //apiClient.formatJson(dronesResponse.toString());


        APIEndpoints droneIndivData = new APIEndpoints();
        System.out.println(droneIndivData.getDroneTypesIndivData(55));
        String drone55 = droneIndivData.getDroneTypesIndivData(55);
        System.out.println(droneIndivData.getDroneTypesIndivData(65));
        System.out.println(droneIndivData.getDronesIndivData(65));
        System.out.println(droneIndivData.getDroneDynamicsIndivData(65));
        System.out.println(droneIndivData.formatJson(drone55)); // formats the string input to json

    }
}

