package Drone;

import API.APIEndpoints;

import java.io.Serializable;

public class DroneTypes extends Catalog implements Serializable {
    static APIEndpoints apiEndpoints = new APIEndpoints(); // wieso nicht attribute sondern static
    private int id;
    private String manufacturer;
    private String typeName;
    private int weight;
    private int maximumSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maximumCarriage;
    private int countDroneTypes;
    DroneTypes() {
    }

    public DroneTypes(int id, String manufacturer, String typeName, int weight, int maximumSpeed, int batteryCapacity, int controlRange, int maximumCarriage) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.typeName = typeName;
        this.weight = weight;
        this.maximumSpeed = maximumSpeed;
        this.batteryCapacity = batteryCapacity;
        this.controlRange = controlRange;
        this.maximumCarriage = maximumCarriage;
    }


    public String toPrint() {
        return "DroneTypes [id=" + id
                + ", manufacturer=" + manufacturer
                + ", typename=" + typeName
                + ", weight=" + weight
                + ", maximumSpeed=" + maximumSpeed
                + ", batteryCapacity=" + batteryCapacity
                + ", controlRange=" + controlRange
                + ", maximumCarriage=" + maximumCarriage + "]";
    }
    @Override
    public String toString() {
        return "[" + id + ", " + manufacturer + ", " + typeName + ", " + weight + ", " + maximumSpeed + ", " + batteryCapacity + ", " + controlRange + ", " + maximumCarriage + "]";
    }

    public int setCountDroneTypes(){ //TODO: COUNT
        try {
            this.countDroneTypes = apiEndpoints.getDroneTypes().get("count").getAsInt();
            System.out.println("countDroneTypes " + countDroneTypes);
        }catch (NullPointerException e){
            System.out.println("count is null?????");
        }

        return getCountDroneTypes();
    }
    public int getCountDroneTypes() {
        return countDroneTypes;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }


    public void setControlRange(int controlRange) {
        this.controlRange = controlRange;
    }

    public void setMaximumCarriage(int maximumCarriage) {
        this.maximumCarriage = maximumCarriage;
    }
    public int getId() {
        return this.id;
    }

    public String getManufacturer() { return manufacturer; }
    public String getTypeName() {
        return typeName;
    }

    public int getWeight() { return weight; }

    public int getMaximumSpeed() { return maximumSpeed; }

    public int getBatteryCapacity() { return batteryCapacity; }

    public int getControlRange() { return controlRange; }

    public int getMaximumCarriage() { return maximumCarriage; }


}
