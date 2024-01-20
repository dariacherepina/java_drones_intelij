package Drone;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.logging.Logger;


public class Convert {
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());

    public ArrayList<Drones> initialiseDrones(JsonObject input) {
        ArrayList<Drones> dronesList = new ArrayList<>();
        JsonArray jsonArray = input.getAsJsonArray("results");
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            dronesList.add(new Drones(
                    obj.get("id").getAsInt(),
                    obj.get("dronetype").getAsString(),
                    obj.get("created").getAsString(),
                    obj.get("serialnumber").getAsString(),
                    obj.get("carriage_weight").getAsInt(),
                    obj.get("carriage_type").getAsString()
            ));


        }
        return dronesList;
    }

    public ArrayList<DroneTypes> initialiseDroneTypes(JsonObject input) {
        ArrayList<DroneTypes> droneTypesList = new ArrayList<>();
        JsonArray jsonArray = input.getAsJsonArray("results");
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            droneTypesList.add(new DroneTypes(
                    obj.get("id").getAsInt(),
                    obj.get("manufacturer").getAsString(),
                    obj.get("typename").getAsString(),
                    obj.get("weight").getAsInt(),
                    obj.get("max_speed").getAsInt(),
                    obj.get("battery_capacity").getAsInt(),
                    obj.get("control_range").getAsInt(),
                    obj.get("max_carriage").getAsInt()
            ));

        }
        return droneTypesList;
    }

    public ArrayList<DroneDynamics> initialiseDroneDynamics(JsonObject input) {
        ArrayList<DroneDynamics> droneDynamicsList = new ArrayList<>();
        JsonArray jsonArray = input.getAsJsonArray("results");
        for (JsonElement element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            droneDynamicsList.add(new DroneDynamics(
                    obj.get("drone").getAsString(),
                    obj.get("timestamp").getAsString(),
                    obj.get("speed").getAsInt(),
                    obj.get("align_roll").getAsString(),
                    obj.get("align_pitch").getAsString(),
                    obj.get("align_yaw").getAsString(),
                    obj.get("longitude").getAsString(),
                    obj.get("latitude").getAsString(),
                    obj.get("battery_status").getAsString(),
                    obj.get("last_seen").getAsString(),
                    obj.get("status").getAsString()
            ));

            droneDynamicsList.sort((o1, o2) -> {
                int id1 = (o1).getId();
                int id2 = (o2).getId();
                return Integer.compare(id1, id2);
            });
        }
        return droneDynamicsList;
    }

    // find right drone in arrayList of drones
    public Drones findDrone(ArrayList<Drones> dronesList, int id) {
        for (Drones drone : dronesList) {
            if (drone.getId() == id) {
                return drone;
            }
        }
        return null;
    }


    //addign DroneTypes and DroneDynamics to the Drones based on the id
    public void addAdditinalDataToDrone(ArrayList<Drones> dronesList, ArrayList<DroneTypes> droneTypesList, ArrayList<DroneDynamics> droneDynamicsList) {
        for (Drones drone : dronesList) {
            addDroneTypesForDrone(drone, droneTypesList);
            addDroneDynamicsForDrone(drone, droneDynamicsList);
        }
    }

    public void addDroneTypesForDrone(Drones drone, ArrayList<DroneTypes> droneTypesList) {
        for (DroneTypes droneType : droneTypesList) {
            if (drone.getIdType() == droneType.getId()) {
                drone.setDroneType(droneType);
            }
        }
    }

    public void addDroneDynamicsForDrone(Drones drone, ArrayList<DroneDynamics> droneDynamicsList) {
        ArrayList<DroneDynamics> droneDynamicsForDrone = new ArrayList<>();
        for (DroneDynamics droneDynamic : droneDynamicsList) {
            if (drone.getId() == droneDynamic.getId()) {
                droneDynamicsForDrone.add(droneDynamic);
            }
        }
        drone.setDroneDynamicsList(droneDynamicsForDrone);
    }

    public Object[][] ArrayList2ObjectDrones(ArrayList<Drones> drones) {
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

    public Object[][] ArrayList2ObjectDroneType(ArrayList<DroneTypes> droneTypes) {
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

    public Object[][] ArrayList2ObjectDroneDynamics(ArrayList<DroneDynamics> droneDynamics) {
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

}