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
        String[] columns = {"ID", "TypeName", "Manufacturer"};

        // Determine the number of rows needed based on the ArrayList with the smallest size
        int numRows = DronesList.size();

        // Create a 2D array to hold the data for the table
        Object[][] data = new Object[numRows][columns.length];

        int minSize = DronesList.size();

        for (int i = 0; i < minSize; i++) {
            Drones drone = DronesList.get(i);
            data[i][0] = drone.getId();
            data[i][1] = drone.getDroneType().getTypeName();

            DroneDynamics droneDynamics = DroneDynamicsList.get(i);
            data[i][2] = drone.getDroneType().getManufacturer();
        }


        // Set the new data model for the table
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }


}