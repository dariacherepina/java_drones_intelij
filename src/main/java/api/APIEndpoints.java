package api;


import com.google.gson.JsonObject;

public class APIEndpoints extends APIConnection {
    static JsonObject dronesResponse;
    static JsonObject droneTypesResponse;
    static JsonObject droneDynamicsResponse;

    /**
     * using the getResponse method with a drones-specific endpoint
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
     * using the getResponse method with a dronetypes-specific endpoint
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
     * using the getResponse method with a dronedynamics-specific endpoint
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