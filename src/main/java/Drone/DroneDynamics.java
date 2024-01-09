package Drone;

import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

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



//    public DroneDynamics createDroneDynamicsFromUrl(String url) throws MalformedURLException {
//        int id = extractIdFromUrl(url);
//        return new DroneDynamics(id);
//    }
//
//    public DroneDynamics(int id) {
//        extractIdFromUrl();
//        this.id = id;
//    }

    public DroneDynamics(String url) throws MalformedURLException {
        this.drone = url;
        this.id = extractIdFromUrl(this.drone);
        //System.out.println("con :" + this.id);
    }
    public static int extractIdFromUrl(String drone) throws MalformedURLException {
        try {
            URL urlObj = new URL(drone); // Use the passed parameter
            String path = urlObj.getPath();
           // System.out.println(path);
            String[] parts = path.split("/");
            //System.out.println(Arrays.toString(parts));
            String lastPart = parts[parts.length - 1];
            //System.out.println(Integer.parseInt(lastPart));
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

    public int getId() throws MalformedURLException {
        return this.id;
    }
    public void setId(int id)  {
         this.id = id;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }
//    public  ArrayList<DroneDynamics> filterDroneDynamicsById(ArrayList<Object> DroneDynamicsList, int id) {
//        return DroneDynamicsList.stream()
//                .filter(object -> object instanceof DroneDynamics)
//                .map(object -> (DroneDynamics) object)
//                .filter(droneDynamics -> {
//                    try {
//                        return droneDynamics.getId() == id;
//                    } catch (MalformedURLException e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .collect(Collectors.toCollection(ArrayList::new));
//    }
}
