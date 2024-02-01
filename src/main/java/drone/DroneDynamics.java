package drone;

import api.APIConnection;
import api.APIEndpoints;
import api.Stream;

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
 * This class represents the dynamic characteristics of drones
 * and providing time-related information for each drone
 *
 * @author Nisa Colak
 */
public class DroneDynamics implements Refreshable {
    private static int onlineCount;
    private static int offlineCount;
    private static File file = new File("outputDroneDynamics.json");
    private static final Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
    private int id;
    private String drone;
    private String timestamp;
    private int speed;
    private String align_roll;
    private String align_pitch;
    private String align_yaw;
    private String longitude;
    private String latitude;
    private String battery_status;
    private String last_seen;
    private String status;

    public DroneDynamics() {
    }

    public DroneDynamics(String drone, String timestamp, int speed, String align_roll, String align_pitch, String align_yaw, String longitude, String latitude, String battery_status, String last_seen, String status) {
        this.drone = drone;
        this.timestamp = timestamp;
        this.speed = speed;
        this.align_roll = align_roll;
        this.align_pitch = align_pitch;
        this.align_yaw = align_yaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.battery_status = battery_status;
        this.last_seen = last_seen;
        this.status = status;
        try {
            this.id = extractIdFromUrl(this.drone);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while extracting ID from URL.", e);
        }
    }

    /**
     * To sort ArrayList<DroneDynamics>  by status
     * ON goes first, OF second and IS last
     *
     * @param droneDynamicsList ArrayList<DroneDynamics>
     * @return ArrayList<DroneDynamics> sorted
     */
    public static ArrayList<DroneDynamics> sortStatus(ArrayList<DroneDynamics> droneDynamicsList) {
        Collections.sort(droneDynamicsList, new Comparator<DroneDynamics>() {
            @Override
            public int compare(DroneDynamics d1, DroneDynamics d2) {
                return d1.getStatus().compareTo(d2.getStatus());
            }
        });
        Collections.sort(droneDynamicsList, new Comparator<DroneDynamics>() {
            @Override
            public int compare(DroneDynamics d1, DroneDynamics d2) {
                if (d1.getStatus().equals("ON")) {
                    return -1;
                } else if (d1.getStatus().equals("OF")) {
                    if (d2.getStatus().equals("ON")) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (d2.getStatus().equals("ON") || d2.getStatus().equals("OF")) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        return droneDynamicsList;
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
     * To transform the Object to String
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Drone Dynamic:\n"
                + "\nId: " + id
                + "\nTimestamp: " + timestamp
                + "\nSpeed:" + speed
                + "\nAlign Roll:" + align_roll
                + "\nAlign Pitch: " + align_pitch
                + "\nAlign Yaw: " + align_yaw
                + "\nLongitude: " + longitude
                + "\nLatitude: " + latitude
                + "\nBattery Status: " + battery_status
                + "\nLast Seen: " + last_seen
                + "\nStatus: " + status;
    }

    /**
     * To get the count of the data from the file
     *
     * @return int offlineCount
     */
    @Override
    public int checkOfflineCount() {
        try {
            offlineCount = Stream.dataStreamOut("outputDroneDynamics").get("count").getAsInt();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An IOException occurred while checking offline count.", e);
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
            onlineCount = APIEndpoints.getDroneDynamics(36025, 36024).get("count").getAsInt();
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
    public boolean isRefreshChecked() {
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

    public String getDrone() {
        return drone;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getSpeed() {
        return speed;
    }

    public String getAlign_roll() {
        return align_roll;
    }

    public String getAlign_pitch() {
        return align_pitch;
    }

    public String getAlign_yaw() {
        return align_yaw;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getBattery_status() {
        return battery_status;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public String getStatus() {
        return status;
    }

    public static void setOnlineCount(int onlineCount) {
        DroneDynamics.onlineCount = onlineCount;
    }

    public static void setOfflineCount(int offlineCount) {
        DroneDynamics.offlineCount = offlineCount;
    }

    public static void setFile(File file) {
        DroneDynamics.file = file;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAlign_roll(String align_roll) {
        this.align_roll = align_roll;
    }

    public void setAlign_pitch(String align_pitch) {
        this.align_pitch = align_pitch;
    }

    public void setAlign_yaw(String align_yaw) {
        this.align_yaw = align_yaw;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setBattery_status(String battery_status) {
        this.battery_status = battery_status;
    }

    public void setLast_seen(String last_seen) {
        this.last_seen = last_seen;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * To get id of the Drone of this DroneDynamics
     *
     * @param drone String Link of the Drones
     * @return int the id of the drone to this DroneDynamic
     * @throws MalformedURLException when URL is malformed
     */
    private int extractIdFromUrl(String drone) throws MalformedURLException {
        try {
            URL urlObj = new URL(drone); // Use the passed parameter
            String path = urlObj.getPath();
            String[] parts = path.split("/");
            String lastPart = parts[parts.length - 1];
            return Integer.parseInt(lastPart);
        } catch (MalformedURLException | ArrayIndexOutOfBoundsException e) {
            LOGGER.log(Level.SEVERE, "Failed to extract ID from URL: " + drone, e);
            throw e;
        }
    }
}