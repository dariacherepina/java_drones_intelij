package Drone;

import API.APIEndpoints;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Collections;

import static java.lang.System.in;


public class Convert {

    static APIEndpoints apiEndpoints = new APIEndpoints(); // wieso nicht attribute sondern static

    public void dataStreamIn (JsonObject jsonObject, String fileName) throws IOException {
        String jsonString = new Gson().toJson(jsonObject);
        // Write the JSON string to a file
        try {
            FileWriter fileWriter = new FileWriter( fileName + ".json");
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void dataStreamOut (String fileName) throws IOException {
        JsonObject jsonObject = null;
        try {
            FileReader fileReader = new FileReader( fileName + ".json");
            System.out.println(fileReader);
            // Parse the JSON file into a JsonObject
            jsonObject = new Gson().fromJson(fileReader, JsonObject.class);
            fileReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                    System.out.println("parsedResult" + parsedResult);
                    parsedResult.sort((o1, o2) -> {
                        int id1 = (o1).getId();
                        int id2 = (o2).getId();
                        return Integer.compare(id1, id2);
                    });
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
        parsedResult.sort((o1, o2) -> {
            int id1 = (o1).getId();
            int id2 = (o2).getId();
            return Integer.compare(id1, id2);
        });

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
                String droneUrl = jsonObject.get("drone").getAsString();
                // Extracting ID from the drone URL
                DroneDynamics droneDynamics;
                try {
                    droneDynamics = new DroneDynamics(droneUrl);
                    parsedResult.add(constructorDroneDynamics(droneDynamics, jsonObject));
                    parsedResult.sort((o1, o2) -> {
                        int id1 = (o1).getId();
                        int id2 = (o2).getId();
                        return Integer.compare(id1, id2);
                    });
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return parsedResult;
    }



    public Drones Input2DronesObjectIndiv(JsonObject input) {
        Drones drone = new Drones();
        try {
            String droneTypeUrl = input.get("dronetype").getAsString();
            URI uri = new URI(droneTypeUrl);
            String droneTypePath = uri.getPath().substring("/api/".length());
            // Fetch data for dronetypePath separately
            JsonObject droneTypesResponse = apiEndpoints.getResponse(droneTypePath + "?format=json");
            //create the object of DroneTypes
            DroneTypes droneTypeFromDrone = new DroneTypes();
            //constructerDroneType(droneTypeFromDrone, droneTypesResponse);
            // Extracting ID from the drone URL
            drone.setId(input.get("id").getAsInt());
            drone.setDroneType(constructorDroneType(droneTypeFromDrone, droneTypesResponse));
            drone.setCreated(input.get("created").getAsString());
            drone.setSerialNumber(input.get("serialnumber").getAsString());
            drone.setCarriageWeight(input.get("carriage_weight").getAsInt());
            drone.setCarriageType(input.get("carriage_type").getAsString());
            addDroneDynamicsForDrone(drone);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        return drone;
    }


    public void addDroneDynamicsForDrone(Drones drones) {
            //info for dynamics by  id
            JsonObject droneDynamicsJsonObject = apiEndpoints.getDroneDynamicsIndivData(drones.getId());
            if (drones.getDroneDynamicsList() == null) {
                ArrayList<DroneDynamics> droneDynamicsArrayList = new ArrayList<>();
            }
        drones.setDroneDynamicsList(Input2DroneDynamicsObject(droneDynamicsJsonObject));

    }
//    public void addDroneDynamicsForArray(ArrayList<Drones> drones) {
//        for (Drones obj : drones) {
//            //info for dynamics by  id
//            JsonObject droneDynamicsJsonObject = apiEndpoints.getDroneDynamicsIndivData(obj.getId());
//            if (obj.getDroneDynamicsList() == null) {
//                ArrayList<DroneDynamics> droneDynamicsArrayList = new ArrayList<>();
//            }
//            obj.setDroneDynamicsList(Input2DroneDynamicsObject(droneDynamicsJsonObject));
//
//        }
//    }




    public Object[][] ArrayList2ObjectDrones(ArrayList<Drones> drones ) {
        int numRows = drones.size();
        Object[][] droneTypesObj = new Object[numRows][8];
        for (int i = 0; i < numRows; i++) {
            Drones droneObj = drones.get(i);
            Object[] droneArray = new Object[5]; // considering there are 9 properties in the DroneType class
            droneArray[0] = droneObj.getId();
//            droneArray[1] = droneObj.getDroneType();
            droneArray[1] = droneObj.getCreated();
            droneArray[2] = droneObj.getSerialNumber();
            droneArray[3] = droneObj.getCarriageWeight();
            droneArray[4] = droneObj.getCarriageType();
            droneTypesObj[i] = droneArray;
        }
        return droneTypesObj;
    }
    public Object[][] ArrayList2ObjectDroneType(ArrayList<DroneTypes> droneTypes ) {
        int numRows = droneTypes.size();
        Object[][] droneTypesObj = new Object[numRows][8];
        for (int i = 0; i < numRows; i++) {
            DroneTypes droneTypeObj = droneTypes.get(i);
            Object[] droneTypeArray = new Object[8];
            droneTypeArray[0] = droneTypeObj.getId();
            droneTypeArray[1] = droneTypeObj.getManufacturer();
            droneTypeArray[2] = droneTypeObj.getTypeName();
            droneTypeArray[3] = droneTypeObj.getWeight();
            droneTypeArray[4] = droneTypeObj.getMaximumSpeed();
            droneTypeArray[5] = droneTypeObj.getBatteryCapacity();
            droneTypeArray[6] = droneTypeObj.getControlRange();
            droneTypeArray[7] = droneTypeObj.getMaximumCarriage();
            // considering there are 9 properties in the DroneType class

            droneTypesObj[i] = droneTypeArray;
        }
        return droneTypesObj;
    }
    public Object[][] ArrayList2ObjectDroneDynamics(ArrayList<DroneDynamics> droneDynamics ) {
        int numRows = droneDynamics.size();
        Object[][] droneTypesObj = new Object[numRows][8];
        for (int i = 0; i < numRows; i++) {
            DroneDynamics droneDynamicsObj = droneDynamics.get(i);
            Object[] droneDynamicsArray = new Object[11]; // considering there are 9 properties in the DroneType class
            droneDynamicsArray[0] = droneDynamicsObj.getId();
            droneDynamicsArray[1] = droneDynamicsObj.getTimestamp();
            droneDynamicsArray[2] = droneDynamicsObj.getSpeed();
            droneDynamicsArray[3] = droneDynamicsObj.getAlign_roll();
            droneDynamicsArray[4] = droneDynamicsObj.getAlign_pitch();
            droneDynamicsArray[5] = droneDynamicsObj.getAlign_yaw();
            droneDynamicsArray[6] = droneDynamicsObj.getLongitude();
            droneDynamicsArray[7] = droneDynamicsObj.getLatitude();
            droneDynamicsArray[8] = droneDynamicsObj.getBattery_status();
            droneDynamicsArray[9] = droneDynamicsObj.getLast_seen();
            droneDynamicsArray[10] = droneDynamicsObj.getStatus();

            droneTypesObj[i] = droneDynamicsArray;
        }
        return droneTypesObj;
    }
//    public Object[][] ArrayList2ObjectDronesIndiv(ArrayList<Drones> drones ) {
//        int numRows = drones.size();
//        Object[][] droneTypesObj = new Object[numRows][8];
//        for (int i = 0; i < numRows; i++) {
//            Drones droneObj = drones.get(i);
//            Object[] droneArray = new Object[7]; // considering there are 9 properties in the DroneType class
//            droneArray[0] = droneObj.getId();
//            droneArray[1] = droneObj.getDroneType();
//            droneArray[2] = droneObj.getCreated();
//            droneArray[3] = droneObj.getSerialNumber();
//            droneArray[4] = droneObj.getCarriageWeight();
//            droneArray[5] = droneObj.getCarriageType();
//            droneArray[6] = droneObj.getDroneDynamicsList();
//            droneTypesObj[i] = droneArray;
//        }
//        return droneTypesObj;
//    }
}