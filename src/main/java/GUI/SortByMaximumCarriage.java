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
    private ArrayList<DroneTypes> DroneTypesList;

    public SortByMaximumCarriage(MyFrame frame, ArrayList<DroneTypes> DroneTypesList){
        this.frame = frame;
        this.DroneTypesList = DroneTypesList;
        this.helper = new Convert();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data = helper.ArrayList2ObjectDroneType(Sortable.sortMaximumCarriage(DroneTypesList));
        frame.getTable().setModel(new DefaultTableModel(data, columns));
        frame.getTable().repaint();


    }
}