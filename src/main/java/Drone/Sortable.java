package Drone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public interface Sortable {
    /**
     * To sort ArrayList<Drones> by CarriageWeight
     *
     * @param dronesList ArrayList<Drones>
     * @return ArrayList<Drones> sorted
     */
    static ArrayList<Drones> sortCarriageWeight(ArrayList<Drones> dronesList) {
        Collections.sort(dronesList, new Comparator<Drones>() {
            @Override
            public int compare(Drones d1, Drones d2) {
                return Integer.compare(d1.getCarriageWeight(), d2.getCarriageWeight());
            }
        });
        return dronesList;
    }

    /**
     * To sort ArrayList<DroneTypes> by speed
     *
     * @param droneTypesList ArrayList<DroneTypes>
     * @return ArrayList<DroneTypes> sorted
     */
    static ArrayList<DroneTypes> sortSpeed(ArrayList<DroneTypes> droneTypesList) {
        Collections.sort(droneTypesList, new Comparator<DroneTypes>() {
            @Override
            public int compare(DroneTypes d1, DroneTypes d2) {
                return Integer.compare(d1.getMaximumSpeed(), d2.getMaximumSpeed());
            }
        });
        return droneTypesList;
    }

    /**
     * To sort ArrayList<DroneTypes> by maximumCarriage
     *
     * @param droneTypesList ArrayList<DroneTypes>
     * @return ArrayList<DroneTypes> sorted
     */
    static ArrayList<DroneTypes> sortMaximumCarriage(ArrayList<DroneTypes> droneTypesList) {
        Collections.sort(droneTypesList, new Comparator<DroneTypes>() {
            @Override
            public int compare(DroneTypes d1, DroneTypes d2) {
                return Integer.compare(d1.getMaximumCarriage(), d2.getMaximumCarriage());
            }
        });
        return droneTypesList;
    }

    /**
     * ON goes first, OF second and IS last
     * To sort ArrayList<DroneDynamics>  by status
     *
     * @param droneDynamicsList ArrayList<DroneDynamics>
     * @return ArrayList<DroneDynamics> sorted
     */
    static ArrayList<DroneDynamics> sortStatus(ArrayList<DroneDynamics> droneDynamicsList) {
        Collections.sort(droneDynamicsList, new Comparator<DroneDynamics>() {
            @Override
            public int compare(DroneDynamics d1, DroneDynamics d2) {
                return d1.getStatus().compareTo(d2.getStatus());
            }
        });

        Collections.sort(droneDynamicsList, new Comparator<DroneDynamics>() {
            @Override
            public int compare(DroneDynamics d1, DroneDynamics d2) {
                if (d1.getStatus().equals("ON")) {
                    return -1;
                } else if (d1.getStatus().equals("OF")) {
                    if (d2.getStatus().equals("ON")) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    if (d2.getStatus().equals("ON") || d2.getStatus().equals("OF")) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });
        return droneDynamicsList;
    }


}