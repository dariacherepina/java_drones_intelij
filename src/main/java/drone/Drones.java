package drone;

import api.APIConnection;
import api.APIEndpoints;
import api.Stream;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents drone entities,
 * and providing static information for each drone
 *
 * @author Daria Cherepina
 */
public class Drones implements Refreshable {
    private static int onlineCount;
    private static int offlineCount;
    private static File file = new File("outputDrones.json");
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    private ArrayList<DroneDynamics> droneDynamicsList;
    private DroneTypes droneType;
    private int idType;
    private int id;
    private String droneTypeLink;
    private String created;
    private String serialNumber;
    private int carriageWeight;
    private String carriageType;

    public Drones() {
    }

    public Drones(int id, String droneTypeLink, String created, String serialNumber, int carriageWeight, String carriageType) {
        this.id = id;
        this.droneTypeLink = droneTypeLink;
        this.created = created;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
        try {
            this.idType = extractIdFromUrl(this.droneTypeLink);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while extracting ID from URL.", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * To check if file exist and if it is empty
     *
     * @return boolean
     */
    public static boolean isFileValid() throws IOException {
        if (file.exists() && file.isFile() && file.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * To sort ArrayList<Drones> by CarriageWeight
     *
     * @param dronesList ArrayList<Drones>
     * @return ArrayList<Drones> sorted
     */
    public static ArrayList<Drones> sortCarriageWeight(ArrayList<Drones> dronesList) {
        Collections.sort(dronesList, new Comparator<Drones>() {
            @Override
            public int compare(Drones d1, Drones d2) {
                return Integer.compare(d1.getCarriageWeight(), d2.getCarriageWeight());
            }
        });
        return dronesList;
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
            o = Stream.dataStreamOut("outputDrones");
            offlineCount = o.get("count").getAsInt();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An IOException occurred while checking offline count.", e);
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
            onlineCount = APIEndpoints.getDronesUrl(25, 24).get("count").getAsInt();
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

    /**
     * To transform the Object to String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Drones\nId: " + id
                + "\nDronetype: " + droneType
                + "\nDrone Dynamics List: " + droneDynamicsList
                + "\nCreated: " + created
                + "\nSerial Number: " + serialNumber
                + "\nCarriage Weight: " + carriageWeight
                + "\nCarriage Type: " + carriageType;
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

    public ArrayList<DroneDynamics> getDroneDynamicsList() {
        return droneDynamicsList;
    }

    public DroneTypes getDroneType() {
        return droneType;
    }

    public int getIdType() {
        return idType;
    }

    public int getId() {
        return id;
    }

    public String getDroneTypeLink() {
        return droneTypeLink;
    }

    public String getCreated() {
        return created;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getCarriageWeight() {
        return carriageWeight;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public static void setOnlineCount(int onlineCount) {
        Drones.onlineCount = onlineCount;
    }

    public static void setOfflineCount(int offlineCount) {
        Drones.offlineCount = offlineCount;
    }

    public static void setFile(File file) {
        Drones.file = file;
    }

    public void setDroneDynamicsList(ArrayList<DroneDynamics> droneDynamicsList) {
        this.droneDynamicsList = droneDynamicsList;
    }

    public void setDroneType(DroneTypes droneType) {
        this.droneType = droneType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDroneTypeLink(String droneTypeLink) {
        this.droneTypeLink = droneTypeLink;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setCarriageWeight(int carriageWeight) {
        this.carriageWeight = carriageWeight;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    /**
     * To get id of the DroneType of this Drone
     *
     * @param droneTypeLink String Link of the DroneTypes
     * @return int the id of the DroneType to this Drone
     * @throws MalformedURLException when URL is malformed
     */
    private int extractIdFromUrl(String droneTypeLink) throws MalformedURLException {
        try {
            URL urlObj = new URL(droneTypeLink); // Use the passed parameter
            String path = urlObj.getPath();
            String[] parts = path.split("/");
            String lastPart = parts[parts.length - 1];
            return Integer.parseInt(lastPart);
        } catch (MalformedURLException | ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE, "Failed to extract ID from URL: " + droneTypeLink, e);
            throw e;
        }
    }

}