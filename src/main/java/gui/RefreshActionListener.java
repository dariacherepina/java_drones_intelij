package gui;

import api.Stream;
import drone.Convert;
import drone.DroneDynamics;
import drone.DroneTypes;
import drone.Drones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Implements the ActionListener of the `refreshButton`
 * @author Afnan Ismail & Daria Cherepina
 */

public class RefreshActionListener implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());
    private Convert helper;
    private MyFrame frame;
    private ArrayList<Drones> dronesList;
    private ArrayList<DroneTypes> droneTypesList;
    private ArrayList<DroneDynamics> droneDynamicsList;

    public RefreshActionListener(MyFrame frame, ArrayList<Drones> dronesList, ArrayList<DroneTypes> droneTypesList, ArrayList<DroneDynamics> droneDynamicsList) {
        this.frame = frame;
        this.dronesList = dronesList;
        this.droneTypesList = droneTypesList;
        this.droneDynamicsList = droneDynamicsList;
        this.helper = new Convert();
    }

    /**
     * Updates the table when clicking on the buttons Dashboard, Drones, DroneTypes and DroneDynamics
     * @param e the event to be processed
     * @author Afnan Ismail & Daria Cherepina
     */

    public void actionPerformed(ActionEvent e) {
        try {
            updateTables(dronesList, droneTypesList, droneDynamicsList);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.convertArrayListToObjectDrones(dronesList);
        frame.getTable().setBackground(Color.GRAY);
        frame.getTable().setForeground(Color.BLACK);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

        String[] columns1 = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data1 = helper.convertArrayListToObjectDroneType(droneTypesList);
        frame.getTable().setModel(new DefaultTableModel(data1, columns1));

        String[] columns2 = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data2 = helper.convertArrayListToObjectDroneDynamics(droneDynamicsList);
        frame.getTable().setModel(new DefaultTableModel(data2, columns2));
    }

    /**
     * The data is updated with Stream.fetchData()
     * and then we replace old data in the ArrayLists with new one
     * @param DronesList List of Drones
     * @param DroneTypesList List of DroneType
     * @param DroneDynamicsList List Of DroneDynamics
     * @throws IOException input/output is wrong
     * @author Daria Cherepina & Afnan Ismail
     */

    private void updateTables(ArrayList<Drones> DronesList, ArrayList<DroneTypes> DroneTypesList, ArrayList<DroneDynamics> DroneDynamicsList) throws IOException {
        JOptionPane.showMessageDialog(frame,
                "Your data is being refreshed right now. Please click 'OK'.",
                "Attention!",
                JOptionPane.INFORMATION_MESSAGE);
        LOGGER.info("Data is being refreshed!");

        Stream.fetchData();

        DronesList = null;
        DroneTypesList = null;
        DroneDynamicsList = null;
        DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
        DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
        DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));

        helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);

        JOptionPane.showMessageDialog(frame,
                "Data initialized successfully! Click the button you want, to see the refreshed data.",
                "Data initialized!",
                JOptionPane.INFORMATION_MESSAGE);
        LOGGER.info("Data is initialized!");
    }
}
