package API;



import com.google.gson.JsonObject;

public class APIEndpoints extends APIConnection  {
    // class for all methods to get info from endpoints



    //public APIEndpoints(){}
    JsonObject dronesResponse;
    JsonObject droneTypesResponse;
    JsonObject droneDynamicsResponse;


    public JsonObject getDronesIndivData(int droneId) {

        return getResponse("drones/" + droneId + "/?format=json");
    }
    public JsonObject getDroneTypesIndivData(int droneId) {

        return getResponse("dronetypes/" + droneId + "/?format=json");
    }
    public String getDroneDynamicsIndivData(int droneId) {
        return getResponse(droneId + "/dynamics/?format=json&limit=10000&offset=0");
    }
    public JsonObject getDronesUrl(int limit, int offset) {
        dronesResponse = getResponse("drones/?format=json&limit=" + limit + "&offset=" + offset);
        //dronesResponse = getResponse("drones/?format=json&limit=100&offset=0");
        return dronesResponse;
    }
    public JsonObject getDroneTypesUrl(int limit, int offset) {
        droneTypesResponse = getResponse("dronetypes/?format=json&limit=" + limit + "&offset=" + offset);
       // droneTypesResponse = getResponse("dronetypes/?format=json&limit=1000&offset=0");
        return droneTypesResponse;
    }
    public JsonObject getDroneDynamics(int limit, int offset) {
        droneDynamicsResponse = getResponse("dronedynamics/?format=json&limit=" + limit + "&offset=" + offset);
       // droneDynamicsResponse = getResponse("dronedynamics/?format=json&limit=4000000&offset=0");
        return  droneDynamicsResponse;
    }

}