package Drone;

import API.APIConnection;
import API.APIEndpoints;
import API.Stream;
import Exception.*;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Drones extends Refresh {
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

    private static int onlineCount;
    private static int offlineCount;

    public Drones() {}

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
            throw new RuntimeException(e);
        }
    }

    public int extractIdFromUrl(String droneTypeLink) throws MalformedURLException {
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

    public void setDroneType(DroneTypes droneType) {
        this.droneType = droneType;
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

    public void setDroneDynamicsList(ArrayList<DroneDynamics> droneDynamicsList) {
        this.droneDynamicsList = droneDynamicsList;
    }


    public int getId() {
        return this.id;
    }

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

    public ArrayList<DroneDynamics> getDroneDynamicsList() {
        return droneDynamicsList;
    }

    public int getIdType() {
        return idType;
    }

    public String getDroneTypeLink() {
        return droneTypeLink;
    }

    public static int getOfflineCount() {
        return offlineCount;
    }

    public static int getOnlineCount() {
        return onlineCount;
    }

    @Override
    public int checkOfflineCount() {
        JsonObject o;
        try {
            o = Stream.dataStreamOut("outputDrones");
            offlineCount = o.get("count").getAsInt();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return offlineCount;
    }

    @Override
    public int checkOnlineCount() {
        try {
            onlineCount = APIEndpoints.getDronesUrl(25, 24).get("count").getAsInt();
        } catch (NullPointerException e) {
            LOGGER.warning("NullPointerException: count is null");
        }
        return onlineCount;
    }

    @Override
    public boolean checkRefresh() throws IOException{
        if (checkOfflineCount() < checkOnlineCount()) {
            return true;
        } else {
            LOGGER.info("No updates");
            return false;
        }
    }
//    @Override
//    public void refresh() throws IOException {
//        if (checkRefresh()) {
//            try {
//                Stream.dataStreamIn(APIEndpoints.getDronesUrl(100, 0), "outputDrones");
//                //trigger gui to refetch data
//            } catch (InvalidFileNameException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            LOGGER.info("Same amount of data. No Updates ");
//        }
//    }

}