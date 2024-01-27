package GUI;

import Drone.Convert;
import Drone.DroneTypes;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DroneTypesActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<DroneTypes> DroneTypesList;
    private Convert helper;

    public DroneTypesActionListener(MyFrame frame, ArrayList<DroneTypes> DroneTypesList) {
        this.frame = frame;
        this.DroneTypesList = DroneTypesList;
        this.helper = new Convert();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE TYPES");
        frame.getEastPanel().remove(frame.getRefreshButton());
        frame.getEastPanel().add(frame.getSortByMaximumCarriage());
        frame.getEastPanel().add(frame.getSortBySpeed());

        String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data = helper.ArrayList2ObjectDroneType(DroneTypesList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));


    }

}