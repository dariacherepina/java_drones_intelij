package GUI;

import Drone.DroneTypes;
import Drone.Convert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DroneTypesActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<DroneTypes> droneTypesList;
    private Convert helper;

    public DroneTypesActionListener(MyFrame frame, ArrayList<DroneTypes> droneTypesList) {
        this.frame = frame;
        this.droneTypesList = droneTypesList;
        this.helper = new Convert();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE TYPES");

        frame.getPanelSort().remove(frame.getSortByCarriageWeight());
        frame.getPanelSort().remove(frame.getSortByStatus());
        frame.getPanelSort().add(frame.getSortByMaximumCarriage());
        frame.getPanelSort().add(frame.getSortBySpeed());
        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();

        String[] columns = {"ID", "Manufacturer", "TypeName", "Weight", "MaximumSpeed", "BatteryCapacity", "ControlRange", "MaximumCarriage"};
        Object[][] data = helper.convertArrayListToObjectDroneType(droneTypesList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

        JScrollPane scrollPane = (JScrollPane) frame.getTable().getParent().getParent(); //whole scrollPane,sets background color of the table-scrollPane
        scrollPane.getViewport().setBackground(Color.DARK_GRAY);  //Viewport = table
    }
}