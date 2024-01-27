
package GUI;

import API.Stream;
import Drone.Convert;
import Drone.DroneDynamics;
import Drone.DroneTypes;
import Drone.Drones;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class RefreshActionListener implements ActionListener {
    private static final Logger LOGGER = Logger.getLogger(Convert.class.getName());
    private Convert helper = new Convert();

    private MyFrame frame;
    private ArrayList<Drones> DronesList;
    ArrayList<DroneTypes> DroneTypesList;
    ArrayList<DroneDynamics> DroneDynamicsList;

    public RefreshActionListener(MyFrame frame, ArrayList<Drones> DronesList,ArrayList<DroneTypes> DroneTypesList,ArrayList<DroneDynamics> DroneDynamicsList) {
        this.frame = frame;
        this.DronesList = DronesList;
        this.DroneTypesList = DroneTypesList;
        this.DroneDynamicsList = DroneDynamicsList;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            updateTables(DronesList, DroneTypesList, DroneDynamicsList);
            String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
            Object[][] data = helper.convertArrayListToObjectDrones(DronesList);
            frame.getTable().setBackground(Color.PINK);
            frame.getTable().setModel(new DefaultTableModel(data, columns));


            String[] columns1 = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
            Object[][] data1 = helper.convertArrayListToObjectDroneType(DroneTypesList);
            frame.getTable().setModel(new DefaultTableModel(data1, columns1));


            String[] columns2 = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
            Object[][] data2 = helper.convertArrayListToObjectDroneDynamics(DroneDynamicsList);
            frame.getTable().setModel(new DefaultTableModel(data2, columns2));


        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
    private void updateTables(ArrayList<Drones> DronesList, ArrayList<DroneTypes> DroneTypesList, ArrayList<DroneDynamics> DroneDynamicsList) throws IOException {
        Stream.fetchData();
        LOGGER.info("Data is updated!");
        DronesList = null;
        DroneTypesList = null;
        DroneDynamicsList = null;
        DronesList = helper.initialiseDrones(Stream.dataStreamOut("outputDrones"));
        DroneTypesList = helper.initialiseDroneTypes(Stream.dataStreamOut("outputDroneTypes"));
        DroneDynamicsList = helper.initialiseDroneDynamics(Stream.dataStreamOut("outputDroneDynamics"));
        helper.addAdditinalDataToDrone(DronesList, DroneTypesList, DroneDynamicsList);
        LOGGER.info("Data is initialised!");

    }
}