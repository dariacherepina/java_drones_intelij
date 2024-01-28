package GUI;

import Drone.Convert;
import Drone.DroneDynamics;
import Drone.Drones;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DroneDynamicsActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<DroneDynamics> droneDynamicsList;
    private Convert helper;

    public DroneDynamicsActionListener(MyFrame frame, ArrayList<DroneDynamics> droneDynamicsList){
        this.frame = frame;
        this.droneDynamicsList = droneDynamicsList;
        this.helper = new Convert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE DYNAMICS");

        frame.getPanelSort().add(frame.getSortByStatus());
        frame.getPanelSort().remove(frame.getSortByCarriageWeight());
        frame.getPanelSort().remove(frame.getSortByMaximumCarriage());
        frame.getPanelSort().remove(frame.getSortBySpeed());
        frame.getMainPanel().revalidate();
        frame.getMainPanel().repaint();

        String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data = helper.ArrayList2ObjectDroneDynamics(droneDynamicsList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }
}
