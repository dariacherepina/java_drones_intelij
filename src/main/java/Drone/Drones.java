package Drone;

import java.net.MalformedURLException;
import java.net.URL;

import static Drone.DroneDynamics.extractIdFromUrl;

public class Drones extends Catalog {

    private int id;
    //private int typeId;

    private DroneTypes dronetype;
    private String created;
    private String serialnumber;
    private int carriage_weight;
    private String carriage_type;

    public Drones() {
    }

    public Drones(int id, DroneTypes dronetype, String created, String serialnumber, int carriage_weight, String carriage_type) {
        this.id = id;
        this.dronetype = dronetype;
        this.created = created;
        this.serialnumber = serialnumber;
        this.carriage_weight = carriage_weight;
        this.carriage_type = carriage_type;
        //


    }

//    public Drones(String url) throws MalformedURLException {
//        this.dronetype = url;
//        this.typeId = extractIdFromUrl(this.dronetype);
//        //System.out.println("con :" + this.id);
//    }


    @Override
    public String toString() {
        return "Drones [id=" + id +
                ", dronetype=" + dronetype +
                ", created=" + created
                + ", serialnumber=" + serialnumber
                + ", carriage_weight=" + carriage_weight
                + ", carriage_type=" + carriage_type + "]";
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

    public void setDronetype(DroneTypes dronetype) {
        this.dronetype = dronetype;
    }
    public int getId() { return this.id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public void setCarriage_weight(int carriage_weight) {
        this.carriage_weight = carriage_weight;
    }

    public void setCarriage_type(String carriage_type) {
        this.carriage_type = carriage_type;
    }
// public int getTypeId() throws MalformedURLException { return this.id; }
    // public void setTypeId(int typeId)  { this.typeId = typeId; }


}
