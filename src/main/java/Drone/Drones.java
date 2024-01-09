package Drone;

import java.net.MalformedURLException;
import java.net.URL;

import static Drone.DroneDynamics.extractIdFromUrl;

public class Drones extends Catalog {

    private int id;
    private int typeId;
    private String dronetype;
    private String created;
    private String serialnumber;
    private int carriage_weight;
    private String carriage_type;

    public Drones() {
    }

//    public Drones(int id, String dronetype, String created, String serialnumber, int carriage_weight, String carriage_type) {
//        this.id = id;
//        this.dronetype = dronetype;
//        this.created = created;
//        this.serialnumber = serialnumber;
//        this.carriage_weight = carriage_weight;
//        this.carriage_type = carriage_type;
//        try {
//            this.typeId = extractIdFromUrl(this.dronetype);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public Drones(String url) throws MalformedURLException {
        this.dronetype = url;
        this.typeId = extractIdFromUrl(this.dronetype);
        //System.out.println("con :" + this.id);
    }

    public int getId() { return this.id; }
    public int getTypeId() throws MalformedURLException { return this.id; }
    public void setTypeId(int typeId)  {
        this.typeId = typeId;
    }

    public void setDronetype(String dronetype) {
        this.dronetype = dronetype;
    }
    @Override
    public String toString() {
        return "Drones [id=" + id +
                ", typeId=" + typeId +
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
}
