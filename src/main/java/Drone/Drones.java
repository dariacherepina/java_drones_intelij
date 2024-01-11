package Drone;

import java.util.ArrayList;

public class Drones extends Catalog {

    private int id;
    //private int typeId;
    private DroneTypes droneType;
    private String created;
    private String serialNumber;
    private int carriageWeight;
    private String carriageType;

    private ArrayList<DroneDynamics> droneDynamicsList;

    private static int dronesCount;

    public Drones() {
    }

    public Drones(int id, DroneTypes droneType, String created, String serialNumber, int carriageWeight, String carriageType) {
        this.id = id;
        this.droneType = droneType;
        this.created = created;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
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



    public void setId(int id) {
        this.id = id;
    }
    public void setDroneType(DroneTypes droneType) { this.droneType = droneType; }

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

}
