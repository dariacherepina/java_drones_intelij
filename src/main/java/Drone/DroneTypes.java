package Drone;

public class DroneTypes extends Catalog {
    private int id;
    private String manufacturer;
    private String typename;
    private int weight;
    private int maximumSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maximumCarriage;
    DroneTypes(){}

    public DroneTypes(int id, String manufacturer, String typename, int weight, int maximumSpeed, int batteryCapacity, int controlRange, int maximumCarriage) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.typename = typename;
        this.weight = weight;
        this.maximumSpeed = maximumSpeed;
        this.batteryCapacity = batteryCapacity;
        this.controlRange = controlRange;
        this.maximumCarriage = maximumCarriage;
    }

    @Override
    public String toString() {
        return "DroneTypes [id=" + id
                + ", manufacturer=" + manufacturer
                + ", typename=" + typename
                + ", weight=" + weight
                + ", maximumSpeed=" + maximumSpeed
                + ", batteryCapacity=" + batteryCapacity
                + ", controlRange=" + controlRange
                + ", maximumCarriage=" + maximumCarriage + "]";
    }


    public int getId() {
        return this.id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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

    public int getWeight() {
        return weight;
    }

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getControlRange() {
        return controlRange;
    }

    public int getMaximumCarriage() {
        return maximumCarriage;
    }

    public String getTypename() {
        return typename;
    }
}
