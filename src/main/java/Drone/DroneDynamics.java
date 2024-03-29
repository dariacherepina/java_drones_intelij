package Drone;

import API.APIEndpoints;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DroneDynamics extends Catalog {
    private static final Logger LOGGER = Logger.getLogger(DroneDynamics.class.getName());

    static APIEndpoints apiEndpoints = new APIEndpoints();

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
    private int countDroneDynamics;



    public DroneDynamics(String url) throws MalformedURLException {
        this.drone = url;
        this.id = extractIdFromUrl(this.drone);
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
            throw new RuntimeException(e);
        }
    }

    public DroneDynamics() {}

    public int extractIdFromUrl(String drone) throws MalformedURLException {
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


    @Override
    public String toString() {
        return "[" + "id=" + id

                + ", drone=" + drone
                + ", timestamp=" + timestamp
                + ", speed=" + speed
                + ", align_roll=" + align_roll
                + ", align_pitch=" + align_pitch
                + ", align_yaw=" + align_yaw
                + ", longitude=" + longitude
                + ", latitude=" + latitude
                + ", battery_status=" + battery_status
                + ", last_seen=" + last_seen
                + ", status=" + status + "]";
    }


    public int getCountDroneDynamics() {
        return countDroneDynamics;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public void setId(int id) {
        this.id = id;
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
    public int getId() { return this.id; }
    public String getDrone() { return drone;}
    public String getTimestamp() { return timestamp; }
    public int getSpeed() { return speed; }
    public String getAlign_roll() { return align_roll; }
    public String getAlign_pitch() { return align_pitch; }
    public String getAlign_yaw() { return align_yaw; }
    public String getLongitude() { return longitude;}
    public String getLatitude() { return latitude; }
    public String getBattery_status() { return battery_status; }
    public String getLast_seen() { return last_seen; }
    public String getStatus() { return status; }


}
