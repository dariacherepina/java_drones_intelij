package Drone;

import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;

public class DroneDynamics extends Catalog {
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



    public DroneDynamics(JsonObject jsonObject) throws MalformedURLException {
        this.drone = jsonObject.get("drone").getAsString();
        this.id = extractIdFromUrl(this.drone);
        this.timestamp = jsonObject.get("timestamp").getAsString();
        this.speed = jsonObject.get("speed").getAsInt();
        this.align_roll = jsonObject.get("align_roll").getAsString();
        this.align_pitch = jsonObject.get("align_pitch").getAsString();
        this.align_yaw = jsonObject.get("align_yaw").getAsString();
        this.longitude = jsonObject.get("longitude").getAsString();
        this.latitude = jsonObject.get("latitude").getAsString();
        this.battery_status = jsonObject.get("battery_status").getAsString();
        this.last_seen = jsonObject.get("last_seen").getAsString();
        this.status = jsonObject.get("status").getAsString();
    }

    private int extractIdFromUrl(String drone) throws MalformedURLException {
        try {
            URL urlObj = new URL(drone); // Use the passed parameter
            String path = urlObj.getPath();
            String[] parts = path.split("/");
            String lastPart = parts[parts.length - 1];
            return Integer.parseInt(lastPart);
        } catch (MalformedURLException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Failed to extract ID from URL: " + drone);
            e.printStackTrace();
            throw e;
        }
    }


    @Override
    public String toString() {
        return "Drone Dynamics [" + "id=" + id
                + "drone=" + drone
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

    public int getId() throws MalformedURLException {
        return this.id;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }
}
