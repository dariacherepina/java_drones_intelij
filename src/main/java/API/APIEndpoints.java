package API;


import com.google.gson.JsonObject;

public class APIEndpoints extends APIConnection  {
    // class for all methods to get info from endpoints
    private int countDroneTypes;
    private int countDrones;
    private int countDroneDynamics;



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
    public JsonObject getDroneDynamicsIndivData(int droneId) {

        return getResponse(droneId + "/dynamics/?format=json&limit=100&offset=0");
    }
    public JsonObject getDrones() {
        dronesResponse = getResponse("drones/?format=json");
        return dronesResponse;
    }
    public JsonObject getDroneTypes() {
        droneTypesResponse = getResponse("dronetypes/?format=json&limit=30&offset=0");
        return droneTypesResponse;
    }
    public JsonObject getDroneDynamics() {
        droneDynamicsResponse = getResponse("dronedynamics/?format=json&limit=100&offset=0");
        return  droneDynamicsResponse; // TODO: figure out limit
    }

//    public int setCountDroneTypes(){ //TODO: COUNT
//        try {
//            //System.out.println(droneTypesResponse);
//            this.countDroneTypes = getDroneTypes().get("count").getAsInt();
//            System.out.println("countDroneTypes " + countDroneTypes);
//        }catch (NullPointerException e){
//            System.out.println("count is null?????");
//        }
//
//        return getCountDroneTypes();
//    }
//    public int getCountDroneTypes() {
//        return countDroneTypes;
//    }
//    public int getSetCountDrones(){
//        this.countDrones = droneTypesResponse.get("count").getAsInt();
//        return getCountDrones();
//    }
//    public int getCountDrones() {
//        return this.countDrones;
//    }
//    public int getSetCountDroneDynamics(){
//        this.countDroneDynamics = droneDynamicsResponse.get("count").getAsInt();
//        return getCountDroneDynamics();
//    }
//    public int getCountDroneDynamics() {
//        return this.countDroneDynamics;
//    }
}