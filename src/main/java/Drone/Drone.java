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
    public Drone(int id , String dronetype, String created, String serialnumber , String carriage_weight, String carriage_type) {
        this.id = id;
        this.dronetype = dronetype;
        this.created = created;
        this.serialnumber = serialnumber;
        this.carriage_weight = carriage_weight;
        this.carriage_type = carriage_type;
    };

    /* Drone Arrayliste muss ich noch mal überarbeiten das hat irgenwie noch nicht geklappt
       // Creating an ArrayList for the drone catalog
       ArrayList<Drone> DroneCatalog = new ArrayList<>();
        DroneCatalog.add(drone1);
        DroneCatalog.add(drone2);
        DroneCatalog.add(drone3);
        DroneCatalog.add(drone4);
        DroneCatalog.add(drone5);
        DroneCatalog.add(drone6);

        // Display info about each drone in the catalog
        for (Drone drone : DroneCatalog) {
            drone.display();
        }
*/





