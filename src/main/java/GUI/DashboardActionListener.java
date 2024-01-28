package GUI;

import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DashboardActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<Drones> dronesList;
    private ArrayList<DroneTypes>  droneTypesList;
    private ArrayList<DroneDynamics> droneDynamicsList;

    public DashboardActionListener(MyFrame frame, ArrayList<Drones> dronesList, ArrayList<DroneTypes>  droneTypesList,ArrayList<DroneDynamics> droneDynamicsList){
        this.frame = frame;
        this.dronesList = dronesList;
        this.droneTypesList = droneTypesList;
        this.droneDynamicsList = droneDynamicsList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE OVERVIEW");

        frame.getPanelSort().remove(frame.getSortByCarriageWeight());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getPanelSort().remove(frame.getSortByMaximumCarriage());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getPanelSort().remove(frame.getSortByStatus());

        String[] columns = {"ID", "TypeName", "Manufacturer"}; // Define the columns for the table
        int numRows = dronesList.size(); // Determine the number of rows needed based on the ArrayList with the smallest size
        Object[][] data = new Object[numRows][columns.length]; // Create a 2D array to hold the data for the table
        int minSize = dronesList.size();

        for (int i = 0; i < minSize; i++) {
            Drones drone = dronesList.get(i);
            data[i][0] = drone.getId();
            data[i][1] = drone.getDroneType().getTypeName();

            DroneDynamics droneDynamics = droneDynamicsList.get(i);
            data[i][2] = drone.getDroneType().getManufacturer();
        }
        frame.getTable().setModel(new DefaultTableModel(data, columns));         // Set the new data model for the table
    }
}