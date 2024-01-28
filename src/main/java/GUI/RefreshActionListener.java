package GUI;

import API.Stream;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import static GUI.MyFrame.helper;

public class RefreshActionListener implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());
    private MyFrame frame;
    private ArrayList<Drones> dronesList;
    ArrayList<DroneTypes> droneTypesList;
    ArrayList<DroneDynamics> droneDynamicsList;

    public RefreshActionListener(MyFrame frame, ArrayList<Drones> dronesList,ArrayList<DroneTypes> droneTypesList,ArrayList<DroneDynamics> droneDynamicsList) {
        this.frame = frame;
        this.dronesList = dronesList;
        this.droneTypesList = droneTypesList;
        this.droneDynamicsList = droneDynamicsList;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            updateTables(dronesList, droneTypesList, droneDynamicsList);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.ArrayList2ObjectDrones(dronesList);
        frame.getTable().setBackground(Color.GRAY);
        frame.getTable().setForeground(Color.BLACK);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

        String[] columns1 = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data1 = helper.ArrayList2ObjectDroneType(droneTypesList);
        frame.getTable().setModel(new DefaultTableModel(data1, columns1));

        String[] columns2 = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data2 = helper.ArrayList2ObjectDroneDynamics(droneDynamicsList);
        frame.getTable().setModel(new DefaultTableModel(data2, columns2));
    }

    private void updateTables(ArrayList<Drones> dronesList, ArrayList<DroneTypes> droneTypesList, ArrayList<DroneDynamics> droneDynamicsList) throws IOException {
        JOptionPane.showMessageDialog(frame,
                "Please wait...",
                "Attention",
                JOptionPane.INFORMATION_MESSAGE);
        LOGGER.info("Data is updated!");

        Stream.fetchData();

        JOptionPane.showMessageDialog(frame,          // Popup with message that data has been updated
                "Data updated successfully!",
                "Data Updated",
                JOptionPane.INFORMATION_MESSAGE);
        LOGGER.info("Data is updated!");

        dronesList = null;
        droneTypesList = null;
        droneDynamicsList = null;
        dronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
        droneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
        droneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
        helper.addAdditinalDataToDrone(dronesList, droneTypesList, droneDynamicsList);

        JOptionPane.showMessageDialog(frame,
                "Data initialised successfully! Click the button you want, to see the updated data.",
                "Data initialised",
                JOptionPane.INFORMATION_MESSAGE);
        LOGGER.info("Data is initialised!");

    }
}


