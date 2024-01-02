package API;

public class APIEndpoints extends APIConnection  {
    // class for all methods to get info from endpoints
    public APIEndpoints(){}
    String dronesResponse;
    String droneTypesResponse;
    String droneDynamicsResponse;
    public String getDronesIndivData(int droneId) {

        return getResponse("drones/" + droneId + "/?format=json");
    }
    public String getDroneTypesIndivData(int droneId) {

        return getResponse("dronetypes/" + droneId + "/?format=json");
    }
    public String getDroneDynamicsIndivData(int droneId) {

        return getResponse(droneId + "/dynamics/?format=json");
    }
    public String getDrones() {
        dronesResponse = getResponse("drones/?format=json&limit=20");
        return dronesResponse;
    }
    public String getDroneTypes() {
        droneTypesResponse = getResponse("dronetypes/?format=json&limit=20&offset=0");
        return droneTypesResponse;
    }
    public String getDroneDynamics() {
        droneDynamicsResponse = getResponse("dronedynamics/?format=json&limit=30&offset=0");
        return  droneDynamicsResponse; // TODO: figure out limit
    }
}