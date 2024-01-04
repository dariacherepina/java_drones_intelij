package Drone;

public class DroneTypes extends Catalog {
    private int id;
    private String manufacturer;
    private String typename;
    private int weight;
    private int maximumSpeed;
    private int battery;
    private int capacity;
    private int controlRange;
    private int maximumCarriage;

    @Override
    public String toString() {
        return "DroneDetails [id=" + id
                + ", manufacturer=" + manufacturer
                + ", typename=" + typename
                + ", weight=" + weight
                + ", maximumSpeed=" + maximumSpeed
                + ", battery=" + battery
                + ", capacity=" + capacity
                + ", controlRange=" + controlRange
                + ", maximumCarriage=" + maximumCarriage + "]";
    }


    public int getId() {
        return this.id;
    }


}
