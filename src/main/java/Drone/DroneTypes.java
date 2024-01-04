package Drone;

import java.util.Comparator;

public class DroneTypes extends Catalog implements Comparator {
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

    @Override
    public int compare(Object o, Object t1) {
        return 0;
    }
}
