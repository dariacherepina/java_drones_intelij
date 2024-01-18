package Drone;


import API.APIEndpoints;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;

public class Drones extends Catalog {
    private static final Logger LOGGER = Logger.getLogger(DroneDynamics.class.getName());
    static APIEndpoints apiEndpoints = new APIEndpoints();
    private int id;
 
    private DroneTypes droneType;
    private int idType;
    private int id;
    private String droneTypeLink;
    private String created;
    private String serialNumber;
    private int carriageWeight;
    private String carriageType;

    private final static String link = "http://dronesim.facets-labs.com/api/drones";

    private static int onlineCount;
    private static int offlineCount;



    public Drones() {}

    public Drones(int id, String dronetypeLink, String created, String serialNumber, int carriageWeight, String carriageType) {
        this.id = id;
        this.droneTypeLink = dronetypeLink;
        this.created = created;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
        try {
            this.idType = extractIdFromUrl(this.droneTypeLink);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public int extractIdFromUrl(String dronetypeLink) throws MalformedURLException {
        try {
            URL urlObj = new URL(dronetypeLink); // Use the passed parameter
            String path = urlObj.getPath();
            String[] parts = path.split("/");
            String lastPart = parts[parts.length - 1];
            return Integer.parseInt(lastPart);
        } catch (MalformedURLException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Failed to extract ID from URL: " + dronetypeLink);
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public String toString() {
        return "Drones [id=" + id
                + ", droneType= " + droneType
                + ", droneDynamicsList =" + droneDynamicsList
                + ", created=" + created
                + ", serialNumber=" + serialNumber
                + ", carriage_weight=" + carriageWeight
                + ", carriage_type=" + carriageType + "]";
    }




    public void setIdType(int idType) {
        this.idType = idType;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDroneType(DroneTypes droneType) { this.droneType = droneType; }
    public void setDroneTypeLink(String droneTypeLink) {
        this.droneTypeLink = droneTypeLink;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    public void setCarriageWeight(int carriageWeight) { this.carriageWeight = carriageWeight; }
    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }
    public void setDroneDynamicsList(ArrayList<DroneDynamics> droneDynamicsList) { this.droneDynamicsList = droneDynamicsList; }


    public int getId() { return this.id; }
    public DroneTypes getDroneType() {
        return droneType;
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
    public ArrayList<DroneDynamics> getDroneDynamicsList() { return droneDynamicsList; }
    public int getIdType() { return idType;}
    public String getDroneTypeLink() { return droneTypeLink; }


    public static void setOfflineCount(int offlineCount) {
        Drones.offlineCount = offlineCount;
    }
    public static int getOfflineCount() { return offlineCount; }

    public static int getOnlineCount() {
        return onlineCount;
    }

    public static void setOnlineCount(int count) {
        onlineCount = count;
    }



}


