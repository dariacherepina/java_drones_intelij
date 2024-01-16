package Drone;

import java.util.ArrayList;

// Class representing a drone
public class Drone {
    int id;
    String model;
    String manufacturer;
    int maxFlightTime; //in minutes
    int maxRange;   // in meters
    boolean builtInCamera;
    String dronetype;
    String created;
    String serialnumber;
    String carriage_weight;
    String carriage_type;

    // Constructor
    public Drone(int id, String dronetype, String created, String serialnumber, String carriage_weight, String carriage_type) {
        this.id = id;
        this.dronetype = dronetype;
        this.created = created;
        this.serialnumber = serialnumber;
        this.carriage_weight = carriage_weight;
        this.carriage_type = carriage_type;
    }

    public interface DroneStatus {
    }
}






