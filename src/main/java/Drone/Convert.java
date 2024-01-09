package Drone;

import com.google.gson.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;


public class Convert {

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
                        //
                        String droneTypeUrl = jsonObject.get("dronetype").getAsString();
                        //
                        URI uri = new URI(droneTypeUrl);
                        String dronetypePath = uri.getPath().substring("/api/".length());

                        // Fetch data for dronetypePath separately
                        JsonObject droneTypesResponse = apiEndpoints.getResponse(dronetypePath + "?format=json");
                        //System.out.println(droneTypesResponse);
                        //create the object of DroneTypes
                        DroneTypes droneTypeFromDrone = new DroneTypes();
                        droneTypeFromDrone.setId(droneTypesResponse.get("id").getAsInt());
                        droneTypeFromDrone.setManufacturer(droneTypesResponse.get("manufacturer").getAsString());
                        droneTypeFromDrone.setTypename(droneTypesResponse.get("typename").getAsString());
                        droneTypeFromDrone.setWeight(droneTypesResponse.get("weight").getAsInt());
                        droneTypeFromDrone.setMaximumSpeed(droneTypesResponse.get("max_speed").getAsInt());
                        droneTypeFromDrone.setBatteryCapacity(droneTypesResponse.get("battery_capacity").getAsInt());
                        droneTypeFromDrone.setControlRange(droneTypesResponse.get("control_range").getAsInt());
                        droneTypeFromDrone.setMaximumCarriage(droneTypesResponse.get("max_carriage").getAsInt());



                        //
                        // Extracting ID from the drone URL
                        Drones drone = new Drones();

                        //drone = gson.fromJson(jsonObject, Drones.class);
                        //int dronetTypeID = drone.extractIdFromUrl(droneTypeUrl);;
                        //drone.setDronetype(jsonObject.get("dronetype").getAsString());
                        drone.setDronetype(droneTypeFromDrone);
                        drone.setId(jsonObject.get("id").getAsInt());
                        drone.setCreated(jsonObject.get("created").getAsString());
                        drone.setSerialnumber(jsonObject.get("serialnumber").getAsString());
                        drone.setCarriage_weight(jsonObject.get("carriage_weight").getAsInt());
                        drone.setCarriage_type(jsonObject.get("carriage_type").getAsString());



                        parsedResult.add(drone);

                        //
                    } else if (jsonObject.has("drone")) {
                        String droneUrl = jsonObject.get("drone").getAsString();

                        // Extracting ID from the drone URL
                        DroneDynamics droneDynamics = new DroneDynamics(droneUrl);
                        int droneID = droneDynamics.extractIdFromUrl(droneUrl);
                        droneDynamics = gson.fromJson(jsonObject, DroneDynamics.class);
                        droneDynamics.setDrone(jsonObject.get("drone").getAsString());
                        System.out.println("waiting");
                        droneDynamics.setId(droneID);
                        parsedResult.add(droneDynamics);
                    } else {
                       // parsedResult.add(gson.fromJson(jsonObject, DroneTypes.class));
                        DroneTypes droneTypeFromDrone = new DroneTypes();
                        droneTypeFromDrone.setId(jsonObject.get("id").getAsInt());
                        droneTypeFromDrone.setManufacturer(jsonObject.get("manufacturer").getAsString());
                        droneTypeFromDrone.setTypename(jsonObject.get("typename").getAsString());
                        droneTypeFromDrone.setWeight(jsonObject.get("weight").getAsInt());
                        droneTypeFromDrone.setMaximumSpeed(jsonObject.get("max_speed").getAsInt());
                        droneTypeFromDrone.setBatteryCapacity(jsonObject.get("battery_capacity").getAsInt());
                        droneTypeFromDrone.setControlRange(jsonObject.get("control_range").getAsInt());
                        droneTypeFromDrone.setMaximumCarriage(jsonObject.get("max_carriage").getAsInt());
                        parsedResult.add(droneTypeFromDrone);
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