package api;


import com.google.gson.JsonObject;

/**
 * It provides methods for receiving data from "drones," "dronetypes," and "dronedynamics" endpoints
 *
 * @author Nisa Colak
 */
public class APIEndpoints extends APIConnection {
    static JsonObject dronesResponse;
    static JsonObject droneTypesResponse;
    static JsonObject droneDynamicsResponse;

    /**
     * Using the getResponse method with a drones-specific endpoint
     *
     * @param limit  int
     * @param offset int
     * @return JsonObject with data from  Drones
     */

    public static JsonObject getDronesUrl(int limit, int offset) {
        dronesResponse = getResponse("drones/?format=json&limit=" + limit + "&offset=" + offset);
        //dronesResponse = getResponse("drones/?format=json&limit=100&offset=0");
        return dronesResponse;
    }

    /**
     * Using the getResponse method with a dronetypes-specific endpoint
     *
     * @param limit  int
     * @param offset int
     * @return JsonObject with data from DroneType
     */
    public static JsonObject getDroneTypesUrl(int limit, int offset) {
        droneTypesResponse = getResponse("dronetypes/?format=json&limit=" + limit + "&offset=" + offset);
        return droneTypesResponse;
    }

    /**
     * Using the getResponse method with a dronedynamics-specific endpoint
     *
     * @param limit  int
     * @param offset int
     * @return JsonObject with data from DroneDynamic
     */
    public static JsonObject getDroneDynamics(int limit, int offset) {
        droneDynamicsResponse = getResponse("dronedynamics/?format=json&limit=" + limit + "&offset=" + offset);
        return droneDynamicsResponse;
    }

}