package GUI;

import Drone.Convert;
import Drone.DroneTypes;
import Drone.Sortable;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SortByMaximumCarriage implements ActionListener {
    private Convert helper;
    private MyFrame frame;
    private ArrayList<DroneTypes> droneTypesList;


    public SortByMaximumCarriage(MyFrame frame, ArrayList<DroneTypes> droneTypesList){
        this.frame = frame;
        this.droneTypesList = droneTypesList;
        this.helper = new Convert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data = helper.convertArrayListToObjectDroneType(Sortable.sortMaximumCarriage(droneTypesList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));
        frame.getTable().repaint();
    }
}