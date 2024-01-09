package Drone;

import com.google.gson.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;


public class Convert {
    public static DroneTypes constructerDroneType(DroneTypes droneTypeFromDrone, JsonObject droneTypesResponse){
        droneTypeFromDrone.setId(droneTypesResponse.get("id").getAsInt());
        droneTypeFromDrone.setManufacturer(droneTypesResponse.get("manufacturer").getAsString());
        droneTypeFromDrone.setTypename(droneTypesResponse.get("typename").getAsString());
        droneTypeFromDrone.setWeight(droneTypesResponse.get("weight").getAsInt());
        droneTypeFromDrone.setMaximumSpeed(droneTypesResponse.get("max_speed").getAsInt());
        droneTypeFromDrone.setBatteryCapacity(droneTypesResponse.get("battery_capacity").getAsInt());
        droneTypeFromDrone.setControlRange(droneTypesResponse.get("control_range").getAsInt());
        droneTypeFromDrone.setMaximumCarriage(droneTypesResponse.get("max_carriage").getAsInt());
        return droneTypeFromDrone;
    }
    public static Drones constructerDrones(Drones drone, JsonObject jsonObject){
        drone.setId(jsonObject.get("id").getAsInt());
        drone.setCreated(jsonObject.get("created").getAsString());
        drone.setSerialnumber(jsonObject.get("serialnumber").getAsString());
        drone.setCarriage_weight(jsonObject.get("carriage_weight").getAsInt());
        drone.setCarriage_type(jsonObject.get("carriage_type").getAsString());

        return drone;
    }
//    public static DronesDynamics constructerDrones(DronesDynamics drone, JsonObject jsonObject){
//        drone.
//        return drone;
//    }

    public static ArrayList<Object> Input2Object(JsonObject input, API.APIEndpoints apiEndpoints) {
        ArrayList<Object> parsedResult = new ArrayList<>();
        Gson gson = new Gson();
        //JsonElement inputJson = JsonParser.parseString(input);
        JsonObject inputObject = input.getAsJsonObject();
        JsonArray inputArray = inputObject.getAsJsonArray("results");
        try {
            //wrong input
            if (!inputArray.isJsonArray()) {
                throw new IllegalArgumentException("Input is not a JSON array");
            }
            for (JsonElement element : inputArray) {
                if (element.isJsonObject()) {
                    JsonObject jsonObject = element.getAsJsonObject();
                    if (jsonObject.has("dronetype")) {

                        String droneTypeUrl = jsonObject.get("dronetype").getAsString();
                        URI uri = new URI(droneTypeUrl);
                        String dronetypePath = uri.getPath().substring("/api/".length());

                        // Fetch data for dronetypePath separately
                        JsonObject droneTypesResponse = apiEndpoints.getResponse(dronetypePath + "?format=json");
                        //create the object of DroneTypes
                        DroneTypes droneTypeFromDrone = new DroneTypes();
                        //constructerDroneType(droneTypeFromDrone, droneTypesResponse);
                        // Extracting ID from the drone URL
                        Drones drone = new Drones();
                        drone.setDronetype(constructerDroneType(droneTypeFromDrone, droneTypesResponse));
                        parsedResult.add(constructerDrones(drone,jsonObject ));

                    } else if (jsonObject.has("drone")) {
                        String droneUrl = jsonObject.get("drone").getAsString();
                        // Extracting ID from the drone URL
                        DroneDynamics droneDynamics = new DroneDynamics(droneUrl);
                        int droneID = droneDynamics.extractIdFromUrl(droneUrl);
                        //droneDynamics = gson.fromJson(jsonObject, DroneDynamics.class);
                        //
                        Drones drone = new Drones();
                        URI uri = new URI(droneUrl);
                        String dronePath = uri.getPath().substring("/api/".length());

                        // Fetch data for dronetypePath separately
                        JsonObject droneResponse = apiEndpoints.getResponse(dronePath + "?format=json");
                        //create the object of DroneTypes
                        DroneTypes droneFromDrone = new DroneTypes();
                        //
                        droneDynamics.setDroneClass(constructerDrones(drone, droneResponse));
                        droneDynamics.setDrone(jsonObject.get("drone").getAsString());
                        droneDynamics.setTimestamp(jsonObject.get("drone").getAsString());
                        droneDynamics.setId(droneID);
                        parsedResult.add(droneDynamics);
                    } else {
                       // parsedResult.add(gson.fromJson(jsonObject, DroneTypes.class));
                        DroneTypes droneType = new DroneTypes();
                        constructerDroneType(droneType,jsonObject);
                        parsedResult.add(constructerDroneType(droneType,jsonObject));
                    }
                }
            }
            //Now parsedResults contains the appropriate objects

            Collections.sort(parsedResult, (o1, o2) -> {
                int id1 = 0;
                int id2 = 0;

                if (o1 instanceof DroneTypes || o1 instanceof Drones) {
                    id1 = (o1 instanceof Drones) ? ((Drones) o1).getId() : ((DroneTypes) o1).getId();
                }

                if (o2 instanceof DroneTypes || o2 instanceof Drones) {
                    id2 = (o2 instanceof Drones) ? ((Drones) o2).getId() : ((DroneTypes) o2).getId();
                }
                if (o1 instanceof DroneDynamics && o2 instanceof DroneDynamics){
                    try {
                        id1 = ((DroneDynamics) o1).getId();
                        id2 = ((DroneDynamics) o2).getId();
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                }

                return Integer.compare(id1, id2);
            });


            //for (Object obj : parsedResult) System.out.println(obj.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedResult;
    }

}