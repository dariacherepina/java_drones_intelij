package GUI;

import Drone.Convert;
import Drone.DroneDynamics;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DroneDynamicsActionListener implements ActionListener {
    private MyFrame frame;
    private ArrayList<DroneDynamics> DroneDynamicsList;
    private Convert helper;

    public DroneDynamicsActionListener(MyFrame frame, ArrayList<DroneDynamics> DroneDynamicsList){
        this.frame = frame;
        this.DroneDynamicsList = DroneDynamicsList;
        this.helper = new Convert();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setLabel("DRONE DYNAMICS");
        frame.getEastPanel().remove(frame.getRefreshButton());
        frame.getEastPanel().add(frame.getSortByStatus());



        String[] columns = {"ID", "TimeStamp", "Speed", "AlignmentRoll", "Pitch", "AlignmentYaw", "Longitude", "Latitude", "BatteryStatus", "LastSeen", "Status"};
        Object[][] data = helper.convertArrayListToObjectDroneDynamics(DroneDynamicsList);
        frame.getTable().setModel(new DefaultTableModel(data, columns));

    }
}