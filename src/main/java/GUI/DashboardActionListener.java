package GUI;

import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;
import GUI.MyFrame;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DashboardActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<Drones> DronesList;
    private ArrayList<DroneTypes>  DroneTypesList;
    private ArrayList<DroneDynamics> DroneDynamicsList;

    public DashboardActionListener(MyFrame frame, ArrayList<Drones> DronesList, ArrayList<DroneTypes>  DroneTypesList,ArrayList<DroneDynamics> DroneDynamicsList){
        this.frame = frame;
        this.DronesList = DronesList;
        this.DroneTypesList = DroneTypesList;
        this.DroneDynamicsList = DroneDynamicsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE OVERVIEW");

        // Define the columns for the table
        String[] columns = {"ID", "TypeName", "Status"};



        // Determine the number of rows needed based on the ArrayList with the largest size
        int numRows = Math.max(DronesList.size(), Math.max(DroneTypesList.size(), DroneDynamicsList.size()));

        // Create a 2D array to hold the data for the table
        Object[][] data = new Object[numRows][columns.length];

        // Populate the data array with the specific information you need
        for (int i = 0; i < numRows; i++) {
            if (i < DronesList.size()) {
                Drones drone = DronesList.get(i);
                data[i][0] = drone.getId();

                // Assuming you have a method to get TypeName from Drone
                data[i][1] = drone.getDroneType().getTypeName();

                if (i < DroneDynamicsList.size()) {
                    DroneDynamics droneDynamics = DroneDynamicsList.get(i);
                    // Assuming you have a method to get Status from DroneDynamics
                    data[i][2] = droneDynamics.getStatus();
                }
//                        else {
//                            data[i][2] = ""; // Placeholder for Status, modify accordingly
//                        }
            }
//                    else {
//                        // Populate remaining rows with empty data or placeholders
//                        data[i][0] = "";
//                        data[i][1] = "";
//                        data[i][2] = "";
//                    }


            if (i < DroneTypesList.size()) {
                DroneTypes droneType = DroneTypesList.get(i);
                // You need to decide how to map DroneTypes data to the columns
            }

            if (i < DroneDynamicsList.size()) {
                DroneDynamics droneDynamics = DroneDynamicsList.get(i);
                // You need to decide how to map DroneDynamics data to the columns
            }
        }

        // Set the new data model for the table
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }


}