package drone;

import api.APIConnection;
import api.APIEndpoints;
import api.Stream;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * This class is a representation of drone types,
 * and its information about a specific drone type, including its characteristics
 *
 * @author Nisa Colak
 */
public class DroneTypes implements Refreshable {
    private static int onlineCount;
    private static int offlineCount;
    private static File file = new File("outputDroneTypes.json");
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    private int id;
    private String manufacturer;
    private String typeName;
    private int weight;
    private int maximumSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maximumCarriage;

    public DroneTypes() {
    }

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
     * To check if file exist and if it is empty
     *
     * @return boolean
     */
    public static boolean ifFileValid() {
        if (file.exists() && file.isFile() && file.length() > 0) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * To sort ArrayList<DroneTypes> by speed
     *
     * @param droneTypesList ArrayList<DroneTypes>
     * @return ArrayList<DroneTypes> sorted
     */
    public static ArrayList<DroneTypes> sortSpeed(ArrayList<DroneTypes> droneTypesList) {
        Collections.sort(droneTypesList, new Comparator<DroneTypes>() {
            @Override
            public int compare(DroneTypes d1, DroneTypes d2) {
                return Integer.compare(d1.getMaximumSpeed(), d2.getMaximumSpeed());
            }
        });
        return droneTypesList;
    }

    /**
     * To sort ArrayList<DroneTypes> by maximumCarriage
     *
     * @param droneTypesList ArrayList<DroneTypes>
     * @return ArrayList<DroneTypes> sorted
     */
    public static ArrayList<DroneTypes> sortMaximumCarriage(ArrayList<DroneTypes> droneTypesList) {
        Collections.sort(droneTypesList, new Comparator<DroneTypes>() {
            @Override
            public int compare(DroneTypes d1, DroneTypes d2) {
                return Integer.compare(d1.getMaximumCarriage(), d2.getMaximumCarriage());
            }
        });
        return droneTypesList;
    }
    /**
     * To transform the Object to String
     *
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
    /**
     * To get the count of the data from the file
     *
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
     *
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
     *
     * @return boolean
     */
    @Override
    public boolean isRefreshChecked() throws IOException {
        if (checkOfflineCount() < checkOnlineCount()) {
            return true;
        } else {
            LOGGER.info("No updates");
            return false;
        }
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

    public static void setOnlineCount(int onlineCount) {
        DroneTypes.onlineCount = onlineCount;
    }

    public static void setOfflineCount(int offlineCount) {
        DroneTypes.offlineCount = offlineCount;
    }

    public static void setFile(File file) {
        DroneTypes.file = file;
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
}