package Drone;

import API.APIEndpoints;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;


public class Convert {

    static APIEndpoints apiEndpoints = new APIEndpoints();

    public Drones constructorDrones(Drones drone, JsonObject jsonObject, DroneTypes droneTypeFromDrone) {
        drone.setId(jsonObject.get("id").getAsInt());
        drone.setDroneType(droneTypeFromDrone);
        drone.setCreated(jsonObject.get("created").getAsString());
        drone.setSerialNumber(jsonObject.get("serialnumber").getAsString());
        drone.setCarriageWeight(jsonObject.get("carriage_weight").getAsInt());
        drone.setCarriageType(jsonObject.get("carriage_type").getAsString());

        return drone;
    }
    public DroneTypes constructorDroneType(DroneTypes droneTypeFromDrone, JsonObject droneTypesResponse) {
        droneTypeFromDrone.setId(droneTypesResponse.get("id").getAsInt());
        droneTypeFromDrone.setManufacturer(droneTypesResponse.get("manufacturer").getAsString());
        droneTypeFromDrone.setTypeName(droneTypesResponse.get("typename").getAsString());
        droneTypeFromDrone.setWeight(droneTypesResponse.get("weight").getAsInt());
        droneTypeFromDrone.setMaximumSpeed(droneTypesResponse.get("max_speed").getAsInt());
        droneTypeFromDrone.setBatteryCapacity(droneTypesResponse.get("battery_capacity").getAsInt());
        droneTypeFromDrone.setControlRange(droneTypesResponse.get("control_range").getAsInt());
        droneTypeFromDrone.setMaximumCarriage(droneTypesResponse.get("max_carriage").getAsInt());
        return droneTypeFromDrone;
    }
    public DroneDynamics constructorDroneDynamics(DroneDynamics droneDynamics, JsonObject jsonObject) {
        droneDynamics.setTimestamp(jsonObject.get("timestamp").getAsString());
        droneDynamics.setSpeed(jsonObject.get("speed").getAsInt());
        droneDynamics.setAlign_roll(jsonObject.get("align_roll").getAsString());
        droneDynamics.setAlign_pitch(jsonObject.get("align_pitch").getAsString());
        droneDynamics.setAlign_yaw(jsonObject.get("align_yaw").getAsString());
        droneDynamics.setLongitude(jsonObject.get("longitude").getAsString());
        droneDynamics.setLatitude(jsonObject.get("latitude").getAsString());
        droneDynamics.setBattery_status(jsonObject.get("battery_status").getAsString());
        droneDynamics.setLast_seen(jsonObject.get("last_seen").getAsString());
        droneDynamics.setStatus(jsonObject.get("status").getAsString());

        //droneDynamics.setId(droneID);
        return droneDynamics;
    }

    public ArrayList<Drones> Input2DronesObject(JsonObject input) {
        ArrayList<Drones> parsedResult = new ArrayList<>();
        //Gson gson = new Gson();
        JsonArray inputArray = input.getAsJsonArray("results");
        for (JsonElement element : inputArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                try {
                    //wrong input
                    if (!inputArray.isJsonArray()) {
                        throw new IllegalArgumentException("Input is not a JSON array");
                    } //TODO: Own Exception
                    // Drones
                    String droneTypeUrl = jsonObject.get("dronetype").getAsString();
                    URI uri = new URI(droneTypeUrl);
                    String droneTypePath = uri.getPath().substring("/api/".length());

                    // Fetch data for dronetypePath separately
                    JsonObject droneTypesResponse = apiEndpoints.getResponse(droneTypePath + "?format=json");
                    //create the object of DroneTypes
                    DroneTypes droneTypeFromDrone = new DroneTypes();
                    //constructerDroneType(droneTypeFromDrone, droneTypesResponse);
                    // Extracting ID from the drone URL

                    Drones drone = new Drones();


                    parsedResult.add(constructorDrones(drone, jsonObject, constructorDroneType(droneTypeFromDrone, droneTypesResponse)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return parsedResult;
    }

    public ArrayList<DroneTypes> Input2DroneTypesObject(JsonObject input) {
        ArrayList<DroneTypes> parsedResult = new ArrayList<>();
        //Gson gson = new Gson();
        JsonArray inputArray = input.getAsJsonArray("results");
        //wrong input
        if (!inputArray.isJsonArray()) {
            throw new IllegalArgumentException("Input is not a JSON array");
        }
        for (JsonElement element : inputArray) {
            if (element.isJsonObject()) {
                JsonObject jsonObject = element.getAsJsonObject();
                DroneTypes droneType = new DroneTypes();
                parsedResult.add(constructorDroneType(droneType, jsonObject));
            }
        }
        return parsedResult;
    }

    public ArrayList<DroneDynamics> Input2DroneDynamicsObject(JsonObject input) {
        ArrayList<DroneDynamics> parsedResult = new ArrayList<>();
        //Gson gson = new Gson();
        JsonArray inputArray = input.getAsJsonArray("results");
        //wrong input
        if (!inputArray.isJsonArray()) {
            throw new IllegalArgumentException("Input is not a JSON array");
        }
        for (JsonElement element : inputArray) {
            if (element.isJsonObject()) {
                //Dronedynamics
                JsonObject jsonObject = element.getAsJsonObject();
//                String droneUrl = jsonObject.get("drone").getAsString();
                String droneUrl = jsonObject.get("drone").getAsString();
                // Extracting ID from the drone URL
                DroneDynamics droneDynamics = null;
                try {
                    droneDynamics = new DroneDynamics(droneUrl);
                    //int droneID = droneDynamics.extractIdFromUrl();
                    //droneDynamics = gson.fromJson(jsonObject, DroneDynamics.class);

                    //Drones drone = new Drones();
                    //URI uri = new URI(droneUrl);
                    //String dronePath = uri.getPath().substring("/api/".length());

                    // Fetch data for dronetypePath separately
                    //JsonObject droneResponse = apiEndpoints.getResponse(dronePath + "?format=json");
                    //create the object of DroneTypes
                    //DroneTypes dronetypeFromDrone = new DroneTypes();

                    //droneDynamics.setDroneClass(constructerDrones(drone, droneResponse, dronetypeFromDrone));
                    parsedResult.add(constructorDroneDynamics(droneDynamics, jsonObject));
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return parsedResult;
    }

    public void addDroneDynamics(ArrayList<Drones> drones) {
        for (Drones obj : drones) {
            //info for dynamics by  id
            JsonObject droneDynamicsJsonObject = apiEndpoints.getDroneDynamicsIndivData(obj.getId());
            if (obj.getDroneDynamicsList() == null) {
                ArrayList<DroneDynamics> droneDynamicsArrayList = new ArrayList<>();
            }
            obj.setDroneDynamicsList(Input2DroneDynamicsObject(droneDynamicsJsonObject));

        }
    }
}