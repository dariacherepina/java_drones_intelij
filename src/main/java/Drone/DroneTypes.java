package Drone;

import API.APIConnection;
import API.APIEndpoints;
import API.Stream;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class DroneTypes extends Refresh {
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    private int id;
    private String manufacturer;
    private String typeName;
    private int weight;
    private int maximumSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maximumCarriage;

    private static int onlineCount;
    private static int offlineCount;
    private static File file = new File("outputDroneTypes.json");

    public DroneTypes(){}

    public DroneTypes(int id, String manufacturer, String typeName, int weight, int maximumSpeed, int batteryCapacity, int controlRange, int maximumCarriage) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.typeName = typeName;
        this.weight = weight;
        this.maximumSpeed = maximumSpeed;
        this.batteryCapacity = batteryCapacity;
        this.controlRange = controlRange;
        this.maximumCarriage = maximumCarriage;
    }

    /**
     * To transform the Object to String
     * @return String
     */
    public String toString() {
        return "\n Id: " + id
                + "\nManufacturer: " + manufacturer
                + "\nTypename: " + typeName
                + "\nWeight: " + weight
                + "\nMaximum Speed: " + maximumSpeed
                + "\nBattery Capacity: " + batteryCapacity
                + "\nControl Range: " + controlRange
                + "\nMaximumCarriage: " + maximumCarriage;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setControlRange(int controlRange) {
        this.controlRange = controlRange;
    }

    public void setMaximumCarriage(int maximumCarriage) {
        this.maximumCarriage = maximumCarriage;
    }

    public static void setOnlineCount(int onlineCount) {
        DroneTypes.onlineCount = onlineCount;
    }

    public static void setOfflineCount(int offlineCount) {
        DroneTypes.offlineCount = offlineCount;
    }

    public static void setFile(File file) {
        DroneTypes.file = file;
    }

    public int getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getControlRange() {
        return controlRange;
    }

    public int getMaximumCarriage() {
        return maximumCarriage;
    }

    public static int getOnlineCount() {
        return onlineCount;
    }

    public static int getOfflineCount() {
        return offlineCount;
    }

    public static File getFile() {
        return file;
    }
    /**
     * To get the count of the data from the file
     * @return int offlineCount
     */
    @Override
    public int checkOfflineCount() {
        JsonObject o;
        try {
            o = Stream.dataStreamOut("outputDroneTypes");
            offlineCount = o.get("count").getAsInt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return offlineCount;
    }
    /**
     * To get the count of the data from the server
     * @return int onlineCount
     */
    @Override
    public int checkOnlineCount() {
        try {
            onlineCount = APIEndpoints.getDroneTypesUrl(25, 19).get("count").getAsInt();
        } catch (NullPointerException e) {
            LOGGER.warning("NullPointerException: count is null");
        }
        return onlineCount;
    }
    /**
     * If true there is new data on the server, if false there is not
     * @return boolean
     */
    @Override
    public boolean checkRefresh() throws IOException{
        if (checkOfflineCount() < checkOnlineCount()) {
            return true;
        } else {
            LOGGER.info("No updates");
            return false;
        }
    }
    /**
     * To check if file exist and if it is empty
     * @return boolean
     */
    public static boolean ifFileValid(){
        if (file.exists() && file.isFile() && file.length() > 0){
            return true;
        }else {
            return false;
        }
    }




}