package Drone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public interface Sort {
    public static ArrayList<Drones> sortCarriageWeight(ArrayList<Drones> dronesList) {
        Collections.sort(dronesList, new Comparator<Drones>() {
            @Override
            public int compare(Drones d1, Drones d2) {
                return Integer.compare(d1.getCarriageWeight(), d2.getCarriageWeight());
            }
        });
        return dronesList;
    }

    public static ArrayList<DroneTypes> sortSpeed(ArrayList<DroneTypes> droneTypesList) {
        Collections.sort(droneTypesList, new Comparator<DroneTypes>() {
            @Override
            public int compare(DroneTypes d1, DroneTypes d2) {
                return Integer.compare(d1.getMaximumSpeed(), d2.getMaximumSpeed());
            }
        });
        return droneTypesList;
    }

    public static ArrayList<DroneTypes> sortMaximumCarriage(ArrayList<DroneTypes> droneTypesList) {
        Collections.sort(droneTypesList, new Comparator<DroneTypes>() {
            @Override
            public int compare(DroneTypes d1, DroneTypes d2) {
                return Integer.compare(d1.getMaximumCarriage(), d2.getMaximumCarriage());
            }
        });
        return droneTypesList;
    }

    public static ArrayList<DroneDynamics> sortStatus(ArrayList<DroneDynamics> droneDynamicsList) {
        Collections.sort(droneDynamicsList, new Comparator<DroneDynamics>() {
            @Override
            public int compare(DroneDynamics d1, DroneDynamics d2) {
                return d1.getStatus().compareTo(d2.getStatus());
            }
        });
        // ON < OF < IS
        //-1 -> d1 < d2
        //0 -> d1== d2
        //1 -> d1 > d2
        Collections.sort(droneDynamicsList, new Comparator<DroneDynamics>() {
            @Override
            public int compare(DroneDynamics d1, DroneDynamics d2) { // ON
                if (d1.getStatus().equals("ON")) {
                    return -1; // ON goes first
                } else if (d1.getStatus().equals("OF")) { // OF
                    if (d2.getStatus().equals("ON")){
                        return 1; // if ON or OF -> OF goes second
                    }else{
                        return -1; //if IS or OF -> OF goes first
                    }
                } else { // IS
                    if(d2.getStatus().equals("ON") || d2.getStatus().equals("OF")){
                        return 1; // everything accept IS goes first
                    }else{
                        return -1;
                    }
                }
            }
        });
        return droneDynamicsList;
    }


}
