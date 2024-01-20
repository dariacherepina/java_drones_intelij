package API;


import com.google.gson.JsonObject;

public class APIEndpoints extends APIConnection {
    // class for all methods to get info from endpoints
    static JsonObject dronesResponse;
    static JsonObject droneTypesResponse;
    static JsonObject droneDynamicsResponse;

    public static JsonObject getDronesUrl(int limit, int offset) {
        dronesResponse = getResponse("drones/?format=json&limit=" + limit + "&offset=" + offset);
        //dronesResponse = getResponse("drones/?format=json&limit=100&offset=0");
        return dronesResponse;
    }

    public static JsonObject getDroneTypesUrl(int limit, int offset) {
        droneTypesResponse = getResponse("dronetypes/?format=json&limit=" + limit + "&offset=" + offset);
        return droneTypesResponse;
    }

    public static JsonObject getDroneDynamics(int limit, int offset) {
        droneDynamicsResponse = getResponse("dronedynamics/?format=json&limit=" + limit + "&offset=" + offset);
        return droneDynamicsResponse;
    }

}