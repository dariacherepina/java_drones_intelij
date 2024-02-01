package drone;

import api.Stream;
import exception.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Convert {
    ArrayList<Drones> DronesList;
    ArrayList<DroneTypes> DroneTypesList;
    ArrayList<DroneDynamics> DroneDynamicsList;
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());

    /**
     * We want to check if the data is already saved to the files than just initialise, if not than fetch data first and then initialise it
     * @throws IOException throws
     */
    public void checkToInitialiseAllData() throws IOException {
        if (Drones.ifFileValid() && DroneTypes.ifFileValid() && DroneDynamics.ifFileValid()) {
            initialiseAllData();
        }else {
            Stream.fetchData();
            initialiseAllData();
        }
    }

    /**
     * initialise ArrayLists with data from files
     * @throws IOException throws
     */
    public void initialiseAllData() throws IOException {
        DronesList = initialiseDrones(Stream.dataStreamOut("outputDrones"));
        DroneTypesList = initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
        DroneDynamicsList = initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
        addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
    }

    /**
     *
     * @param input JsonObject from the files
     * @return ArrayList<Drones>
     */
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
    /**
     *
     * @param input JsonObject from the files
     * @return ArrayList<DroneTypes>
     */
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
        droneTypesList.sort((o1, o2) -> {
            int id1 = (o1).getId();
            int id2 = (o2).getId();
            return Integer.compare(id1, id2);
        });
        return droneTypesList;
    }
    /**
     *
     * @param input JsonObject from the files
     * @return ArrayList<DroneDynamics>
     */
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

    /**
     * Find the drone in ArrayList of drones with the right id
     * @param dronesList ArrayList<Drones>
     * @param id int
     * @return drone of the right id
     * @throws InvalidIdInput customise Exception, when the id is out of range
     */

    public Drones findDrone(ArrayList<Drones> dronesList, int id) throws InvalidIdInput{
        for (Drones drone : dronesList) {
            if (drone.getId() == id) {
                return drone;
            }
        }
        throw new InvalidIdInput("Invalid drone ID input");
    }

    /**
     * add DroneTypes and DroneDynamics to the Drones based on the id
     * @param dronesList ArrayList<Drones>
     * @param droneTypesList ArrayList<DroneTypes
     * @param droneDynamicsList ArrayList<DroneDynamics>
     */
    public void addAdditinalDataToDrone(ArrayList<Drones> dronesList, ArrayList<DroneTypes> droneTypesList, ArrayList<DroneDynamics> droneDynamicsList) {
        for (Drones drone : dronesList) {
            try {
                addDroneTypesForDrone(drone, droneTypesList);
                addDroneDynamicsForDrone(drone, droneDynamicsList);
            } catch (AbsenceTypeForDrone e) {
                LOGGER.log(Level.SEVERE, "Error adding additional data to drone",e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * To add the DroneType for each Drone
     * @param drone Drones
     * @param droneTypesList ArrayList<DroneTypes>
     * @throws AbsenceTypeForDrone No DroneType for this Drone
     */
    private void addDroneTypesForDrone(Drones drone, ArrayList<DroneTypes> droneTypesList) throws AbsenceTypeForDrone {
        for (DroneTypes droneType : droneTypesList) {
            if (drone.getIdType() == droneType.getId()) {
                drone.setDroneType(droneType);
            }
        }
        if(drone.getDroneType() == null){
            throw new AbsenceTypeForDrone("No DroneType for this Drone");
        }
    }

    /**
     * To add the DroneDynamics for each Drone
     * @param drone Drones
     * @param droneDynamicsList ArrayList<DroneDynamics>
     */
    private void addDroneDynamicsForDrone(Drones drone, ArrayList<DroneDynamics> droneDynamicsList) {
        ArrayList<DroneDynamics> droneDynamicsForDrone = new ArrayList<>();
        for (DroneDynamics droneDynamic : droneDynamicsList) {
            if (drone.getId() == droneDynamic.getId()) {
                droneDynamicsForDrone.add(droneDynamic);
            }
        }
        drone.setDroneDynamicsList(droneDynamicsForDrone);
    }

    /**
     * Convert Arraylist<Drones> drones to Object [][]
     * @param drones ArrayList<Drones>
     * @return Object [][] droneObj
     */
    public Object[][] convertArrayListToObjectDrones(ArrayList<Drones> drones) {
        int numRows = drones.size();
        Object[][] donesObj = new Object[numRows][8];

        for (int i = 0; i < numRows; i++) {
            Drones droneObj = drones.get(i);
            Object[] droneArray = new Object[5];
            droneArray[0] = droneObj.getId();
            droneArray[1] = droneObj.getCreated();
            droneArray[2] = droneObj.getSerialNumber();
            droneArray[3] = droneObj.getCarriageWeight();
            droneArray[4] = droneObj.getCarriageType();
            donesObj[i] = droneArray;
        }
        return donesObj;
    }

    /**
     * Convert Arraylist<DroneTypes> drones to Object [][]
     * @param droneTypes ArrayList<DroneTypes>
     * @return Object [][] droneTypesObj
     */
    public Object[][] convertArrayListToObjectDroneType(ArrayList<DroneTypes> droneTypes) {
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

            droneTypesObj[i] = droneTypeArray;
        }
        return droneTypesObj;
    }
    /**
     * Convert Arraylist<DroneDynamics> drones to Object [][]
     * @param droneDynamics ArrayList<DroneDynamics>
     * @return Object [][] droneDynamicsObj
     */
    public Object[][] convertArrayListToObjectDroneDynamics(ArrayList<DroneDynamics> droneDynamics) {
        int numRows = droneDynamics.size();
        Object[][] droneDynamicsObj = new Object[numRows][8];
        for (int i = 0; i < numRows; i++) {
            DroneDynamics droneDynamicObj = droneDynamics.get(i);
            Object[] droneDynamicsArray = new Object[11]; // considering there are 9 properties in the DroneType class
            droneDynamicsArray[0] = droneDynamicObj.getId();
            droneDynamicsArray[1] = droneDynamicObj.getTimestamp();
            droneDynamicsArray[2] = droneDynamicObj.getSpeed();
            droneDynamicsArray[3] = droneDynamicObj.getAlign_roll();
            droneDynamicsArray[4] = droneDynamicObj.getAlign_pitch();
            droneDynamicsArray[5] = droneDynamicObj.getAlign_yaw();
            droneDynamicsArray[6] = droneDynamicObj.getLongitude();
            droneDynamicsArray[7] = droneDynamicObj.getLatitude();
            droneDynamicsArray[8] = droneDynamicObj.getBattery_status();
            droneDynamicsArray[9] = droneDynamicObj.getLast_seen();
            droneDynamicsArray[10] = droneDynamicObj.getStatus();
            droneDynamicsObj[i] = droneDynamicsArray;
        }
        return droneDynamicsObj;
    }

    public ArrayList<Drones> getDronesList() {
        return DronesList;
    }

    public ArrayList<DroneTypes> getDroneTypesList() {
        return DroneTypesList;
    }

    public ArrayList<DroneDynamics> getDroneDynamicsList() {
        return DroneDynamicsList;
    }
}