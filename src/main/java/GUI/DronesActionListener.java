package GUI;

import Drone.Drones;
import Drone.Convert;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DronesActionListener implements ActionListener{
    private MyFrame frame;
    private ArrayList<Drones> DronesList;
    private Convert helper;

    public DronesActionListener(MyFrame frame, ArrayList<Drones> DronesList){
        this.frame = frame;
        this.DronesList = DronesList;
        this.helper = new Convert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONES");

        String[] columns = {"ID", "CreationTime", "SerialNumber", "CarriageWeight", "CarriageType"};
        Object[][] data = helper.ArrayList2ObjectDrones(DronesList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }

}
